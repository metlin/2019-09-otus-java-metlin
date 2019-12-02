package ru.otus.homework5.test;

import ru.otus.homework5.classes.TestClass;
import ru.otus.homework5.classes.TestClass2;
import ru.otus.homework5.classes.TestClass3;
import ru.otus.homework5.classes.TestClass4;

public class Main {
    public static void main(String[] args) {
        new TestFramework().startTest(TestClass.class);
        new TestFramework().startTest(TestClass2.class);
        new TestFramework().startTest(TestClass3.class);
        new TestFramework().startTest(TestClass4.class);

        System.out.println("Total tests - " + TestCounter.testCounter);
        System.out.println("Test success - " + TestCounter.successCounter);
        System.out.println("Test failed - " + TestCounter.failedCounter);
    }
}
