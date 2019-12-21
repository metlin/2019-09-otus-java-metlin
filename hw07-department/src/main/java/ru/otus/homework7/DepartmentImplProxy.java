package ru.otus.homework7;

//Proxy
public class DepartmentImplProxy implements Department {

    private DepartmentImpl departmentImpl;

    public DepartmentImplProxy(DepartmentImpl departmentImpl) {
        this.departmentImpl = departmentImpl;
    }

    @Override
    public int getSum() {
        System.out.print(" total sum - ");
        return departmentImpl.getSum();
    }

    @Override
    public void initialState() {
        System.out.print("Восстановлено начальное состояние - ");
        departmentImpl.initialState();
    }

    @Override
    public void addSum(int sum) {
        System.out.print("Во все банкоматы добавлена сумма - " + sum);
        departmentImpl.addSum(sum);
    }
}
