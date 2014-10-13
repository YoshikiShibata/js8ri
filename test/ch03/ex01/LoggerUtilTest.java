/*
 * Copyright (C) 2014 Yoshiki Shibata. All rights reserved.
 */
package ch03.ex01;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Test code for LoggerUtil
 */
public class LoggerUtilTest {

    static private final Level levels[] = {
        Level.ALL,
        Level.CONFIG,
        Level.FINE,
        Level.FINER,
        Level.FINEST,
        Level.INFO,
        // Level.OFF, // This level is not used for log
        Level.SEVERE,
        Level.WARNING
    };

    private final Logger logger;

    public LoggerUtilTest() {
        logger = Logger.getGlobal();
    }

    @Test(expected = NullPointerException.class)
    public void testNullPointerExceptionWithConstructor() {
        new LoggerUtil(null);
    }

    @Test(expected = NullPointerException.class)
    public void testNullPointerExceptionWithLevel() {
        LoggerUtil lu = new LoggerUtil(logger);
        lu.logIf(null, null, null);
    }

    @Test(expected = NullPointerException.class)
    public void testNullPointerExceptionWithPredicate() {
        LoggerUtil lu = new LoggerUtil(logger);
        lu.logIf(Level.INFO, null, null);
    }

    @Test(expected = NullPointerException.class)
    public void testNullPointerExceptionWithMsgSupplier() {
        LoggerUtil lu = new LoggerUtil(logger);
        lu.logIf(Level.INFO, () -> true, null);
    }

    @Test
    public void testLogIf() {
        // Prepare
        boolean[] called = new boolean[2]; // 0: predicate, 1: msgSupplier
        LoggerUtil lu = new LoggerUtil(logger);

        validateWithPredicateResult(called, lu, true);
        validateWithPredicateResult(called, lu, false);
    }

    private void validateWithPredicateResult(
            boolean[] called, LoggerUtil lu, boolean predicateResult) {
        for (Level level : levels) {
            // Prepare
            called[0] = false;
            called[1] = false;

            // Action
            lu.logIf(level,
                    () -> {
                        called[0] = true;
                        return predicateResult;
                    },
                    () -> {
                        called[1] = true;
                        return "hello";
                    });

            // Check
            boolean loggable = logger.isLoggable(level);
            if (loggable) {
                assertTrue(called[0]);
                if (predicateResult) {
                    assertTrue(called[1]);
                } else {
                    assertTrue(!called[1]);
                }
                continue;

            }
            assertTrue(!called[0]);
            assertTrue(!called[1]);
        }
    }
}
