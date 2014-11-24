/*
 * Copyright (C) 2014 Yoshiki Shibata. All rights reserved.
 */
package ch03.ex17;

import js8ri.util.AsyncResult;
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
public class AsyncUtilTest {

    public AsyncUtilTest() {
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
                (e) -> {
                    asFirst.setResult(true);
                });

        assertTrue(asFirst.waitForResult());
        assertTrue(asSecond.waitForResult());
    }
    
    @Test
    public void testSecondThrowException() {
        throw new AssertionError("Not Implemented Yet");
    }
}
