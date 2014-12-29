/*
 * Copyright (C) 2014 Yoshiki Shibata. All rights reserved.
 */
package ch05.ex07;

import java.time.LocalDateTime;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author yoshiki
 */
public class TimeIntervalTest {

    @Test(expected = IllegalArgumentException.class)
    public void illegalInterval() {
        LocalDateTime start = LocalDateTime.now();

        TimeInterval it = new TimeInterval(start, start);
    }

    @Test
    public void twoEqualsTimeIntervals() {
        LocalDateTime start = LocalDateTime.now();
        LocalDateTime end = start.plusSeconds(1);

        TimeInterval it1 = new TimeInterval(start, end);
        TimeInterval it2 = new TimeInterval(start, end);

        assertEquals(it1, it2);
    }

    @Test
    public void notOverlapped1() {
        LocalDateTime start = LocalDateTime.now();
        LocalDateTime end = start.plusSeconds(1);

        TimeInterval it1 = new TimeInterval(start, end);
        TimeInterval it2 = new TimeInterval(end, end.plusSeconds(1));

        assertFalse(it1.isOverlapped(it2));
        assertFalse(it2.isOverlapped(it1));
    }

    @Test
    public void notOverlapped2() {
        LocalDateTime start = LocalDateTime.now();
        LocalDateTime end = start.plusSeconds(1);

        LocalDateTime start2 = end.plusMinutes(1);
        LocalDateTime end2 = start2.plusSeconds(1);

        TimeInterval it1 = new TimeInterval(start, end);
        TimeInterval it2 = new TimeInterval(start2, end2);

        assertFalse(it1.isOverlapped(it2));
        assertFalse(it2.isOverlapped(it1));
    }

    @Test
    public void overlapped1() {
        LocalDateTime start = LocalDateTime.now();
        LocalDateTime end = start.plusHours(1);

        LocalDateTime start2 = start.plusMinutes(1);
        LocalDateTime end2 = start2.plusMinutes(30);

        TimeInterval it1 = new TimeInterval(start, end);
        TimeInterval it2 = new TimeInterval(start2, end2);

        assertTrue(it1.isOverlapped(it2));
        assertTrue(it2.isOverlapped(it1));
    }

    @Test
    public void overlapped2() {
        LocalDateTime start = LocalDateTime.now();
        LocalDateTime end = start.plusHours(1);

        LocalDateTime start2 = start.plusMinutes(1);
        LocalDateTime end2 = start2.plusHours(1);

        TimeInterval it1 = new TimeInterval(start, end);
        TimeInterval it2 = new TimeInterval(start2, end2);

        assertTrue(it1.isOverlapped(it2));
        assertTrue(it2.isOverlapped(it1));
    }
}
