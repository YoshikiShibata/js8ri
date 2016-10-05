/*
 * Copyright (C) 2015 Yoshiki Shibata. All rights reserved.
 */
package ch08.ex01;

import ch08.ex01.UnsignedInt;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author yoshiki
 */
public class UnsignedIntTest {
    @Test
    public void testAdd() {
        int x = -1;
        int y = 0;

        assertTrue((x + y) == UnsignedInt.add(x, y));

        y = -1;
        assertTrue((x + y) == UnsignedInt.add(x, y));
    }

    @Test
    public void testSubtract() {
        int x = -1;
        int y = 0;

        assertTrue((x - y) == UnsignedInt.subtract(x, y));
        assertTrue((y - x) == UnsignedInt.subtract(y, x));

        y = -1;
        assertTrue((x - y) == UnsignedInt.subtract(x, y));
    }

    @Test
    public void testDivide() {
        int x = -1;
        int y = 5;

        assertTrue(Integer.divideUnsigned(x, y) == UnsignedInt.divide(x, y));

        y = -1;
        assertTrue(Integer.divideUnsigned(x, y) == UnsignedInt.divide(x, y));
    }

    @Test
    public void testCompare() {
        int x = -1;
        int y = 0;
        
        assertTrue(UnsignedInt.compare(x, y) == 1);
        assertTrue(UnsignedInt.compare(y, x) == -1);
        assertTrue(UnsignedInt.compare(x, x) == 0);
    }
}
