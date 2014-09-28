/*
 * Copyright (C) 2014 Yoshiki Shibata. All rights reserved.
 */
package ch01.ex10;

public interface NavigableMap<K, V> {

    default NavigableMap<K, V> checkedNavigableMap(Class<K> keyType, Class<V> valueType) {
        throw new AssertionError("Not Implemented Yet");
    }
    
    static <K, V> NavigableMap<K, V> emptyNavigableSet() {
        throw new AssertionError("Not Implemented Yet");
    }
    
    default NavigableMap<K, V> synchronziedNavigableMap() {
        throw new AssertionError("Not Implemented Yet");
    }
    
       default NavigableMap<K, V> unmodifiableNavigableMap() {
        throw new AssertionError("Not Implemented Yet");
    }
}
