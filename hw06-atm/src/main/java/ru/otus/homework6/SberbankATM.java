package ru.otus.homework6;

import ru.otus.homework6.cells.*;
import java.util.*;

public class SberbankATM implements ATM {

    private Integer balance = 0;
    private static int sumBanknotes;

    private Map<Nominal, ATMCell> atmCellMap;

    public SberbankATM() {
        atmCellMap =  new HashMap<>();
        for (Nominal nominal : Nominal.values()) {
            atmCellMap.put(nominal, new ATMCellImpl(nominal));
        }
    }

    @Override
    public void addBanknotes(int banknote, int quantity) {
        for (Map.Entry<Nominal, ATMCell> entry : atmCellMap.entrySet()) {
            if(banknote == entry.getKey().getValue()) {
                entry.getValue().addBanknotes(banknote, quantity);
            }
        }

        setBalance(atmCellMap);
    }

    @Override
    public void issueRequestedAmount(int sum) {
        if (sum > balance) {
            System.out.println("В банкомате нет столько денег, введите, плиз, сумму поменьше");
            return;
        }

        if (checkBanknotes(sum)) {
            List list = ATMCellHelper.sortedMap(atmCellMap);
            sumBanknotes = 0;
            for (Object o : list) {
                sum -= sumBanknotes;
                Map.Entry<Nominal, ATMCell> entry = (Map.Entry<Nominal, ATMCell>) o;
                int numberOfBanknotes = sum / entry.getKey().getValue();
                boolean containsBanknotes = ATMCellHelper.checkBanknotes(entry.getValue().getBanknotesList(), numberOfBanknotes);
                if(numberOfBanknotes != 0 && containsBanknotes) {
                    ATMCellHelper.removeElementsFromCollection(entry.getValue().getBanknotesList(), numberOfBanknotes);
                    sumBanknotes = numberOfBanknotes * entry.getKey().getValue();
                }

                if(numberOfBanknotes != 0 && !containsBanknotes && !entry.getValue().getBanknotesList().isEmpty()) {
                    int cellSize = entry.getValue().getCellSize();
                    ATMCellHelper.removeElementsFromCollection(entry.getValue().getBanknotesList(), cellSize);
                    sumBanknotes = cellSize * entry.getKey().getValue();
                }

            }

        } else {
            System.out.println("Невозможно выдать такую сумму, введите другую");
        }
    }

    @Override
    public void showBalance() {
        setBalance(atmCellMap);
        System.out.println("Balance - " + balance);
        ATMCellHelper.showQuantityOfBanknotes(atmCellMap);
        System.out.println();
    }

    private boolean checkBanknotes(int sum) {
        List list = ATMCellHelper.sortedMap(atmCellMap);
        for (Object o : list) {
            Map.Entry<Nominal, ATMCell> entry = (Map.Entry<Nominal, ATMCell>) o;
            int numberOfBanknotes = sum / entry.getKey().getValue();
            boolean containsBanknotes = ATMCellHelper.checkBanknotes(entry.getValue().getBanknotesList(), numberOfBanknotes);
            if (containsBanknotes) {
                sum -= numberOfBanknotes * entry.getKey().getValue();
            }
        }

        return sum == 0;
    }

    @Override
    public int getBalance() {
        setBalance(atmCellMap);
        return balance;
    }

    private void setBalance(Map<Nominal, ATMCell> atmCellMap) {
        balance = 0;
        for (Map.Entry<Nominal, ATMCell> entry : atmCellMap.entrySet()) {
            balance += ATMCellHelper.sumBanknotes(entry.getValue().getBanknotesList());
        }
    }
}
