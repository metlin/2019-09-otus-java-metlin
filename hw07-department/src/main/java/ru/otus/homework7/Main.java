package ru.otus.homework7;

import ru.otus.homework7.atms.ATMHelper;

public class Main {
    public static void main(String[] args) {
        Department department = new DepartmentImplProxy(new DepartmentImpl(ATMHelper.getBankomats()));
        System.out.println(department.getSum());
        department.initialState();
        System.out.println(department.getSum());
        department.addSum(500);
        System.out.println(department.getSum());
    }
}
