/*
 * Copyright (C) 2014 Yoshiki Shibata. All rights reserved.
 */
package ch03.ex16;

import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Supplier;

/**
 * Implement the doInOrderAsync of Section 3.8, “Dealing with Exceptions,” on
 * page 58, where the second parameter is a BiConsumer<T, Throwable>. Provide a
 * plausible use case. Do you still need the third parameter?
 *
 * 71 ページの3.8 節「例外の取り扱い」のdoInOrderAsync を実装し、2 つ目のパラメー
 * タはBiConsumer<T, Throwable>としなさい。うまいユースケースを示しなさい。 3 つ目のパラメータは必要ですか。
 */
public class AsyncUtil {

    private AsyncUtil() {
        // Non-instantiable
    }
    
    /**
     * Executes supplier and consumer in order. The result of supplier will
     * be provided to the consumer. If the supplier throws an exception, the
     * exception will be passe to the consumer as the second parameter: in this
     * case, the first parameter will be null. If the consumer will throws
     * an Exception, then handler will be invoked.
     * 
     * @param <T> type varaible 
     * @param supplier Supplier
     * @param consumer Consumer
     * @param handler Handle to be invoked when the consumer will throw an exception.
     */
    public static <T> void doInOrderAsync(
            Supplier<T> supplier, BiConsumer<T, Throwable> consumer,
            Consumer<Throwable> handler) {
        Thread t = new Thread(
            () -> {

                T result = null;
                Throwable firstFailure = null;

                try {
                    result = supplier.get();
                } catch (Throwable e) {
                    firstFailure = e;
                    try {
                        consumer.accept(null, e);
                    } catch (Throwable e2) {
                        handler.accept(e2);
                    }
                }

                if (firstFailure == null) {
                    try {
                        consumer.accept(result, null);
                    } catch (Throwable e) {
                        handler.accept(e);
                    }
                }
            }
        );
        t.start();
    }

}
