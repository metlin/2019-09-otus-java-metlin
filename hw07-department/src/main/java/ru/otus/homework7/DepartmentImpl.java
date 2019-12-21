package ru.otus.homework7;

import ru.otus.homework7.atms.ATM;

import java.util.List;

public class DepartmentImpl implements Department {
    private List<ATM> atmList;

    public DepartmentImpl(List<ATM> atmList) {
        this.atmList = atmList;
    }

    @Override
    public int getSum() {
        int sum = 0;
        for (ATM atm : atmList) {
            sum += atm.getBalance();
        }

        return sum;
    }

    //Observer
    @Override
    public void initialState() {
       atmList.forEach(ATM::getInitialState);
    }

    @Override
    public void addSum(int sum) {
        atmList.forEach(atm -> atm.addSum(sum));
    }
}
