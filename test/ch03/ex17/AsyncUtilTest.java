/*
 * Copyright (C) 2014 Yoshiki Shibata. All rights reserved.
 */
package ch03.ex17;

import js8ri.util.AsyncResult;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author yoshiki
 */
public class AsyncUtilTest {

    @Test(expected = NullPointerException.class)
    public void testFirstNull() {
        AsyncUtil.doInParallelAsync(null, null, null);
    }

    @Test(expected = NullPointerException.class)
    public void testSecondNull() {
        AsyncUtil.doInParallelAsync(() -> {
        }, null, null);
    }

    @Test(expected = NullPointerException.class)
    public void testHandlerNull() {
        AsyncUtil.doInParallelAsync(() -> {
        }, () -> {
        }, null);
    }

    public void testBothRunnableNormal() {
        AsyncResult asFirst = new AsyncResult();
        AsyncResult asSecond = new AsyncResult();

        AsyncUtil.doInParallelAsync(
                () -> asFirst.setResult(true),
                () -> asSecond.setResult(true),
                (e) -> {
                    throw new AssertionError("Not Possible");
                });

        assertTrue(asFirst.waitForResult());
        assertTrue(asSecond.waitForResult());
    }

    @Test
    public void testFirstThrowException() {
        AsyncResult asFirst = new AsyncResult();
        AsyncResult asSecond = new AsyncResult();

        AsyncUtil.doInParallelAsync(
                () -> {
                    throw new RuntimeException("ha ha");
                },
                () -> asSecond.setResult(true),
                (e) -> asFirst.setResult(true));

        assertTrue(asFirst.waitForResult());
        assertTrue(asSecond.waitForResult());
    }

    @Test
    public void testSecondThrowException() {
        AsyncResult asFirst = new AsyncResult();
        AsyncResult asSecond = new AsyncResult();

        AsyncUtil.doInParallelAsync(
                () -> asFirst.setResult(true),
                () -> {
                    throw new RuntimeException("ha ha");
                },
                (e) -> asSecond.setResult(true));

        assertTrue(asFirst.waitForResult());
        assertTrue(asSecond.waitForResult());
    }
}
