/*
 * Copyright (C) 2014, 2020 Yoshiki Shibata. All rights reserved.
 */
package ch02.ex12;

import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Stream;

/**
 * Count all short words in a parallel Stream<String>, as described in Section
 * 2.13, “Parallel Streams,” on page 40, by updating an array of AtomicInteger.
 * Use the atomic getAndIncrement method to safely increment each counter.
 *
 * 51 ページの2.13「並列ストリーム」で説明したように、AtomicInteger の配列を更新
 * することで、並列なStream<String>内の短い単語をすべて数えなさい。個々のカウ
 * ントを安全に増やすためにアトミックであるgetAndIncrement メソッドを使用しな さい。
 */
public class ParallelCount {
    
    private ParallelCount() {
        // Non-instantiable
    }
    
    /**
     * Counts occurrences of short words, based on its legnth.
     * 
     * @param words stream of words
     * @param length words which are less than length will be counted.
     * @return array of occurennces of each short words. 
     * @throws NullPointerException if words is null.
     * @throws IllegalArgumentException if length is not greater than zero.
     */
    public static int[] countShortWords(Stream<String> words, int length) {
		Objects.requireNonNull(words, "words is null");
        
        if (length <= 0)
            throw new IllegalArgumentException("length(" + length + ") must be greater than zero");
        
        AtomicInteger[] occurences = new AtomicInteger[length];
        for (int i = 0; i < length; i++)
            occurences[i] = new AtomicInteger();
        
        words.parallel().forEach(w -> {
            if (!w.isEmpty() && w.length() < length) {
                occurences[w.length()].getAndIncrement();
            }
        });
        
        int[] result = new int[length];
        for (int i = 0; i < length; i++)
            result[i] = occurences[i].get();
        
        return result;
    }
    

}
