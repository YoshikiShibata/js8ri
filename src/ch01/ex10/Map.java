/*
 * Copyright (C) 2014 Yoshiki Shibata. All rights reserved.
 */

package ch01.ex10;

public interface Map<K, V> {
    default Map<K,V> checkedMap(Class<K> keyType, Class<V> valueType) {
        throw new AssertionError("Not implemented Yet");
    }
    
    static <K, V> Map<K, V> emptyMap() {
        throw new AssertionError("Not implemented Yet");
    }
    
    default Set<K> newSetFromMap() {
        throw new AssertionError("Not implemented Yet");
    }
    
    static <K, V> Map<K, V> singletonMap(K key, V value) {
        throw new AssertionError("Not implemented Yet");
    }
    
    default Map<K, V> synchronizedMap() {
        throw new AssertionError("Not implemented Yet");
    }
    
       default Map<K, V> unmodifiableMap() {
        throw new AssertionError("Not implemented Yet");
    }
}
