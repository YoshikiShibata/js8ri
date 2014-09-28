/*
 * Copyright (C) 2014 Yoshiki Shibata. All rights reserved.
 */

package ch01.ex10;

public interface Comparator<T> {
    
    static <T> Comparator<T> reverseOrder() {
        throw new AssertionError("Not Implemented Yet");
    }
    
    static <T> Comparator<T> reverseOrder(Comparator<T> cmp) {
        throw new AssertionError("Not Implemented Yet");
    }
}
