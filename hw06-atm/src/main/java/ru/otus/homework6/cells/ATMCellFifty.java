package ru.otus.homework6.cells;

import ru.otus.homework6.cells.ATMCell;

import java.util.ArrayList;
import java.util.List;

public class ATMCellFifty implements ATMCell {

    private final int FIFTY = 50;
    private List<Integer> banknotesFifty = new ArrayList<>();

    @Override
    public int getNominal() {
        return FIFTY;
    }

    @Override
    public int getCellSize() {
        return banknotesFifty.size();
    }

    @Override
    public void addBanknotes(int banknote) {
        banknotesFifty.add(banknote);
    }

    @Override
    public List<Integer> getBanknotesList() {
        return banknotesFifty;
    }
}
