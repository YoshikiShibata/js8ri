/*
 * Copyright (C) 2014 Yoshiki Shibata. All rights reserved.
 */
package ch03.ex17;

import java.util.Arrays;
import java.util.Objects;
import java.util.function.Consumer;

/**
 * Implement a doInParallelAsync(Runnable first, Runnable second,
 * Consumer<Throwable>) method that executes first and second in parallel,
 * calling the handler if either method throws an exception.
 *
 * first とsecond を並列に実行し、どちらかのメソッドが例外をスローしたら handler
 * を呼び出すdoInParallelAsync(Runnable first, Runnable second, Consumer<Throwable>
 * handler) を実装しなさい。
 */
public class AsyncUtil {

    private AsyncUtil() {
        // Non-instantiable
    }

    /**
     * Executes two Runnables in parallel. When either of Runnables throws an
     * exception, handler will be invoked from a thread where the handler is
     * being executed.
     *
     * @param first a Runnable
     * @param second a Runnable
     * @param handler a handler to handle an exception
     * @throws NullPointerException if either first, second, or handler is null.
     */
    public static void doInParallelAsync(Runnable first, Runnable second,
            Consumer<Throwable> handler) {
        Objects.requireNonNull(first, "first is null");
        Objects.requireNonNull(second, "second is null");
        Objects.requireNonNull(handler, "handler is null");

        Thread t1 = new Thread(
                () -> {
                    try {
                        first.run();
                    } catch (Throwable e) {
                        handler.accept(e);
                    }
                }
        );

        Thread t2 = new Thread(
                () -> {
                    try {
                        second.run();
                    } catch (Throwable e) {
                        handler.accept(e);
                    }
                }
        );

        t1.start();
        t2.start();

    }

}
