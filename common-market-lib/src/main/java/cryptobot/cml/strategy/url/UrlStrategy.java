package cryptobot.cml.strategy.url;

import java.time.LocalDateTime;

public interface UrlStrategy {
    String buildCandlestickUrl(String pair, String interval, LocalDateTime start);
    String buildCandlestickUrl(String pair, String interval, LocalDateTime start, LocalDateTime end);

    String buildWebSocketUrl();

}
