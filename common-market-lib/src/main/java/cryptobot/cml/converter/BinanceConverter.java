package cryptobot.cml.converter;

import cryptobot.cml.enums.Timeframe;
import cryptobot.cml.enums.Pair;

public class BinanceConverter {
    private BinanceConverter() {
    }

    public static String convertPair(Pair pair) {
        return switch (pair) {
            case EUR_BTC -> "BTCEUR";
            case EUR_ETH -> "ETHEUR";
            case EUR_ADA -> "ADAEUR";
            case EUR_XRP -> "XRPEUR";
            default -> throw new IllegalArgumentException("Invalid Binance Pair: " + pair);
        };
    }

    public static String convertTimeframe(Timeframe timeframe) {
        return switch (timeframe) {
            case ONE_MINUTE -> "1m";
            case FIVE_MINUTES -> "5m";
            case FIFTEEN_MINUTES -> "15m";
            case THIRTY_MINUTES -> "30m";
            case ONE_HOUR -> "1h";
            case FOUR_HOURS -> "4h";
            case ONE_DAY -> "1d";
            default -> throw new IllegalArgumentException("Invalid Binance Interval: " + timeframe);
        };
    }
}
