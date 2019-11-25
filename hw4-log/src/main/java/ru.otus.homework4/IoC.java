package ru.otus.homework4;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.List;

public class IoC {
    static Logging createMyClass() {
        InvocationHandler handler = new DemoInvocationHandler(new TestLogging());
        return (Logging) Proxy.newProxyInstance(IoC.class.getClassLoader(),
                new Class<?>[]{Logging.class}, handler);
    }

    static class DemoInvocationHandler implements InvocationHandler {
        private final Logging logging;
        private List<String> methodNames = new ArrayList<>();

        DemoInvocationHandler(Logging logging) {
            this.logging = logging;
            Method[] methods = this.logging.getClass().getDeclaredMethods();
            for (Method method : methods) {
                if (method.isAnnotationPresent(Log.class)) {
                    methodNames.add(method.getName());
                }
            }
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            if (methodNames.contains(method.getName())) {
                System.out.println("executed method:" + method.getName() + ", param:" + args[0]);
            }

            return method.invoke(logging, args);
        }
    }
}
