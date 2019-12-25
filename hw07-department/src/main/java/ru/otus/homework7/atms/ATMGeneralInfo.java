package ru.otus.homework7.atms;

public class ATMGeneralInfo {
    private String bankName;
    private String color;

    public ATMGeneralInfo(String bankName, String color) {
        this.bankName = bankName;
        this.color = color;
    }

    public String getInfo() {
        return bankName + " " + color;
    }
}
