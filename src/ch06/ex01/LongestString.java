/*
 * Copyright (C) 2014 Yoshiki Shibata. All rights reserved.
 */
package ch06.ex01;

import java.util.concurrent.atomic.AtomicReference;

/**
 * Write a program that keeps track of the longest string that is observed by a
 * number of threads. Use an AtomicReference and an appropriate accumulator.
 *
 * 多数のスレッドが更新する最大長の文字列を管理するプログラムを書きなさい。 AtomicReference と適切な累積関数を使用しなさい
 */
public final class LongestString {
    
    private final AtomicReference<String> longestString= new AtomicReference<>(""); 
    
    public final void setIfLongest(String s) {
        longestString.accumulateAndGet(s, (s1, s2) -> {
            return s1.length() > s2.length()? s1 : s2;
        });
    }
    
    public final String getLongest() {
        return longestString.get();
    }
}
