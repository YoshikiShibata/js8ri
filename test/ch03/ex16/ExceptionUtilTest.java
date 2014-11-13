/*
 * Copyright (C) 2014 Yoshiki Shibata. All rights reserved.
 */
package ch03.ex16;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author yoshiki
 */
public class ExceptionUtilTest {

    private static class AsyncResult {

        private Boolean result = null;

        synchronized boolean waitForResult() {
            while (result == null) {
                try {
                    wait();
                } catch (InterruptedException ex) {
                    Logger.getLogger(ExceptionUtilTest.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            return result;
        }

        synchronized void setResult(boolean result) {
            this.result = result;
            notifyAll();
        }
    }

    public ExceptionUtilTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

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
