/*
 * Copyright (C) 2014 Yoshiki Shibata. All rights reserved.
 */
package ch02.ex06;

import java.util.ArrayList;
import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Test code for CharacterUtil
 */
public class CharacterUtilTest {

    @Test
    public void testCharacterStreams() {
        final String msg = "Hello World!";
        
        List<Character> list1 = new ArrayList<>();
        List<Character> list2 = new ArrayList<>();
        
        CharacterUtil.characterStream(msg).forEach(list1::add);
        CharacterUtil.characterStream2(msg).forEach(list2::add);
        
        assertTrue(list1.equals(list2));
    }
}
