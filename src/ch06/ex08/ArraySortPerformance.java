/*
 * Copyright (C) 2015 Yoshiki Shibata. All rights reserved.
 */
package ch06.ex08;

import java.time.Instant;
import java.util.Arrays;
import java.util.Random;

/**
 * How large does an array have to be for Arrays.parallelSort to be faster than
 * Arrays.sort on your computer?
 *
 * みなさんのコンピュータでは、Arrays.parallelSort は、配列がどのくらいの大き さであればArrays.sort より速くなりますか。
 */
public final class ArraySortPerformance {

    private static final int LOOP_COUNT = 10;

    public static void main(String[] args) {
        long seed = Instant.now().toEpochMilli();

        for (int size = 1000; size < 20000; size += 1000) {
            System.out.printf("Size of Array: %d%n", size);
            System.out.printf("sort(Integer)         %d%n",
                    sortInteger(size, seed, Arrays::sort));
            System.out.printf("parallelSort(Integer) %d%n",
                    sortInteger(size, seed, Arrays::parallelSort));
            System.out.printf("sort(int)         %d%n",
                    sortInt(size, seed, Arrays::sort));
            System.out.printf("parallelSort(int) %d%n",
                    sortInt(size, seed, Arrays::parallelSort));
        }
    }

    @FunctionalInterface
    interface IntegerSorter {

        void sort(Integer[] array);
    }

    @FunctionalInterface
    interface IntSorter {

        void sort(int[] array);
    }

    private static long sortInteger(int arraySize, long seed, IntegerSorter sorter) {
        Integer[] array = createIntegerArray(arraySize, seed);
        long[] performances = new long[LOOP_COUNT];

        for (int i = 0; i < LOOP_COUNT; i++) {
            Integer[] clonedArray = array.clone();

            long startTime = System.nanoTime();
            sorter.sort(clonedArray);
            performances[i] = System.nanoTime() - startTime;
        }

        return average(performances);
    }

    private static long sortInt(int arraySize, long seed, IntSorter sorter) {
        int[] array = createIntArray(arraySize, seed);
        long[] performances = new long[LOOP_COUNT];

        for (int i = 0; i < LOOP_COUNT; i++) {
            int[] clonedArray = array.clone();

            long startTime = System.nanoTime();
            sorter.sort(clonedArray);
            performances[i] = System.nanoTime() - startTime;
        }

        return average(performances);
    }

    private static long average(long[] performances) {
        long result = 0;
        for (int i = 0; i < performances.length; i++) {
            result += performances[i];
        }
        return result / performances.length;
    }

    private static Integer[] createIntegerArray(int arraySize, long seed) {
        Integer[] array = new Integer[arraySize];
        Random random = new Random(seed);

        for (int i = 0; i < arraySize; i++) {
            array[i] = random.nextInt();
        }

        return array;
    }

    private static int[] createIntArray(int arraySize, long seed) {
        int[] array = new int[arraySize];
        Random random = new Random(seed);

        for (int i = 0; i < arraySize; i++) {
            array[i] = random.nextInt();
        }

        return array;
    }

}
