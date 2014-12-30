/*
 * Copyright (C) 2014 Yoshiki Shibata. All rights reserved.
 */
package ch06.ex03;

import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.LongAdder;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Generate 1,000 threads, each of which increments a counter 100,000 times.
 * Compare the performance of using AtomicLong versus LongAdder.
 *
 * 1,000 個のスレッドを生成し、各スレッドは、ある1 つのカウンターを100,000 回だけ 1 ずつ増加させます。AtomicLong
 * とLongAdder を使用した場合の性能を比較しな さい。
 */
public class AtomicLongVSLongAdder {

    private static final int NUMBER_OF_THREADS = 1_000;
    private static final int INCREMENT_COUNTER = 100_000;

    public static void main(String[] args) {
        AtomicLong atomicLong = new AtomicLong();
        Runnable r1 = () -> {
            for (int i = 0; i < INCREMENT_COUNTER; i++) {
                atomicLong.incrementAndGet();
            }
        };
        Runnable end1 = () -> {
            if (atomicLong.get() != NUMBER_OF_THREADS * INCREMENT_COUNTER) {
                throw new AssertionError("Incorrect result");
            }
        };

        LongAdder longAdder = new LongAdder();
        Runnable r2 = () -> {
            for (int i = 0; i < INCREMENT_COUNTER; i++) {
                longAdder.increment();
            }
        };
        Runnable end2 = () -> {
            if (longAdder.sum() != NUMBER_OF_THREADS * INCREMENT_COUNTER) {
                throw new AssertionError("Incorrect result");
            }
        };

        System.out.printf("AtomicLong: %f seconds%n", executeWithThreads(r1, end1));
        System.out.printf("LongAdder:  %f seconds%n", executeWithThreads(r2, end2));
    }

    public static double executeWithThreads(Runnable r, Runnable end) {
        Thread[] threads = new Thread[NUMBER_OF_THREADS];
        for (int i = 0; i < NUMBER_OF_THREADS; i++) {
            threads[i] = new Thread(r);
        }

        long startTime = System.nanoTime();
        for (Thread thread : threads) {
            thread.start();
        }

        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException ex) {
                Logger.getLogger(AtomicLongVSLongAdder.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        end.run();
        long endTime = System.nanoTime();
        return (double)(endTime - startTime) / 1_000_000_000;
    }

}
