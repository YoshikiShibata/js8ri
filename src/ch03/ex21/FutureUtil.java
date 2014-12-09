/*
 * Copyright (C) 2014 Yoshiki Shibata. All rights reserved.
 */
package ch03.ex21;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.function.Function;

/**
 * Supply a static method <T, U> Future<U> map(Future<T>, Function<T, U>).
 * Return an object of an anonymous class that implements all methods of the
 * Future interface. In the get methods, invoke the function.
 *
 * static メソッドである<T, U> Future<U> map(Future<T>, Function<T,
 * U>) を提供しなさい。Future インタフェースのすべてのメソッドを実装した無名クラ スのオブジェクトを返しなさい。get
 * メソッドで、関数を呼び出しなさい。
 */
public class FutureUtil {

    private FutureUtil() {
        // non-instantiable
    }

    public static <T, U> Future<U> map(Future<T> future, Function<T, U> func) {
        return new Future<U>() {

            @Override
            public boolean cancel(boolean mayInterruptIfRunning) {
                return future.cancel(mayInterruptIfRunning);
            }

            @Override
            public boolean isCancelled() {
                return future.isCancelled();
            }

            @Override
            public boolean isDone() {
                return future.isDone();
            }

            @Override
            public U get() throws InterruptedException, ExecutionException {
                return func.apply(future.get());
            }

            @Override
            public U get(long timeout, TimeUnit unit)
                    throws InterruptedException, ExecutionException, TimeoutException {
                return func.apply(future.get(timeout, unit));
            }
        };
    }
}
