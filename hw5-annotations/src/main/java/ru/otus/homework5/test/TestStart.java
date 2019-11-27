package ru.otus.homework5.test;

import ru.otus.homework5.annotations.After;
import ru.otus.homework5.annotations.Before;
import ru.otus.homework5.annotations.Test;

import java.lang.reflect.Method;
import java.util.*;

public class TestStart {
    private Map<String, String> mapMethods = new HashMap<>();

    public void startTest(Class<?> clazz) {
        TestInterface testClass = (TestInterface)getInstance(clazz);
        Method[] methods =  clazz.getDeclaredMethods();

        for (Method method : methods) {
            if (testClass != null && method.isAnnotationPresent(Before.class)) {
                mapMethods.put("before", method.getName());
            }

            if(method.isAnnotationPresent(Test.class)) {
                mapMethods.put("test", method.getName());
            }

            if(testClass != null && method.isAnnotationPresent(After.class)) {
                mapMethods.put("after", method.getName());
            }
        }

        if (testClass != null && checkBeforeMethods() && checkTestMethods() && checkAfterMethods()) {
            testClass.beforeTest();
            testClass.testMethod();
            testClass.afterTest();
        }
    }

    private Object getInstance(Class<?> clazz) {
        Object ob = null;

        try {
            ob = clazz.getDeclaredConstructor().newInstance();
        } catch (Exception e) {
            e.getMessage();
        }

        return ob;
    }

    private boolean checkBeforeMethods() {
        return mapMethods.get("before") != null && !mapMethods.get("before").isEmpty();
    }

    private boolean checkTestMethods() {
        return mapMethods.get("test") != null && !mapMethods.get("test").isEmpty();
    }

    private boolean checkAfterMethods() {
        return mapMethods.get("after") != null && !mapMethods.get("after").isEmpty();
    }
}
