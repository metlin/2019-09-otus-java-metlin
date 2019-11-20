package ru.otus.homework4;

import java.lang.reflect.Method;

public class TestLogging {

    private int param;
    private String methodName;
    private final String EMPTY_STRING = "";

    @Log
    public int calculation(int param) {
        this.param = param;
        action();
        return param;
    }

    public int getNumber(int number) {
        return number;
    }

    private void action() {
        try {
            methodName = getLogMethod(TestLogging.class.getMethod("calculation", int.class));
        } catch (NoSuchMethodException e) {
            e.getMessage();
        }

        if (!methodName.equals(EMPTY_STRING)) {
            System.out.println("executed method: " + methodName + " ,param: " + param);
        }
    }

    private String getLogMethod(Method method) {
        return method.isAnnotationPresent(Log.class) ? method.getName() : EMPTY_STRING;
    }
}
