/*
 * Copyright (C) 2014 Yoshiki Shibata. All rights reserved.
 */
package ch01.ex10;

import java.util.Comparator;

public interface Collection<E> extends Iterable<E> {

    default boolean addAll(E... elements) {
        throw new AssertionError("Not Implemented Yet");
    }

    default Collection<E> checkedCollection(Class<E> type) {
        throw new AssertionError("Not Implemented Yet");
    }

    default boolean disjoint(Collection<?> c) {
        throw new AssertionError("Not Implemented Yet");
    }

    default Enumeration<E> enumeration() {
        throw new AssertionError("Not Implemented Yet");
    }

    default int frequency(Object o) {
        throw new AssertionError("Not Implemented Yet");
    }

    default E max() {
        throw new AssertionError("Not Implemented Yet");
    }

    default E max(Comparator<? super E> comp) {
        throw new AssertionError("Not Implemented Yet");
    }

    default E min() {
        throw new AssertionError("Not Implemented Yet");
    }

    default E min(Comparator<? super E> comp) {
        throw new AssertionError("Not Implemented Yet");
    }
    
    default Collection<E> synchronizedCollection() {
        throw new AssertionError("Not Implemented Yet");
    }
    
    default Collection<E> unmodifiableCollection() {
        throw new AssertionError("Not Implemented Yet");
    }
}
