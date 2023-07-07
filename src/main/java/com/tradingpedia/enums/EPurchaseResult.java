package com.tradingpedia.enums;


public enum EPurchaseResult {
    WAIT_CODE(1),
    CODE_RECEIVED(2),
    NUMBER_CANCELED(3),
    NUMBER_BANNED(4),
    WAIT_CODE_AGAIN(5),
    COMPLETED(6);

    private final int value;

    EPurchaseResult(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
