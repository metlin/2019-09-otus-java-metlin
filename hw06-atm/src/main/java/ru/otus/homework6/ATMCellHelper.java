package ru.otus.homework6;

import java.util.List;

public class ATMCellHelper {

    public static boolean checkBanknotes(List<Integer> list, int numberOfBanknotes) {
        return list.size() >= numberOfBanknotes && numberOfBanknotes != 0;
    }

    public static void removeElementsFromCollection(List<Integer> list, int elements) {
        for(int i = 0; i < elements; i++) {
            list.remove(list.size() - 1);
        }
    }

    public static Integer sumBanknotes(List<Integer> list) {
        int sum = 0;
        for(Integer integer : list) {
            sum  += integer;
        }

        return sum;
    }
}
