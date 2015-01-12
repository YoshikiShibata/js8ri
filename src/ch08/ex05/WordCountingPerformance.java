/*
 * Copyright (C) 2015 Yoshiki Shibata. All rights reserved.
 */
package ch08.ex05;

import java.util.List;
import java.util.function.Predicate;
import js8ri.util.FileUtil;

/**
 * At the beginning of Chapter 2, we counted long words in a list as
 * words.stream().filter(w -> w.length() > 12).count(). Do the same with a
 * lambda expression, but without using streams. Which operation is faster for a
 * long list?
 *
 * 第2 章の初めで、リスト内の長い単語をwords.stream().filter(w -> w.length() > 12).count()
 * で数えました。ラムダ式を用いて、ストリームを使用 しないで、同じことを行いなさい。長いリストに対してはどちらの操作が速いですか。
 */
public class WordCountingPerformance {
    private static final int TRIAL_COUNT = 10;
    
    
    public static void main(String[] args) throws Exception {
        List<String> words = FileUtil.readAsWords("WarAndPeace.txt");

        withStream(words);
        withFor(words);
        withForEach(words);
    }
    
    public static void withStream(List<String> words) {
        measurePerformance(() -> {
            words.stream().filter(w -> w.length() > 12).count();
        });
    }
    
    public static void withFor(List<String> words) {
        Predicate<String> p = w -> w.length() > 12;
        measurePerformance(()-> {
            long count = 0;
            for (String w: words) {
                if (p.test(w))
                    count++;
            }
        });
    }
    
    public static void withForEach(List<String> words) {
        measurePerformance(() -> {
                        long[] count = new long[1];
            words.forEach(w -> {
                if (w.length() > 12)
                    count[0]++;
            });
        });
    }
    
    private static void measurePerformance(Runnable r) {
        double total = 0.0;
        for (int i = 0; i < TRIAL_COUNT; i++) {
            long start = System.nanoTime();
            r.run();
            total += System.nanoTime() - start;
        }
        System.out.printf("%f%n", (total / TRIAL_COUNT) / 1000_1000);
    }
 }
