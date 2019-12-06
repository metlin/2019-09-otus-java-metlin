package ru.otus.homework6;

public interface ATM {
    public void addBanknotes(int...banknotes);
    public void issueRequestedAmount(int sum);
    public void showBalance();
    public int getBalance();
}
