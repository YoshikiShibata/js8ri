/*
 * Copyright (C) 2014, 2020 Yoshiki Shibata. All rights reserved.
 */
package ch02.ex13;

import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Repeat the preceding exercise, but filter out the short strings and use the
 * collect method with Collectors.groupingBy and Collectors.counting.
 *
 * 練習問題12 に対して次の点を変更し、その問題を解きなさい。変更点として、短い文字列 はフィルターで取り出し、Collectors.groupingBy
 * とCollectors.counting と一緒にcollect メソッドを使用しなさい。
 */
public class ShortWordCounter {

    private ShortWordCounter() {
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

        if (length <= 0) {
            throw new IllegalArgumentException("length(" + length + ") must be greater than zero");
        }

        Map<Integer, Long> shortWordCounts = words.filter(
                w -> !w.isEmpty() && w.length() < length).collect(
                        Collectors.groupingBy(String::length, Collectors.counting()));

        int[] result = new int[length];

        for (int i = 0; i < length; i++) {
            Long counts = shortWordCounts.get(i);
            result[i] = (counts == null) ? 0 : counts.intValue();
        }

        return result;
    }
}
