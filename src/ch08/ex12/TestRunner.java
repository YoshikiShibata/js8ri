/*
 * Copyright (C) 2015 Yoshiki Shibata. All rights reserved.
 */
package ch08.ex12;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Implement the TestCase annotation and a program that loads a class with such
 * annotations and invokes the annotated methods, checking whether they yield
 * the expected values. Assume that parameters and return values are integers.
 *
 * TestCase アノテーションを定義し、そのアノテーションを持つクラスをロードして、
 * アノテーションが付けられたメソッドを呼び出し、メソッドが期待した値を生成するかを
 * 検査するプログラムを実装しなさい。パラメータと戻り値は整数だと想定しなさい。
 */
public class TestRunner {

    public static void main(String[] args) {
        if (args.length == 1) {
            loadAndInvokeMethods(args[0]);
        } else {
            loadAndInvokeMethods("ch08.ex12.SampleAnnotatedClass");
        }
    }

    private static void loadAndInvokeMethods(String className) {
        Objects.requireNonNull(className);

        Class<?> clasz = loadClass(className);
        Object o = createInstance(clasz);
        List<Method> annotatedMethods = findAnnotatedMethods(clasz);

        int successTotal = 0;
        for (Method method : annotatedMethods) {
            successTotal += invokeMethod(clasz, method);
        }

        System.out.printf("%d method calls were success%n", successTotal);
    }

    private static Class<?> loadClass(String className) {
        try {
            return Class.forName(className);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(TestRunner.class.getName()).log(Level.SEVERE, null, ex);
            System.out.printf("Cannot load %s%n", className);
            System.exit(1);
        }
        throw new AssertionError("Impossible");
    }

    private static Object createInstance(Class<?> clasz) {
        try {
            return clasz.newInstance();
        } catch (InstantiationException | IllegalAccessException ex) {
            Logger.getLogger(TestRunner.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Cannot Instantiate");
            System.exit(1);
        }
        throw new AssertionError("Impossible");
    }

    private static List<Method> findAnnotatedMethods(Class<?> clasz) {
        Method[] methods = clasz.getMethods();
        List<Method> list = new ArrayList<>();

        for (Method method : methods) {
            Class<?>[] parameterTypes = method.getParameterTypes();
            if (parameterTypes.length != 1) {
                continue;
            }
            if (parameterTypes[0] != int.class) {
                continue;
            }

            Class<?> returnType = method.getReturnType();
            if (returnType != int.class) {
                continue;
            }

            if (method.getAnnotation(TestCase.class) == null
                    && method.getAnnotation(TestCases.class) == null) {
                continue;
            }

            list.add(method);
        }
        return list;

    }

    private static int invokeMethod(Class<?> clasz, Method method) {

        TestCase[] testCases = method.getAnnotationsByType(TestCase.class);

        int successCount = 0;
        try {
            for (TestCase tc : testCases) {
                Object o = clasz.newInstance();
                int result = (Integer) method.invoke(o, tc.param());
                if (result == tc.expected()) {
                    successCount++;
                } else {
                    System.out.printf("%d expected but %d returned%n", tc.expected(), result);
                }
            }

        } catch (Throwable e) {
            e.printStackTrace();
        }
        return successCount;
    }
}
