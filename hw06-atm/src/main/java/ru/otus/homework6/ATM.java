package ru.otus.homework6;

public interface ATM {
    public void addBanknotes(int banknote, int quantity);
    public void issueRequestedAmount(int sum);
    public void showBalance();
    public int getBalance();
}
