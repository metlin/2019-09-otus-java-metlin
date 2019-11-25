package ru.otus.homework4;

public class Demo {
    public static void main(String[] args) {
        Logging logging = IoC.createMyClass();
        logging.calculation(7);
        logging.getNumber(1);
        logging.getParam(5);
    }
}
