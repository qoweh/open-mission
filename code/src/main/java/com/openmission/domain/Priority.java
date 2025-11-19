package com.openmission.domain;

import java.util.Arrays;

public enum Priority {
    NORMAL(1, "일반"),
    URGENT(2, "긴급"),
    IMPORTANT(3, "중요");

    private final int _level;
    private final String _name;

    Priority(int level, String name) {
        _level = level;
        _name = name;
    }

    public static Priority getPriority(int level) {
        return Arrays
                .stream(values())
                .filter(priority -> priority._level == level)
                .findFirst()
                .orElse(NORMAL);
    }
}
