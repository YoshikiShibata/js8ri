/*
 * Copyright (C) 2014 Yoshiki Shibata. All rights reserved.
 */
package ch03.ex09;

import java.util.Comparator;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author yoshiki
 */
public class ComparatorUtilTest {

    @Test(expected = NullPointerException.class)
    public void testLexicographicComparatorWithNull() {
        ComparatorUtil.lexicographicComparator((String[]) null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testLexicographicComparatorWithEmpty() {
        ComparatorUtil.lexicographicComparator();
    }

    private static class OneField {

        private final String firstField;

        OneField(String value) {
            firstField = value;
        }
    }

    @Test(expected = NullPointerException.class)
    public void testLexicographicComparatorWithNullO1() {
        Comparator<? super OneField> cmp
                = ComparatorUtil.lexicographicComparator("firstField");
        cmp.compare(null, new OneField("hello"));
    }

    @Test(expected = NullPointerException.class)
    public void testLexicographicComparatorWithNullO2() {
        Comparator<? super OneField> cmp
                = ComparatorUtil.lexicographicComparator("firstField");
        cmp.compare(new OneField("hello"), null);
    }

    @Test
    public void testLexicographicComparatorWithOneField() {
        Comparator<? super OneField> cmp
                = ComparatorUtil.lexicographicComparator("firstField");

        OneField o1 = new OneField("hello");
        OneField o2 = new OneField("world");
        assertTrue(cmp.compare(o1, o2) < 0);
        assertTrue(cmp.compare(o2, o1) > 0);
        assertTrue(cmp.compare(o1, o1) == 0);
    }

    private static class FiveField {

        private final String field1;
        private final String field2;
        private final String field3;
        private final String field4;
        private final String field5;

        FiveField(String v1, String v2, String v3, String v4, String v5) {
            field1 = v1;
            field2 = v2;
            field3 = v3;
            field4 = v4;
            field5 = v5;
        }
    }

    @Test
    public void testLexicographicComparatorWith5Fields() {
        Comparator<? super FiveField> cmp
                = ComparatorUtil.lexicographicComparator(
                        "field1", "field2", "field3", "field4", "field5");

        FiveField o1 = new FiveField("one", "two", "three", "four", "five");
        assertTrue(cmp.compare(o1, o1) == 0);

        FiveField o2 = new FiveField("one", "two", "three", "four", "six");
        assertTrue(cmp.compare(o1, o2) < 0);
        assertTrue(cmp.compare(o2, o1) > 0);

        FiveField o3 = new FiveField("one", "two", "six", "four", "five");
        assertTrue(cmp.compare(o1, o3) > 0);
        assertTrue(cmp.compare(o3, o1) < 0);
    }
}
