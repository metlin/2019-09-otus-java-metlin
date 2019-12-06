package ru.otus.homework6;

public class Main {
    public static void main(String[] args) {
        ATM atm = new SberbankATM();
        atm.showBalance();
        atm.addBanknotes(10 ,50 ,50 ,100);
        atm.showBalance();
        atm.issueRequestedAmount(110);
        atm.showBalance();
        atm.issueRequestedAmount(100);
        atm.showBalance();
        atm.addBanknotes(100, 50, 50, 50);
        atm.showBalance();
        atm.issueRequestedAmount(150);
        atm.showBalance();

        atm.issueRequestedAmount(50000);
        int count = 0;
        while (count < 500) {
            atm.addBanknotes(10, 50, 100);
            count++;
        }

        atm.showBalance();
        atm.issueRequestedAmount(3000);
        atm.showBalance();
        atm.issueRequestedAmount(75000);
        atm.showBalance();
        atm.issueRequestedAmount(2100);
        atm.showBalance();

    }
}
