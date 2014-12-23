/*
 * Copyright (C) 2014 Yoshiki Shibata. All rights reserved.
 */
package ch04.ex05;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableStringValue;
import javafx.beans.value.ObservableValue;
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
public class BindingUtilTest {

    public BindingUtilTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
    @Test
    public void testSimpleGetValue() {
        SimpleStringProperty sProperty = new SimpleStringProperty();
        sProperty.set("123");
        ObservableValue<Integer> newOV = BindingUtil.observe(
                Integer::valueOf, sProperty);

        assertEquals(Integer.valueOf(123), newOV.getValue());
    }

    @Test
    public void testInvalidationListener() {
        SimpleStringProperty sProperty = new SimpleStringProperty();
        sProperty.set("123");
        ObservableValue<Integer> newOV = BindingUtil.observe(
                Integer::valueOf, sProperty);

        boolean[] flag = new boolean[1];
        flag[0] = false;

        newOV.addListener(observable -> flag[0] = true);

        sProperty.set("234");

        assertTrue(flag[0]);
        assertEquals(Integer.valueOf(234), newOV.getValue());
    }

    @Test
    public void testChangeListener() {
        SimpleStringProperty sProperty = new SimpleStringProperty();
        sProperty.set("123");
        ObservableValue<Integer> newOV = BindingUtil.observe(
                Integer::valueOf, sProperty);

        boolean[] flag = new boolean[1];
        flag[0] = false;
        Integer[] values = new Integer[2];

        newOV.addListener((observable, oldValue, newValue) -> {
            flag[0] = true;
            values[0] = oldValue;
            values[1] = newValue;
        });

        sProperty.set("234");

        assertTrue(flag[0]);
        assertEquals(Integer.valueOf(234), newOV.getValue());
        assertEquals(Integer.valueOf(123), values[0]);
        assertEquals(Integer.valueOf(234), values[1]);
    }

    @Test
    public void testSimpleGetValue2() {
        SimpleStringProperty value1 = new SimpleStringProperty();
        SimpleIntegerProperty value2 = new SimpleIntegerProperty();

        value1.set("123");
        value2.set(123);

        ObservableValue<Integer> newOV = BindingUtil.observe(
                (v1, v2) -> Integer.valueOf(v1) + v2.intValue(),
                value1, value2);

        assertEquals(Integer.valueOf(246), newOV.getValue());
    }

    @Test
    public void testInvalidationListener2_1() {
        SimpleStringProperty value1 = new SimpleStringProperty();
        SimpleIntegerProperty value2 = new SimpleIntegerProperty();

        value1.set("123");
        value2.set(123);

        ObservableValue<Integer> newOV = BindingUtil.observe(
                (v1, v2) -> Integer.valueOf(v1) + v2.intValue(),
                value1, value2);

        boolean[] flag = new boolean[1];
        flag[0] = false;

        newOV.addListener(observable -> flag[0] = true);

        value1.set("234");

        assertTrue(flag[0]);
        assertEquals(Integer.valueOf(357), newOV.getValue());
    }
    
        @Test
    public void testInvalidationListener2_2() {
        SimpleStringProperty value1 = new SimpleStringProperty();
        SimpleIntegerProperty value2 = new SimpleIntegerProperty();

        value1.set("123");
        value2.set(123);

        ObservableValue<Integer> newOV = BindingUtil.observe(
                (v1, v2) -> Integer.valueOf(v1) + v2.intValue(),
                value1, value2);

        boolean[] flag = new boolean[1];
        flag[0] = false;

        newOV.addListener(observable -> flag[0] = true);

        value2.set(234);

        assertTrue(flag[0]);
        assertEquals(Integer.valueOf(357), newOV.getValue());
    }
    
    @Test
    public void testChangeListener2_1() {
        SimpleStringProperty value1 = new SimpleStringProperty();
        SimpleIntegerProperty value2 = new SimpleIntegerProperty();
        value1.set("123");
        value2.set(123);;

        ObservableValue<Integer> newOV = BindingUtil.observe(
                (v1, v2) -> Integer.valueOf(v1) + v2.intValue(),
                value1, value2);

        boolean[] flag = new boolean[1];
        flag[0] = false;
        Integer[] values = new Integer[2];

        newOV.addListener((observable, oldValue, newValue) -> {
            flag[0] = true;
            values[0] = oldValue;
            values[1] = newValue;
        });

        value1.set("234");

        assertTrue(flag[0]);
        assertEquals(Integer.valueOf(123 + 234), newOV.getValue());
        assertEquals(Integer.valueOf(123 + 123), values[0]);
        assertEquals(Integer.valueOf(123 + 234), values[1]);
    }
    
        @Test
    public void testChangeListener2_2() {
        SimpleStringProperty value1 = new SimpleStringProperty();
        SimpleIntegerProperty value2 = new SimpleIntegerProperty();
        value1.set("123");
        value2.set(123);;

        ObservableValue<Integer> newOV = BindingUtil.observe(
                (v1, v2) -> Integer.valueOf(v1) + v2.intValue(),
                value1, value2);

        boolean[] flag = new boolean[1];
        flag[0] = false;
        Integer[] values = new Integer[2];

        newOV.addListener((observable, oldValue, newValue) -> {
            flag[0] = true;
            values[0] = oldValue;
            values[1] = newValue;
        });

        value2.set(234);

        assertTrue(flag[0]);
        assertEquals(Integer.valueOf(123 + 234), newOV.getValue());
        assertEquals(Integer.valueOf(123 + 123), values[0]);
        assertEquals(Integer.valueOf(123 + 234), values[1]);
    }
}
