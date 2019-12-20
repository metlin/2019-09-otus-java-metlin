package ru.otus.homework6.cells;

import java.util.List;

public interface ATMCell {
    public int getNominal();
    public int getCellSize();
    public void addBanknotes(int banknote, int quantity);
    public List<Integer> getBanknotesList();
}
