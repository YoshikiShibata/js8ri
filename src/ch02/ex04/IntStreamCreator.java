/*
 * Copyright (C) 2014 Yoshiki Shibata. All rights reserved.
 */
package ch02.ex04;

import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * Suppose you have an array int[] values = { 1, 4, 9, 16 }. What is
 * Stream.of(values)? How do you get a stream of int instead?
 * 
 * 配列int[] values = {1, 4, 9, 16} があるとします。Stream.of(values)
 * は、何になるでしょうか。代わりに、int のストリームをどうやって取得できるでしょ
 * うか。
 */
public class IntStreamCreator {
    
    public static void main(String[] args) {
        int[] values = {1, 4, 9, 16};
        
        // Stream is a generic interface and its type variable T cannot be int.
        // So in this case, T is int[].
        Stream<int[]> s = Stream.of(values);
        
        // Instead of Stream.of, use IntStream.of
        IntStream is = IntStream.of(values);
        System.out.println("sum = " + is.sum());
    }

}
