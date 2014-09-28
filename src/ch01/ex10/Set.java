/*
 * Copyright (C) 2014 Yoshiki Shibata. All rights reserved.
 */
package ch01.ex10;

public interface Set<E> {

    default Set<E> checkedSet(Class<E> type) {
        throw new AssertionError("Not Implemented Yet");
    }

    static <T> Set<T> emptySet() {
        throw new AssertionError("Not Implemented Yet");
    }

    static <T> Set<T> singleton(T o) {
        throw new AssertionError("Not Implemented Yet");
    }

    default Set<E> synchronizedSet() {
        throw new AssertionError("Not Implemented Yet");
    }

    default Set<E> unmodifiableSet() {
        throw new AssertionError("Not Implemented Yet");
    }
}
