package com.mws.domain.request;

import static com.google.common.base.Preconditions.checkArgument;


public record MarketInput(String exchange, String pair, String timeframe, boolean active) {
    public MarketInput {
        checkArgument(exchange != null && !exchange.isEmpty(), "Exchange cannot be null or empty");
        checkArgument(pair != null && !pair.isEmpty(), "Pair cannot be null or empty");
        checkArgument(timeframe != null && !timeframe.isEmpty(), "Timeframe cannot be null or empty");
    }
}