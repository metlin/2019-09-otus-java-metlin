package ru.otus.homework5.test;

import ru.otus.homework5.classes.TestClass;
import ru.otus.homework5.classes.TestClass2;
import ru.otus.homework5.classes.TestClass3;
import ru.otus.homework5.classes.TestClass4;

public class Main {
    public static void main(String[] args) {
        new TestStart().startTest(TestClass.class);
        new TestStart().startTest(TestClass2.class);
        new TestStart().startTest(TestClass3.class);
        new TestStart().startTest(TestClass4.class);

        System.out.println("Total tests - " + TestCounter.testCounter);
        System.out.println("Test success - " + TestCounter.successCounter);
        System.out.println("Test failed - " + TestCounter.failedCounter);
    }
}
