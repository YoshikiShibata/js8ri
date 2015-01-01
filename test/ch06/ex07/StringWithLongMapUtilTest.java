/*
 * Copyright (C) 2015 Yoshiki Shibata. All rights reserved.
 */

package ch06.ex07;

import java.util.Collections;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Test code for StringWithLongMapUtil
 */
public class StringWithLongMapUtilTest {

    @Test
    public void testMax() {
        final int MAX_ENTRIES = 10_000;
        ConcurrentHashMap<String, Long> map = new ConcurrentHashMap<>();
        Random random = new Random();
        
        for (int i = 0; i < MAX_ENTRIES; i++)
            map.put(String.valueOf(i), random.nextLong());
        
        Long expectedMaxValue = Collections.max(map.values());
        Map.Entry<String, Long> entry = StringWithLongMapUtil.max(map);
        assertEquals(expectedMaxValue, entry.getValue());
        System.out.printf("key: %s value: %d%n", entry.getKey(), entry.getValue());
    }
}
