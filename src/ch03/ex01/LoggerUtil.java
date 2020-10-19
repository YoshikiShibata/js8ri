/*
 * Copyright (C) 2014, 2020 Yoshiki Shibata. All rights reserved.
 */
package ch03.ex01;

import java.util.Objects;
import java.util.function.Supplier;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Enhance the lazy logging technique by providing conditional logging. A
 * typical call would be logIf(Level.FINEST, () -> i == 10, () -> "a[10] = " +
 * a[10]). Don’t evaluate the condition if the logger won’t log the message.
 *
 * 条件的なロギングを提供することで、遅延ロギング技法を強化しなさい。典型的な 呼び出しは、logIf(Level.FINEST, () -> i ==
 * 10, () -> "a[10] = " + a[10]) となります。ロガーがメッセージをロギングしないのであれば、その条件を評価
 * しないようにしなさい。
 */
public final class LoggerUtil {

    private final Logger logger;

    /**
     * Constructs an instance of this class.
     *
     * @param logger a Logger instance to be used with this class
     * @throws NullPointerException if logger is null
     */
    public LoggerUtil(Logger logger) {
		this.logger = Objects.requireNonNull(logger, "logger is null");	
    }

    /**
     * Represents a predicate (boolean-valued function) of no argument. This is
     * the no-argument specialization of Predicate.
     */
    @FunctionalInterface
    public interface VoidPredicate {

        /**
         * Evaluates this predicate.
         *
         * @return true if this predicate holds, otherwise, false.
         */
        boolean test();
    }

    /**
     * Log a message, which is only to be constructed if the logging level is
     * such that the message will actually be logged and only if the predicate
     * holds true. If the logger is currently enabled for the given message
     * level and the predicate holds true, then the message is constructed by
     * invoking the provided supplier function and forwarded to all the
     * registered output Handler objects.
     *
     * @param level One of the message level identifiers, e.g., SEVERE
     * @param predicate A function, which detemines whether the desired log
     * message should be logged.
     * @param msgSupplier A function, which when called, produces the desired
     * log message
     * @throws NullPointerException if either leve, predicate, or msgSupplier is
     * null.
     */
    void logIf(Level level,
            VoidPredicate predicate,
            Supplier<String> msgSupplier) {
        Objects.requireNonNull(level, "level is null");
        Objects.requireNonNull(predicate, "predicate is null");
        Objects.requireNonNull(msgSupplier, "predicate is null");

        if (!logger.isLoggable(level)) {
            return;
        }

        if (!predicate.test()) {
            return;
        }

        logger.log(level, msgSupplier.get());
    }
}
