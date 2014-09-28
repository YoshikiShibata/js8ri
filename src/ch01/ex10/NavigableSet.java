/*
 * Copyright (C) 2014 Yoshiki Shibata. All rights reserved.
 */

package ch01.ex10;

public interface NavigableSet<E> {
    static <T> NavigableSet<T> emptyNavigableSet() {
        throw new AssertionError("Not Implemented Yet");
    }
    
    default NavigableSet<E> synchronziedNavigableSet() {
        throw new AssertionError("Not Implemented Yet");
    }
    
    default NavigableSet<E> unmodifiableNavigableSet() {
        throw new AssertionError("Not Implemented Yet");
    }
}
