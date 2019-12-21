package ru.otus.homework7.atms;

import java.util.ArrayList;
import java.util.List;

public class ATMGroup implements ATM {

    private final List<ATM> atmList = new ArrayList<>();

    public void addATM(ATM atm) {
        atmList.add(atm);
    }

    public List<ATM> getATMGroup() {
        return atmList;
    }

    @Override
    public int getBalance() {
        int sum = 0;
        for (ATM atm : atmList) {
            sum += atm.getBalance();
        }

        return sum;
    }

    @Override
    public void getInitialState() {
        atmList.forEach(ATM::getInitialState);
    }

    @Override
    public void addSum(int sum) {
        atmList.forEach(atm -> atm.addSum(sum));
    }
}
