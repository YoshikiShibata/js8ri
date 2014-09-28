/*
 * Copyright (C) 2014 Yoshiki Shibata. All rights reserved.
 */
package ch01.ex06;

import org.junit.Test;

/**
 * Test code for ExceptionUtils.
 */
public class ExceptionUtilsTest {

    @Test
    public void uncheckTest() {
        // Prepare
        Thread t = new Thread(ExceptionUtils.uncheck(() -> {
            System.out.println("Zzz");
            Thread.sleep(1000);
        }));

        // Action
        t.start();

        // Check (Nothing to be checked here)
        ExceptionUtils.uncheck(() -> {
            t.join();
        });
    }
}
