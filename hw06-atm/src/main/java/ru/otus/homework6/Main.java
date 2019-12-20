package ru.otus.homework6;

public class Main {
    public static void main(String[] args) {
        ATM atm = new SberbankATM();
        atm.showBalance();
        atm.addBanknotes(50 ,100);
        atm.showBalance();
        atm.issueRequestedAmount(110);
        atm.showBalance();
        atm.issueRequestedAmount(100);
        atm.showBalance();
        atm.addBanknotes(100, 50);
        atm.showBalance();
        atm.issueRequestedAmount(150);
        atm.showBalance();

        atm.issueRequestedAmount(50000);

        atm.addBanknotes(10, 100);
        atm.showBalance();
        atm.issueRequestedAmount(3000);
        atm.showBalance();
        atm.issueRequestedAmount(75000);
        atm.showBalance();
        atm.issueRequestedAmount(2100);
        atm.showBalance();
    }
}
