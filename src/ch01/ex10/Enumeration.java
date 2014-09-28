/*
 * Copyright (C) 2014 Yoshiki Shibata. All rights reserved.
 */

package ch01.ex10;

import java.util.ArrayList;

public interface Enumeration<E> {
    static <T> Enumeration<T> emptyEnumeration() {
        throw new AssertionError("Not Implemented Yet");
    }
    
    default ArrayList<E> list() {
        throw new AssertionError("Not Implemented Yet");
    } 
}
