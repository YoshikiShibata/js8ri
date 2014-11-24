/*
 * Copyright (C) 2014 Yoshiki Shibata. All rights reserved.
 */
package ch03.ex16;

import js8ri.util.AsyncResult;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Test code for AsyncUtil class
 */
public class AsyncUtilTest {

    @Test
    public void bothOks() {
        AsyncResult as = new AsyncResult();

        AsyncUtil.doInOrderAsync(
                () -> "Hello", (t, e) -> {
                    if (e != null) {
                        as.setResult(false);
                        return;
                    }

                    as.setResult(t.equals("Hello"));
                }, null);

        assertTrue(as.waitForResult());
    }

    @Test
    public void firstException() {
        AsyncResult as = new AsyncResult();

        AsyncUtil.doInOrderAsync(
                () -> {
                    throw new RuntimeException("ha ha");
                },
                (t, e) -> {
                    if (t != null) {
                        as.setResult(false);
                        return;
                    }
                    as.setResult(e != null);
                }, null
        );

        assertTrue(as.waitForResult());
    }

    @Test
    public void secondException() {
        AsyncResult as = new AsyncResult();

        AsyncUtil.doInOrderAsync(
                () -> "hello",
                (t, e) -> {
                    throw new RuntimeException("ha ha");
                },
                (e) -> {
                    as.setResult(e != null);
                });
        
        assertTrue(as.waitForResult());
    }

    @Test
    public void firstExceptionThenSecondException() {
        AsyncResult as = new AsyncResult();
        
        AsyncUtil.doInOrderAsync(
                () -> { throw new RuntimeException("ha ha"); }, 
                (t, e) -> {
                    if (e == null) {
                        as.setResult(false);
                        return;
                    }
                    throw new RuntimeException("ha ha");
                }, 
                (e) -> {
                    as.setResult(e != null);
                });
        
        assertTrue(as.waitForResult());
    }
}
