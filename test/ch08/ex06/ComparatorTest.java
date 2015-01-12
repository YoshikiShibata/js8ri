/*
 * Copyright (C) 2015 Yoshiki Shibata. All rights reserved.
 */
package ch08.ex06;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author yoshiki
 */
public class ComparatorTest {
    @Test
    public void testPoint2D() {
        Point2D p1 = new Point2D(0, 0);
        Point2D p2 = new Point2D(0, 1);
        Point2D p3 = new Point2D(1, 1);
        
        assertTrue(Point2D.COMPARATOR.compare(p1, p2) < 0);
        assertTrue(Point2D.COMPARATOR.compare(p2, p1) > 0);
        assertTrue(Point2D.COMPARATOR.compare(p2, p3) < 0);
        assertTrue(Point2D.COMPARATOR.compare(p3, p2) > 0);
        
        assertTrue(Point2D.COMPARATOR.compare(p1, p1) == 0);
        assertTrue(Point2D.COMPARATOR.compare(p2, p2) == 0);
        assertTrue(Point2D.COMPARATOR.compare(p3, p3) == 0);
    }
    
    @Test
    public void testRectangle2D() {
        Rectangle2D r1 = new Rectangle2D(new Point2D(0,0), 100, 200);
        Rectangle2D r2 = new Rectangle2D(new Point2D(0,0), 200, 200);
        Rectangle2D r3 = new Rectangle2D(new Point2D(0,0), 200, 300);
        
        assertTrue(Rectangle2D.COMPARATOR.compare(r1, r2) < 0);
        assertTrue(Rectangle2D.COMPARATOR.compare(r2, r1) > 0);
        assertTrue(Rectangle2D.COMPARATOR.compare(r2, r3) < 0);
        assertTrue(Rectangle2D.COMPARATOR.compare(r3, r2) > 0);
        
        assertTrue(Rectangle2D.COMPARATOR.compare(r1, r1) == 0);
        assertTrue(Rectangle2D.COMPARATOR.compare(r2, r2) == 0);
        assertTrue(Rectangle2D.COMPARATOR.compare(r3, r3) == 0);
    }
}
