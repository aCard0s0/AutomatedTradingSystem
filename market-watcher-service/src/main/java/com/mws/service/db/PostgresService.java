package com.mws.service.db;

import com.cml.model.Market;
import com.mws.domain.CandlestickEntity;
import jakarta.annotation.Resource;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.*;
import java.util.Collection;
import java.util.Optional;

/**
 * This class is a wrapper for the Postgres JDBC driver.
 * It is used to create the partition tables where exchange, pair and timeframe are the partition keys.
 * The partition tables are created based on the exchange, pair and timeframe.
 * This class is stateless and thread-safe.
 */
// TODO: Repository pattern?
@Component
@Log4j2
public class PostgresService implements SqlClientService {
    @Resource
    private DataSource dataSource;

    public PostgresService(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void createTableIfNotExists(Market market) {
        try (Connection connection = dataSource.getConnection()) {
            if (!tableExists(connection, market.getTableName())) {
                createPartitionTable(connection, market);
            }
        } catch (SQLException e) {
            log.error("operation=createTable, message='Error creating table', market.code='{}', exception='{}'", market.getMarketCode(), e);
        }
    }

    @Override
    public void insertIntoTable(Market market, Collection<CandlestickEntity> candlesticks) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(buildInsertQuery(market), Statement.RETURN_GENERATED_KEYS)) {
            for (CandlestickEntity candlestick : candlesticks) {
                statement.setString(1, candlestick.getPair());
                statement.setString(2, candlestick.getTimeframe());
                statement.setBigDecimal(3, candlestick.getOpen());
                statement.setBigDecimal(4, candlestick.getHigh());
                statement.setBigDecimal(5, candlestick.getLow());
                statement.setBigDecimal(6, candlestick.getClose());
                statement.setBigDecimal(7, candlestick.getVolume());
                statement.setTimestamp(8, candlestick.getTimestamp());
                statement.addBatch();
            }
            statement.executeBatch();
            log.info("operation=insertIntoTable, message='Candlesticks inserted successfully', market.code='{}' candles.size='{}'", market.getMarketCode(), candlesticks.size());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<CandlestickEntity> getLastCandlestick(Market market) {
        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement()) {
            String query = buildGetLastCandlestickQuery(market);
            ResultSet resultSet = statement.executeQuery(query);
            if (resultSet.next()) {
                CandlestickEntity candle = CandlestickEntity.builder()
                        .pair(resultSet.getString("pair"))
                        .timeframe(resultSet.getString("timeframe"))
                        .open(resultSet.getBigDecimal("open"))
                        .high(resultSet.getBigDecimal("high"))
                        .low(resultSet.getBigDecimal("low"))
                        .close(resultSet.getBigDecimal("close"))
                        .volume(resultSet.getBigDecimal("volume"))
                        .timestamp(resultSet.getTimestamp("timestamp"))
                        .build();
                return Optional.of(candle);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return Optional.empty();
    }

    private boolean tableExists(Connection connection, String tableName) throws SQLException {
        DatabaseMetaData metaData = connection.getMetaData();
        ResultSet resultSet = metaData.getTables(null, null, tableName, null);
        return resultSet.next();
    }

    private void createPartitionTable(Connection connection, Market market) throws SQLException {
        try (Statement statement = connection.createStatement()) {
            String query = buildCreateTableQuery(market);
            statement.executeUpdate(query);
        }
        log.info("operation=createPartitionTable, message='Table partition created successfully', marketTablet='{}'", market.getTableName());
    }

    private String buildCreateTableQuery(Market market) {
        return "CREATE TABLE " + market.getTableName() + " (" +
                "CHECK (pair = '" + market.getPairStr() + "' AND timeframe = '" + market.getTimeframeStr() + "')," +
                "CONSTRAINT unique_" + market.getMarketCode() + " UNIQUE (timestamp)" +
                ") INHERITS (candlestick);";
    }

    private String buildInsertQuery(Market market) {
        return "INSERT INTO " + market.getTableName() +
                " (pair, timeframe, open, high, low, close, volume, timestamp) VALUES (?, ?, ?, ?, ?, ?, ?, ?)" +
                " ON CONFLICT (timestamp) DO UPDATE" +
                " SET volume = EXCLUDED.volume;";
    }

    private String buildGetLastCandlestickQuery(Market market) {
        return "SELECT * FROM "+ market.getTableName() +" ORDER BY id DESC LIMIT 1;";
    }
}
