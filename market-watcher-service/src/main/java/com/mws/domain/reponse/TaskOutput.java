package com.mws.domain.reponse;

public record TaskOutput(String marketCode, String status) {
    public TaskOutput {
        if (marketCode == null || marketCode.isEmpty() || marketCode.split("_").length != 3) {
            throw new IllegalArgumentException("Market code is not valid");
        }
    }
}
