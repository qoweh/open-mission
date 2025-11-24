package com.openmission.domain.mail;

import java.util.Arrays;

public enum Priority {
    NORMAL(1, "일반", "@"),
    URGENT(2, "긴급", "@@"),
    IMPORTANT(3, "중요", "@@@");

    private final int priorityValue;
    private final String label;
    private final String symbol;

    Priority(int priorityValue, String label, String symbol) {
        this.priorityValue = priorityValue;
        this.label = label;
        this.symbol = symbol;
    }

    public String getPriorityValue() {
        return String.valueOf(priorityValue);
    }

    public static Priority getPriority(int priorityValue) {
        return Arrays
                .stream(values())
                .filter(priority -> priority.priorityValue == priorityValue)
                .findFirst()
                .orElse(NORMAL);
    }

    public static Priority fromSymbol(String symbol) {
        return Arrays.stream(values())
                .filter(priority -> priority.symbol.equals(symbol))
                .findFirst()
                .orElse(NORMAL);
    }
}
