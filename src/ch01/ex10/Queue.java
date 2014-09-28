/*
 * Copyright (C) 2014 Yoshiki Shibata. All rights reserved.
 */

package ch01.ex10;

public interface Queue<E> {
    default Queue<E> checkedQueue(Class<E> type) {
        throw new AssertionError("Not implemented yet");
    }
}
