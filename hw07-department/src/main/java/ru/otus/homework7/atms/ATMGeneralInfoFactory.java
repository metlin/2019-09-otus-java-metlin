package ru.otus.homework7.atms;

//Flyweight
public class ATMGeneralInfoFactory {
    private final ATMGeneralInfo atmGeneralInfo;

    public ATMGeneralInfoFactory() {
        atmGeneralInfo = new ATMGeneralInfo("Sber", "green");
    }

    public ATMImpl createATM(int sum, int initialState) {
        return new ATMImpl(atmGeneralInfo, sum, initialState);
    }
}
