/*
 * Copyright (C) 2014 Yoshiki Shibata. All rights reserved.
 */
package ch02.ex08;

import java.util.stream.Stream;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Test code for zip method
 */
public class StreamUtilTest {
    
    public StreamUtilTest() {
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

    
    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
    
    
    @Test(expected=NullPointerException.class)
    public void testNullPointerExceptionFirst() {
        StreamUtil.zip(null, Stream.empty());
    }
    
    @Test(expected=NullPointerException.class)
    public void testNullPointerExceptionSecond() {
        StreamUtil.zip(Stream.empty(), null);
    }
}
