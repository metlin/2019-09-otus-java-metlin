package ru.otus.homework6;

import ru.otus.homework6.cells.ATMCell;
import ru.otus.homework6.cells.Nominal;

import java.util.*;

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

    public static List sortedMap(Map<Nominal, ATMCell> atmCellMap) {
        List list = new ArrayList(atmCellMap.entrySet());
        Collections.sort(list, (Comparator<Map.Entry<Nominal, ATMCell>>) (a, b) -> b.getValue().getNominal() - a.getValue().getNominal());
        return list;
    }

    public static void showQuantityOfBanknotes(Map<Nominal, ATMCell> atmCellMap) {
        for (Map.Entry<Nominal, ATMCell> entry : atmCellMap.entrySet()) {
            System.out.println(entry.getKey() + " - " + entry.getValue().getCellSize());
        }
    }
}
