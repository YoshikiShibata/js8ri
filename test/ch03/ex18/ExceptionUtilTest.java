/*
 * Copyright (C) 2014 Yoshiki Shibata. All rights reserved.
 */
package ch03.ex18;

import java.util.function.Function;
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

    @Test(expected=NullPointerException.class)
    public void testNullParameter() {
        ExceptionUtil.unchecked(null);
    }
    
    @Test
    public void testUncheckedWithNoException() {
        FunctionWithException<String, String> fwe = (in) -> {
            return in.toUpperCase();
        };
        
        Function<String, String> f = ExceptionUtil.unchecked(fwe);
        assertEquals("HELLO", f.apply("hello"));
        
    }
    
    @Test
    public void testUncheckedWithException() {
        FunctionWithException<String, String> fwe = (in) -> {
            throw new Exception("ha ha");
        };
        
        Function<String, String> f = ExceptionUtil.unchecked(fwe);
        try {
            f.apply("hello");
        } catch (Throwable e) {
            assertEquals(e.getClass(), RuntimeException.class);
            assertEquals(e.getCause().getClass(), Exception.class);
            return;
        }
        fail();
    }
}
