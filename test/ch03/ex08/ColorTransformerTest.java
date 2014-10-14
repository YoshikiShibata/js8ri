/*
 * Copyright (C) 2014 Yoshiki Shibata. All rights reserved.
 */
package ch03.ex08;

import javafx.scene.paint.Color;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Test code for ColorTransformer
 */
public class ColorTransformerTest {

    public ColorTransformerTest() {
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

    @Test(expected = IllegalArgumentException.class)
    public void testFrameTransformerWithIllegalThickness() {
        ColorTransformer ct = ColorTransformer.frameTransformer(0, Color.RED, 100.0, 100.0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testFrameTransformerWithIllegalWidth() {
        ColorTransformer ct = ColorTransformer.frameTransformer(10, Color.RED, 0, 100.0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testFrameTransformerWithIllegalHeight() {
        ColorTransformer ct = ColorTransformer.frameTransformer(10, Color.RED, 100.0, 0);
    }

    @Test(expected = NullPointerException.class)
    public void testFrameTransformerWithNullColor() {
        ColorTransformer ct = ColorTransformer.frameTransformer(10, null, 100.0, 100.0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testFrameTransformerWithTooLargeThicknessForWidth() {
        ColorTransformer ct = ColorTransformer.frameTransformer(10, Color.AQUA, 20.0, 100.0);
    }
    
        @Test(expected = IllegalArgumentException.class)
    public void testFrameTransformerWithTooLargeThicknessForHeight() {
        ColorTransformer ct = ColorTransformer.frameTransformer(10, Color.AQUA, 100.0, 20.0);
    }
}
