/*
 * Copyright (C) 2014 Yoshiki Shibata. All rights reserved.
 */
package ch03.ex20;

import java.util.ArrayList;
import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Test Code for ListUtil
 */
public class ListUtilTest {
    
    @Test(expected=NullPointerException.class)
    public void testNullList() {
        ListUtil.map(null, (e) -> e);
    }
    
    @Test(expected=NullPointerException.class)
    public void testNullFunction() {
        List<String> list = new ArrayList<>();
        
        ListUtil.map(list, null);
    }
    
    @Test
    public void testMap() {
        // Prepare
        List<Integer> iList = new ArrayList<>();
        
        for (int i = -128; i < 128; i++)
            iList.add(i);
        
        // Action
        List<String> sList = ListUtil.map(iList, (i) -> i.toString());
        
        // Check
        int i = -128;
        for (String s: sList) {
            assertEquals(String.valueOf(i), s);
            i++;
        }
    }
}
