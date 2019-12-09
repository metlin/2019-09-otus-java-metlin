package ru.otus.homework6.cells;

import ru.otus.homework6.cells.ATMCell;

import java.util.ArrayList;
import java.util.List;

public class ATMCellHundred implements ATMCell {

    private final int HUNDRED = 100;
    private List<Integer> banknotesHundred = new ArrayList<>();

    @Override
    public int getNominal() {
        return HUNDRED;
    }

    @Override
    public int getCellSize() {
        return banknotesHundred.size();
    }

    @Override
    public void addBanknotes(int banknote) {
        banknotesHundred.add(banknote);
    }

    @Override
    public List<Integer> getBanknotesList() {
        return banknotesHundred;
    }

}
