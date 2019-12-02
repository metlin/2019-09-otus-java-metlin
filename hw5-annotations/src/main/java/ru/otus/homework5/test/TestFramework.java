package ru.otus.homework5.test;

import ru.otus.homework5.annotations.After;
import ru.otus.homework5.annotations.Before;
import ru.otus.homework5.annotations.Test;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

public class TestFramework {
    public void startTest(Class<?> clazz) {
        List<Method> beforeMethods = getMethods(clazz, Before.class);
        List<Method> testMethods = getMethods(clazz, Test.class);
        List<Method> afterMethods = getMethods(clazz, After.class);

        for(Method method : testMethods) {
            Object testClass = getInstance(clazz);
            try {
                invokeMethods(testClass, beforeMethods);
                method.invoke(testClass);
            } catch (IllegalAccessException | InvocationTargetException e) {
                e.getCause().getMessage();
            } finally {
                invokeMethods(testClass, afterMethods);
            }
        }
    }

    private void invokeMethods(Object clazz, List<Method> methods) {
        for(Method method : methods) {
            try {
                method.invoke(clazz);
            } catch (IllegalAccessException | InvocationTargetException e) {
                e.printStackTrace();
            }
        }
    }

    private List<Method> getMethods(Class<?> clazz, Class<? extends Annotation> annotation) {
        List<Method> list = new ArrayList<>();
        Method[] methods = clazz.getDeclaredMethods();
        for(Method method : methods) {
            if(method.isAnnotationPresent(annotation)) {
               list.add(method);
            }
        }

        return list;
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
}
