package ru.otus.homework6.cells;

public enum Nominal {
    TEN(10),
    FIFTY(50),
    HUNDRED(100);

    private final int value;

    Nominal(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
