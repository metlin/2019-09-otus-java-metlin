package ru.otus.homework;

import ru.otus.homework.arraylist.DIYarrayList;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<String> stringList = new DIYarrayList<>();
        stringList.add("345");
        stringList.add(null);
        stringList.contains(null);
        stringList.remove(null);
        stringList.add("123");
        stringList.add("876");

        List<String> arrayList = new ArrayList<>();
        arrayList.add("5321");
        arrayList.add("456");
        arrayList.add("1488");

        Collections.addAll(arrayList, "11", "22", null, "44", "55","66","77","88","99","10","20","30","40","50","60","70","80",
                "90","1","2","3","4",null,"6","7","8");

        Collections.addAll(stringList, "1234", "2345", "3456", "4567", "5678","6789","7890","8901","9012","1000","2000","3000","4000","5000","6000","7000","8000",
                "90","101","202","303","404","505","606","707","808");

        Collections.copy(stringList, arrayList);
        Collections.sort(stringList, (s1, s2) -> {
            if (s1 == null) {
                return (s2 == null) ? 0 : -1;
            }

            if (s2 == null) {
                return 1;
            }

            if (s1.length() != s2.length()) {
                return s1.length() - s2.length();
            }

            return s1.compareTo(s2);
        });

        System.out.println(stringList + " size - " + stringList.size());

        List<Integer> integerList = new DIYarrayList<>();
        integerList.add(1);
        integerList.add(7);
        integerList.add(3);
        Collections.addAll(integerList, 3, 5, 54, 21, 87, 3, 876, 24, 11, 2, 87, 43, 111, 97, 54, 23, 5);

        List<Integer> integers = new ArrayList<>();
        Collections.addAll(integers, 23, 15, 254, 621, 687, null, 1876, 724, 711, 62, 587, 943, 2111, 297, 354, 323, 25, 452, 122, 253);
        Collections.copy(integerList, integers);

        Collections.sort(integerList, (o1, o2) -> {
            if (o1 == null) {
                return (o2 == null) ? 0 : -1;
            }

            if (o2 == null) {
                return 1;
            }

            return 0;
        });

        System.out.println(integerList + " size - " + stringList.size());
    }
}
