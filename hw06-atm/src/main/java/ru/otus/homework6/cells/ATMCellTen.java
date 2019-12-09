package ru.otus.homework6.cells;

import java.util.ArrayList;
import java.util.List;

public class ATMCellTen implements ATMCell {

    private final int TEN = 10;
    private List<Integer> banknotesTen = new ArrayList<>();

    @Override
    public int getNominal() {
        return TEN;
    }

    @Override
    public int getCellSize() {
        return banknotesTen.size();
    }

    @Override
    public void addBanknotes(int banknote) {
        banknotesTen.add(banknote);
    }

    @Override
    public List<Integer> getBanknotesList() {
        return banknotesTen;
    }
}
