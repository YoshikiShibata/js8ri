/*
 * Copyright (C) 2014 Yoshiki Shibata. All rights reserved.
 */
package ch03.ex07;

import ch03.ex07.StringComparators.Order;
import java.util.Comparator;
import java.util.EnumSet;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author yoshiki
 */
public class StringComparatorsTest {

    @Test
    public void testNullPointerExceptions() {
        // Prepare
        Comparator<?>[] comparators = {
            StringComparators.NATURAL_ORDER,
            StringComparators.REVERSED_ORDER,
            StringComparators.CASE_SENSITIVE_ORDER,
            StringComparators.CASE_INSENSITIVE_ORDER,
            StringComparators.SPACE_SENSITIVE_ORDER,
            StringComparators.SPACE_INSENSITIVE_ORDER
        };

        // Action & Check
        for (Comparator<?> cmp : comparators) {
            Comparator<String> scmp = (Comparator<String>) cmp;

            try {
                scmp.compare(null, "world");
                fail();
            } catch (NullPointerException e) {
                // expected
            }

            try {
                scmp.compare("hello", null);
                fail();
            } catch (NullPointerException e) {
                // expected
            }

        }
    }

    @Test
    public void testNaturalOrder() {
        Comparator<String> cmp = StringComparators.NATURAL_ORDER;

        assertTrue(cmp.compare("hello", "world") < 0);
        assertTrue(cmp.compare("world", "hello") > 0);
        assertTrue(cmp.compare("hello", "hello") == 0);
    }

    @Test
    public void testReversedOrder() {
        Comparator<String> cmp = StringComparators.REVERSED_ORDER;

        assertTrue(cmp.compare("hello", "world") > 0);
        assertTrue(cmp.compare("world", "hello") < 0);
        assertTrue(cmp.compare("hello", "hello") == 0);
    }

    @Test
    public void testCaseSensitiveOrder() {
        Comparator<String> cmp = StringComparators.CASE_SENSITIVE_ORDER;

        assertTrue(cmp.compare("Hello", "hello") < 0);
        assertTrue(cmp.compare("hello", "Hello") > 0);
        assertTrue(cmp.compare("Hello", "Hello") == 0);
        assertTrue(cmp.compare("hello", "hello") == 0);
    }

    @Test
    public void testCaseInsensitiveOrder() {
        Comparator<String> cmp = StringComparators.CASE_INSENSITIVE_ORDER;

        assertTrue(cmp.compare("Hello", "hello") == 0);
        assertTrue(cmp.compare("hello", "Hello") == 0);
        assertTrue(cmp.compare("Hello", "Hello") == 0);
        assertTrue(cmp.compare("hello", "hello") == 0);

        assertTrue(cmp.compare("HELLO", "world") < 0);
        assertTrue(cmp.compare("world", "HELLO") > 0);
    }

    @Test
    public void testCaseSpaceSensitiveOrder() {
        Comparator<String> cmp = StringComparators.SPACE_SENSITIVE_ORDER;

        assertTrue(cmp.compare("hello world", "helloworld") < 0);
        assertTrue(cmp.compare("hello world", "hello world") == 0);
        assertTrue(cmp.compare("helloworld", "hello world") > 0);
    }

    @Test
    public void testCaseSpaceInsensitiveOrder() {
        Comparator<String> cmp = StringComparators.SPACE_INSENSITIVE_ORDER;

        assertTrue(cmp.compare("hello world", "helloworld") == 0);
        assertTrue(cmp.compare("helloworld", "hello world") == 0);
        assertTrue(cmp.compare("hello", "world") < 0);
        assertTrue(cmp.compare("world", "hello") > 0);
    }

    @Test(expected = NullPointerException.class)
    public void testCreate() {
        Comparator<String> cmp = StringComparators.create(null);
    }

    @Test
    public void testCreateWithReversedCaseInsensitive() {
        Comparator<String> cmp = StringComparators.create(
                EnumSet.of(Order.REVERSED, Order.CASE_INSENSITIVE));

        assertTrue(cmp.compare("hello", "World") > 0);
        assertTrue(cmp.compare("world", "Hello") < 0);
        assertTrue(cmp.compare("hello", "Hello") == 0);
    }

    @Test
    public void testCreateWithReversedSpaceInsensitive() {
        Comparator<String> cmp = StringComparators.create(
                EnumSet.of(Order.REVERSED, Order.SPACE_INSENSITIVE));

        assertTrue(cmp.compare("Hello world", "helloworld") > 0);
        assertTrue(cmp.compare("hello world", "Helloworld") < 0);
        assertTrue(cmp.compare("Hello World", "HelloWorld") == 0);
    }

    @Test
    public void testCreateCaseSpaceInsensitive() {
        Comparator<String> cmp = StringComparators.create(
                EnumSet.of(Order.CASE_INSENSITIVE, Order.SPACE_INSENSITIVE));

        assertTrue(cmp.compare("Hello world", "helloworld") == 0);
        assertTrue(cmp.compare("hello world", "Helloworld") == 0);
        assertTrue(cmp.compare("Hello World", "HelloWorld") == 0);
    }

    @Test
    public void testCreateAlls() {
        Comparator<String> cmp = StringComparators.create(
                EnumSet.of(Order.REVERSED,
                        Order.CASE_INSENSITIVE, 
                        Order.SPACE_INSENSITIVE));
        
        assertTrue(cmp.compare("Hello World", "hello world") == 0);
        assertTrue(cmp.compare("H e l l o", "w o r l d") > 0);
        assertTrue(cmp.compare("w o r l d", "H e l l o") < 0);
    }
}
