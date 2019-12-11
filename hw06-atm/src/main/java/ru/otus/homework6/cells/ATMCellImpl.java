package ru.otus.homework6.cells;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class ATMCellImpl implements ATMCell {

    private int nominal;
    private List<Integer> banknotesList = new ArrayList<>();

    public ATMCellImpl(Nominal nominal) {
        this.nominal = nominal.getValue();
    }

    @Override
    public int getNominal() {
        return nominal;
    }

    @Override
    public int getCellSize() {
        return banknotesList.size();
    }

    @Override
    public void addBanknotes(int banknote, int quantity) {
        IntStream.range(0, quantity).forEach(i -> banknotesList.add(banknote));
    }

    @Override
    public List<Integer> getBanknotesList() {
        return banknotesList;
    }
}
