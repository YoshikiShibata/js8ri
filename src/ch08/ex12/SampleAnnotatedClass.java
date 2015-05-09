/*
 * Copyright (C) 2015 Yoshiki Shibata. All rights reserved.
 */
package ch08.ex12;

/**
 */
public class SampleAnnotatedClass {
    public SampleAnnotatedClass() { }
    
    @TestCase(param=0, expected=0)
    public int simpleReturn1(int value) {
        return value; 
    }
    
    @TestCase(param=0, expected=0)
    @TestCase(param=1, expected=1)
    public int simpleReturn2(int value) {
        return value; 
    }   
    
    @TestCase(param=1701, expected=1701)
    public int simpleReturn(int value) {
        return value;
    }
}
