package ch06.ex01;

/*
 * Copyright (C) 2014 Yoshiki Shibata. All rights reserved.
 */

import java.util.List;
import js8ri.util.FileUtil;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Test code for LongestString
 */
public class LongestStringTest {
    @Test
    public void parallelSet() {
        List<String> wap = FileUtil.readAsWords("WarAndPeace.txt");
        LongestString ls = new LongestString();
        
        wap.parallelStream().forEach(ls::setIfLongest);
        System.out.println(ls.getLongest());
        assertEquals(longestString(wap), ls.getLongest());
    }
    
    private String longestString(List<String> strings) {
        String result = "";
        for (String s: strings) {
            if (s.length() > result.length())
                result = s;
        }
        return result;
    }
}
