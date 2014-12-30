/*
 * Copyright (C) 2014 Yoshiki Shibata. All rights reserved.
 */
package ch06.ex04;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
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
public class LongListUtilTest {

    private final static int NUMBER_OF_LONGS = 1_000_000;
    private final static List<Long> longs = new ArrayList<>(NUMBER_OF_LONGS);

    public LongListUtilTest() {
        Random random = new Random();

        for (int i = 0; i < NUMBER_OF_LONGS; i++) {
            longs.add(random.nextLong());
        }
    }

    @Test
    public void testMax() {
        Long max = Collections.max(longs);
        assertEquals(max, LongListUtil.max(longs));
    }

    @Test
    public void testMin() {
        Long min = Collections.min(longs);
        assertEquals(min, LongListUtil.min(longs));
    }
}
