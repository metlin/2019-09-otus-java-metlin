package ru.otus.homework7.atms;

public class ATMImpl implements ATM {

    private final ATMGeneralInfo atmGeneralInfo;
    private int sum;
    private final int initialState;

    public ATMImpl(ATMGeneralInfo atmGeneralInfo, int sum, int initialState) {
        this.atmGeneralInfo = atmGeneralInfo;
        this.sum = sum;
        this.initialState = initialState;
    }

    @Override
    public int getBalance() {
        return sum;
    }

    @Override
    public void getInitialState() {
        sum = initialState;
    }

    @Override
    public void addSum(int sum) {
        this.sum += sum;
    }
}
