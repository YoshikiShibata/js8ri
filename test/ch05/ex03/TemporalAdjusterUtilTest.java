/*
 * Copyright (C) 2014 Yoshiki Shibata. All rights reserved.
 */
package ch05.ex03;

import java.time.LocalDate;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author yoshiki
 */
public class TemporalAdjusterUtilTest {

    @Test
    public void testFriday() {
        LocalDate friday = LocalDate.of(2014, 12, 26);
        LocalDate nextWorkDay = friday.with(TemporalAdjusterUtil.next(
                w -> w.getDayOfWeek().getValue() < 6));

        assertEquals(LocalDate.of(2014, 12, 29), nextWorkDay);
    }

    @Test
    public void testSaturday() {
        LocalDate friday = LocalDate.of(2014, 12, 27);
        LocalDate nextWorkDay = friday.with(TemporalAdjusterUtil.next(
                w -> w.getDayOfWeek().getValue() < 6));

        assertEquals(LocalDate.of(2014, 12, 29), nextWorkDay);
    }

    @Test
    public void testSunday() {
        LocalDate friday = LocalDate.of(2014, 12, 28);
        LocalDate nextWorkDay = friday.with(TemporalAdjusterUtil.next(
                w -> w.getDayOfWeek().getValue() < 6));

        assertEquals(LocalDate.of(2014, 12, 29), nextWorkDay);
    }

    @Test
    public void testMonday() {
        LocalDate friday = LocalDate.of(2014, 12, 29);
        LocalDate nextWorkDay = friday.with(TemporalAdjusterUtil.next(
                w -> w.getDayOfWeek().getValue() < 6));

        assertEquals(LocalDate.of(2014, 12, 30), nextWorkDay);
    }
}
