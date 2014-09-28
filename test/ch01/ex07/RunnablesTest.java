/*
 * Copyright (C) 2014 Yoshiki Shibata. All rights reserved.
 */

package ch01.ex07;

import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.*;
import org.junit.Test;

/**
 * Test code for Runnables
 */
public class RunnablesTest {
    
    @Test
    public void andThenTest() {
        // Prepare
        List<String> messages = new ArrayList<>();
        Runnable first = () -> { messages.add("first"); };
        Runnable second = () -> { messages.add("second"); };
        
        // Action
        Runnable combinedRun = Runnables.andThen(first, second);
        combinedRun.run();
        
        // Check
        assertEquals("first", messages.get(0));
        assertEquals("second", messages.get(1));
    }
}
