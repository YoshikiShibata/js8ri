/*
 * Copyright (C) Yoshiki Shibata. All rights reserved.
 */
package ch01.ex10;

public interface SortedMap<K, V> {

    default SortedMap<K, V> checkedSortedMap(Class<K> keyType, Class<V> valueType) {
        throw new AssertionError("Not implemented Yet");
    }

    static <K, V> SortedMap<K, V> emptyMap() {
        throw new AssertionError("Not implemented Yet");
    }

    default SortedMap<K, V> synchronizedSortedMap() {
        throw new AssertionError("Not implemented Yet");
    }

    default SortedMap<K, V> unmodifiableSortedMap() {
        throw new AssertionError("Not implemented Yet");
    }
}
