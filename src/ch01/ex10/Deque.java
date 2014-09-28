/*
 * Copyright (C) 2014 Yoshiki Shibata. All rights reserved.
 */

package ch01.ex10;

import java.util.Queue;

public interface Deque<T> {
    default Queue<T> asLifoQueue() {
        throw new AssertionError("Not Implemented Yet");
    } 
    
}
