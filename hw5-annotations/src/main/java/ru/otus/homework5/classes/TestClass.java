package ru.otus.homework5.classes;

import ru.otus.homework5.annotations.After;
import ru.otus.homework5.annotations.Before;
import ru.otus.homework5.annotations.Test;
import ru.otus.homework5.test.TestCounter;
import ru.otus.homework5.test.TestInterface;

public class TestClass implements TestInterface {

    private String string;

    @Before
    public void beforeTest() {
        string = "123";
    }

    @Test
    public void testMethod() {
        if(string.equals("123")) {
            TestCounter.successCounter++;
        } else {
            TestCounter.failedCounter++;
        }

        TestCounter.testCounter++;
    }

    @After
    public void afterTest() {
        string = "";
    }
}
