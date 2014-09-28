/*
 * Copyright (C) 2014 Yoshiki Shibata. All rights reserved.
 */
package ch01.ex10;


public interface SortedSet<E> {

    default SortedSet<E> checkedSortedSet(Class<E> type) {
        throw new AssertionError("Not Implemented Yet");
    }

    static <T> SortedSet<T> emptySortedSet() {
        throw new AssertionError("Not Implemented Yet");
    }

    default SortedSet<E> synchronizedSortedSet() {
        throw new AssertionError("Not Implemented Yet");
    }

    default SortedSet<E> unmodifiableSortedSet() {
        throw new AssertionError("Not Implemented Yet");
    }
}
