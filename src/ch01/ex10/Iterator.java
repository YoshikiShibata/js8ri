/*
 * Copyright (C) 2014 Yoshiki Shibata. All rights reserved.
 */

package ch01.ex10;

public interface Iterator<E> {
    static <T> Iterator<T> emptyIterator() {
        throw new AssertionError("Not Implemented Yet");
    }
}
