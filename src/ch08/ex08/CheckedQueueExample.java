/*
 * Copyright (C) 2015 Yoshiki Shibata. All rights reserved.
 */
package ch08.ex08;

import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Write a program that demonstrates the benefits of the CheckedQueue class.
 *
 * CheckedQueue クラスの利点を示すプログラムを書きなさい。
 */
public final class CheckedQueueExample {
    
    public static void main(String[] args) {
        Queue<String> queue = new LinkedList<>();
        
        Queue checkedQueue = Collections.checkedQueue(queue, String.class);
        
        checkedQueue.add(10);
    }
}
