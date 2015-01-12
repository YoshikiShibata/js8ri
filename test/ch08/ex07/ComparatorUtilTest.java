/*
 * Copyright (C) 2015 Yoshiki Shibata. All rights reserved.
 */
package ch08.ex07;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Random;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Test code for ComparatorUtil
 */
public class ComparatorUtilTest {
    
    @Test
    public void testComparator() {
        // Prepare
        List<String> list = new ArrayList<>();
        
        Random random = new Random();
        for (int i = 0; i < 100; i++) {
            int next = random.nextInt();
            list.add(String.valueOf(next));
        }
        
        for (int i = 0; i < 5; i++) {
            list.add(null);
        }
       
        String[] array1 = list.toArray(new String[0]);
        String[] array2 = list.toArray(new String[0]);
        
        // Action
        Arrays.sort(array1, Comparator.<String>nullsFirst(Comparator.naturalOrder()).reversed());
        Arrays.sort(array2, ComparatorUtil.reversedNullsLast());
        
        // Check
        assertTrue(Arrays.equals(array1, array2));
    }
}
