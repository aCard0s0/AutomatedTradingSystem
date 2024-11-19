package cryptobot.cml.strategy.url;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

public record BinanceUrlBuilder() implements UrlStrategy {
    @Override
    public String buildCandlestickUrl(String pair, String interval, LocalDateTime start) {
        return "https://api.binance.com/api/v3/klines?symbol=%s&startTime=%s&interval=%s&limit=1000".formatted(
                pair,
                start.toInstant(ZoneOffset.UTC).toEpochMilli(),
                interval);
    }

    @Override
    public String buildCandlestickUrl(String pair, String interval, LocalDateTime start, LocalDateTime end) {
        return "https://api.binance.com/api/v3/klines?symbol=%s&startTime=%s&endTime=%s&interval=%s&limit=1000".formatted(
                pair,
                start.toInstant(ZoneOffset.UTC).toEpochMilli(),
                end.toInstant(ZoneOffset.UTC).toEpochMilli(),
                interval);
    }

    @Override
    public String buildWebSocketUrl() {
        return null;
    }
}
