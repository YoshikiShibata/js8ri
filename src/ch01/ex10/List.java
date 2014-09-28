/*
 * Copyright (C) 2014 Yoshiki Shibata. All rights reserved.
 */
package ch01.ex10;

import java.util.Comparator;
import java.util.Random;

public interface List<E> {

    default int binarySearch(E key) {
        throw new AssertionError("Not Implemented Yet");
    }

    default int binarySearch(E key, Comparator<? super E> c) {
        throw new AssertionError("Not Implemented Yet");
    }

    default List<E> checkedList(Class<E> type) {
        throw new AssertionError("Not Implemented Yet");
    }

    default void copy(List<? extends E> src) {
    }

    static <T> List<T> emptyList() {
        throw new AssertionError("Not Implemented Yet");
    }

    default void fill(E obj) {
    }

    default int indexOfSubList(List<?> target) {
        throw new AssertionError("Not Implemented Yet");
    }

    default int lastIndexOfSubList(List<?> target) {
        throw new AssertionError("Not Implemented Yet");
    }

    static <T> List<T> nCopies(int n, T o) {
        throw new AssertionError("Not Implemented Yet");
    }

    default boolean replaceAll(E oldVal, E newVal) {
        throw new AssertionError("Not implemented Yet");
    }

    default void reserve() {
    }

    default void rotate(int distance) {
    }

    default void shuffle() {
    }

    default void shuffle(Random rnd) {
    }

    static <T> List<T> singletonList(T o) {
        throw new AssertionError("Not implemented Yet");
    }

    default void sort() {
    }

    default void sort(Comparator<? super E> c) {
    }

    default void swap(int i, int j) {
    }

    default List<E> synchronizedList() {
        throw new AssertionError("Not implemented Yet");
    }

    default List<E> unmodifiableList() {
        throw new AssertionError("Not implemented Yet");
    }
}
