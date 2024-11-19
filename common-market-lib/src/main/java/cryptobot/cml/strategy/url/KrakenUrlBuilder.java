package cryptobot.cml.strategy.url;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

public class KrakenUrlBuilder implements UrlStrategy {
    @Override
    public String buildCandlestickUrl(String pair, String interval, LocalDateTime start) {
        return "https://api.kraken.com/0/public/OHLC?pair=" + pair + "&interval=" + interval +"&since=" + start.toEpochSecond(ZoneOffset.UTC);
    }

    @Override
    public String buildCandlestickUrl(String pair, String interval, LocalDateTime start, LocalDateTime end) {
        return "https://api.kraken.com/0/public/OHLC?pair=" + pair + "&interval=" + interval +"&since=" + start.toEpochSecond(ZoneOffset.UTC);
    }

    @Override
    public String buildWebSocketUrl() {
        return null;
    }
}
