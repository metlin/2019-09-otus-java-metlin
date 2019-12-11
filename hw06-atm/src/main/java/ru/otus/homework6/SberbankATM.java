package ru.otus.homework6;

import ru.otus.homework6.cells.*;

public class SberbankATM implements ATM {

    private Integer balance = 0;
    private static int countHundred;
    private static int countFifty;
    private static int countTen;

    private ATMCell atmCellTen;
    private ATMCell atmCellFifty;
    private ATMCell atmCellHundred;

    public SberbankATM(Nominal ten, Nominal fifty, Nominal hundred) {
       atmCellTen = new ATMCellImpl(ten);
       atmCellFifty = new ATMCellImpl(fifty);
       atmCellHundred = new ATMCellImpl(hundred);
    }

    @Override
    public void addBanknotes(int banknote, int quantity) {
        if (banknote == Nominal.TEN.getValue()) {
            atmCellTen.addBanknotes(banknote, quantity);
        }

        if (banknote == Nominal.FIFTY.getValue()) {
            atmCellFifty.addBanknotes(banknote, quantity);
        }

        if (banknote == Nominal.HUNDRED.getValue()) {
            atmCellHundred.addBanknotes(banknote, quantity);
        }

        setBalance();
    }

    @Override
    public void issueRequestedAmount(int sum) {
        if (sum > balance) {
            System.out.println("В банкомате нет столько денег, введите, плиз, сумму поменьше");
            return;
        }

        int numberOfHundred = sum/atmCellHundred.getNominal();
        if(numberOfHundred != 0) {
            boolean containsHundred = ATMCellHelper.checkBanknotes(atmCellHundred.getBanknotesList(), numberOfHundred);
            int numberOfBanknotes = atmCellHundred.getCellSize();
            if(!containsHundred && numberOfBanknotes != 0) {
                countHundred = numberOfBanknotes;
            } else {
                if(containsHundred) {
                    countHundred = numberOfHundred;
                } else {
                    int numberOfFifty = sum/atmCellFifty.getNominal();
                    boolean containsFifty =  ATMCellHelper.checkBanknotes(atmCellFifty.getBanknotesList(), numberOfFifty);
                    numberOfBanknotes = atmCellFifty.getCellSize();

                    //TODO: if ok, change the duplicate code to a method
                    if(!containsFifty && numberOfBanknotes != 0) {
                        countFifty = numberOfBanknotes;
                    } else {
                        if(containsFifty) {
                            countFifty = numberOfFifty;
                        } else {
                            int numberOfTen = sum / atmCellTen.getNominal();
                            boolean containsTen = ATMCellHelper.checkBanknotes(atmCellTen.getBanknotesList(), numberOfTen);
                            int numberOfBanknotes1 = atmCellTen.getCellSize();
                            if(!containsTen && numberOfBanknotes1 != 0) {
                                countTen = numberOfBanknotes;
                            }
                            if (containsTen) {
                                countTen = numberOfTen;
                            } else {
                                System.out.println("1 - Невозможно выдать такую сумму, введите другую и попробуйте угадать какие купюры есть в банкомате");
                                System.out.println();
                            }
                        }
                    }
                }
            }
        }

        sum = sum - countHundred * atmCellHundred.getNominal();
        int numberOfFifty = sum/atmCellFifty.getNominal();
        if(sum != 0 && numberOfFifty != 0) {
            boolean containsFifty =  ATMCellHelper.checkBanknotes(atmCellFifty.getBanknotesList(), numberOfFifty);
            int numberOfBanknotes = atmCellFifty.getCellSize();
            if(!containsFifty && numberOfBanknotes != 0) {
                countFifty = numberOfBanknotes;
            } else {
                if(containsFifty) {
                    countFifty = numberOfFifty;
                } else {
                    int numberOfTen = sum / atmCellTen.getNominal();
                    boolean containsTen = ATMCellHelper.checkBanknotes(atmCellTen.getBanknotesList(), numberOfTen);
                    int numberOfBanknotes1 = atmCellTen.getCellSize();
                    if(!containsTen && numberOfBanknotes1 != 0) {
                        countTen = numberOfBanknotes;
                    }
                    if (containsTen) {
                        countTen = numberOfTen;
                    } else {
                        System.out.println("1 - Невозможно выдать такую сумму, введите другую и попробуйте угадать какие купюры есть в банкомате");
                        System.out.println();
                    }
                }
            }
        }

        sum = sum - countFifty * atmCellFifty.getNominal();
        int numberOfTen = sum / atmCellTen.getNominal();
        if(sum != 0 && numberOfTen != 0) {
            boolean containsTen = ATMCellHelper.checkBanknotes(atmCellTen.getBanknotesList(), numberOfTen);
            int numberOfBanknotes = atmCellHundred.getCellSize();
            if(!containsTen && numberOfBanknotes != 0) {
                countTen = numberOfBanknotes;
            } else {
                if (containsTen) {
                    countTen = numberOfTen;
                } else {
                    System.out.println("Невозможно выдать такую сумму, введите другую и попробуйте угадать какие купюры есть в банкомате");
                    System.out.println();
                    return;
                }
            }
        }

        ATMCellHelper.removeElementsFromCollection(atmCellHundred.getBanknotesList(), countHundred);
        ATMCellHelper.removeElementsFromCollection(atmCellFifty.getBanknotesList(), countFifty);
        ATMCellHelper.removeElementsFromCollection(atmCellTen.getBanknotesList(), countTen);
        countHundred = 0;
        countFifty = 0;
        countTen = 0;
        setBalance();
    }

    @Override
    public void showBalance() {
        setBalance();
        System.out.println("Balance - " + balance);
        System.out.println("ten - " + atmCellTen.getCellSize() + " fifty - " + atmCellFifty.getCellSize() + " hundred - " + atmCellHundred.getCellSize());
        System.out.println();
    }

    @Override
    public int getBalance() {
        return balance;
    }

    private void setBalance() {
        balance = ATMCellHelper.sumBanknotes(atmCellTen.getBanknotesList())
                + ATMCellHelper.sumBanknotes(atmCellFifty.getBanknotesList())
                + ATMCellHelper.sumBanknotes(atmCellHundred.getBanknotesList());
    }
}
