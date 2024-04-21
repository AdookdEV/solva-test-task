package com.example.expensetrackerservice.enums;

public enum ExpenseCategory {
    SERVICE("SERVICE"),
    PRODUCT("PRODUCT");

    private String value;

    ExpenseCategory(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
