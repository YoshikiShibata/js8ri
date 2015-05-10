/*
 * Copyright (C) 2015 Yoshiki Shibata. All rights reserved.
 */
package ch08.ex16;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Test Code for NamedCapture
 */
public class NamedCaptureTest {

    @Test
    public void test4digitsOfZipCode() {
        NamedCapture.CityInfo ci = NamedCapture.extractCityInfo("Webster, NY 14580");
        assertEquals("Webster", ci.city);
        assertEquals("NY", ci.state);
        assertEquals("14580", ci.zipCode);
    }

    @Test
    public void test7digitsOfZipCode() {
        NamedCapture.CityInfo ci = NamedCapture.extractCityInfo("Webster, NY 14580-1413");
        assertEquals("Webster", ci.city);
        assertEquals("NY", ci.state);
        assertEquals("14580-1413", ci.zipCode);
    }
}
