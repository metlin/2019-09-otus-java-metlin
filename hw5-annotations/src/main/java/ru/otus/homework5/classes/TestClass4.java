package ru.otus.homework5.classes;

import ru.otus.homework5.annotations.After;
import ru.otus.homework5.annotations.Before;
import ru.otus.homework5.annotations.Test;
import ru.otus.homework5.test.TestCounter;
import ru.otus.homework5.test.TestInterface;

public class TestClass4 implements TestInterface {

    private Integer number;

    @Before
    public void beforeTest() {
        number = 5;
    }

    // @Test
    public void testMethod() {
        if(number == 3) {
            TestCounter.successCounter++;
        } else {
            TestCounter.failedCounter++;
        }

        TestCounter.testCounter++;
    }

    @After
    public void afterTest() {
        number = 0;
    }
}
