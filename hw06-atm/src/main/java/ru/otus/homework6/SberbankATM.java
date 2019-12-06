package ru.otus.homework6;

import java.util.ArrayList;
import java.util.List;

public class SberbankATM implements ATM {

    private Integer balance = 0;
    private List<Integer> banknotesTen = new ArrayList<>();
    private List<Integer> banknotesFifty = new ArrayList<>();
    private List<Integer> banknotesHundred = new ArrayList<>();
    private final int TEN = 10;
    private final int FIFTY = 50;
    private final int HUNDRED = 100;

    private static int countHundred;
    private static int countFifty;
    private static int countTen;

    @Override
    public void addBanknotes(int...banknotes) {
        for (int banknote : banknotes) {
            if (banknote == TEN) {
                banknotesTen.add(banknote);
            }

            if (banknote == FIFTY) {
                banknotesFifty.add(banknote);
            }

            if (banknote == HUNDRED) {
                banknotesHundred.add(banknote);
            }
        }

        setBalance();
    }

    @Override
    public void issueRequestedAmount(int sum) {
        if (sum > balance) {
            System.out.println("В банкомате нет столько денег, введите, плиз, сумму поменьше");
            return;
        }

        int numberOfHundred = sum/HUNDRED;
        if(numberOfHundred != 0) {
            boolean containsHundred = checkBanknotes(banknotesHundred, numberOfHundred);
            int numberOfBanknotes = banknotesHundred.size();
            if(!containsHundred && numberOfBanknotes != 0) {
                countHundred = numberOfBanknotes;
            } else {
                if(containsHundred) {
                    countHundred = numberOfHundred;
                } else {
                    int numberOfFifty = sum/FIFTY;
                    boolean containsFifty =  checkBanknotes(banknotesFifty, numberOfFifty);
                    numberOfBanknotes = banknotesFifty.size();

                    //TODO: if ok, change the duplicate code to a method
                    if(!containsFifty && numberOfBanknotes != 0) {
                        countFifty = numberOfBanknotes;
                    } else {
                        if(containsFifty) {
                            countFifty = numberOfFifty;
                        } else {
                            int numberOfTen = sum / TEN;
                            boolean containsTen = checkBanknotes(banknotesTen, numberOfTen);
                            int numberOfBanknotes1 = banknotesTen.size();
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

        sum = sum - countHundred * HUNDRED;
        int numberOfFifty = sum/FIFTY;
        if(sum != 0 && numberOfFifty != 0) {
            boolean containsFifty =  checkBanknotes(banknotesFifty, numberOfFifty);
            int numberOfBanknotes = banknotesFifty.size();
            if(!containsFifty && numberOfBanknotes != 0) {
                countFifty = numberOfBanknotes;
            } else {
                if(containsFifty) {
                    countFifty = numberOfFifty;
                } else {
                    int numberOfTen = sum / TEN;
                    boolean containsTen = checkBanknotes(banknotesTen, numberOfTen);
                    int numberOfBanknotes1 = banknotesTen.size();
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

        sum = sum - countFifty * FIFTY;
        int numberOfTen = sum / TEN;
        if(sum != 0 && numberOfTen != 0) {
            boolean containsTen = checkBanknotes(banknotesTen, numberOfTen);
            int numberOfBanknotes = banknotesHundred.size();
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

        removeElementsFromCollection(banknotesHundred, countHundred);
        removeElementsFromCollection(banknotesFifty, countFifty);
        removeElementsFromCollection(banknotesTen, countTen);
        countHundred = 0;
        countFifty = 0;
        countTen = 0;
        setBalance();
    }

    private boolean checkBanknotes(List<Integer> list, int numberOfBanknotes) {
        return list.size() >= numberOfBanknotes && numberOfBanknotes != 0;
    }

    private void removeElementsFromCollection(List<Integer> list, int elements) {
        for(int i = 0; i < elements; i++) {
            list.remove(list.size() - 1);
        }
    }

    @Override
    public void showBalance() {
        setBalance();
        System.out.println("Balance - " + balance);
        System.out.println("ten - " + banknotesTen.size() + " fifty - " + banknotesFifty.size() + " hundred - " + banknotesHundred.size());
        System.out.println();
    }

    @Override
    public int getBalance() {
        return balance;
    }

    private void setBalance() {
        balance = sumBanknotes(banknotesTen) + sumBanknotes(banknotesFifty) + sumBanknotes(banknotesHundred);
    }

    private Integer sumBanknotes(List<Integer> list) {
        int sum = 0;
        for(Integer integer : list) {
            sum = sum + integer;
        }

        return sum;
    }
}
