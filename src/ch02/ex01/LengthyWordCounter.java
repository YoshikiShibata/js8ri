/*
 * Copyright (C) 2014 Yoshiki Shibata. All rights reserved.
 */
package ch02.ex01;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Write a parallel version of the for loop in Section 2.1, “From Iteration to
 * Stream Operations,” on page 22. Obtain the number of processors. Make that
 * many separate threads, each working on a segment of the list, and total up
 * the results as they come in. (You don’t want the threads to update a single
 * counter. Why?)
 *
 * 28 ページの2.1 節「イテレーションからストリーム操作へ」のfor ループの並列バー
 * ジョンを書きなさい。リストのセグメントごとに処理を行う別々のスレッドを多数生成
 * し、処理が終わるごとに結果を合計するようにしなさい（みなさんは、単一カウンターを 更新するためにスレッドを使用したくはないでしょう。なぜですか）。
 */
public class LengthyWordCounter {

    private final int minLength;
    private final List<CountingThread> threads = new ArrayList<>();

    /**
     * Constructs an instance.
     *
     * @param minLength minimum length of a word
     */
    public LengthyWordCounter(int minLength) {
        this.minLength = minLength;
    }

    /**
     * Counts the number of words whose length is greater than the ninimum
     * length.
     *
     * @param words a list of words
     * @return number of words whose legnth is greater than the minimum length.
     */
    int count(List<String> words) {
        int noOfWords = words.size();
        int cores = Runtime.getRuntime().availableProcessors();
        int segmentSize = noOfWords / cores + cores; // little larger
        
        for (int i = 0; i < noOfWords; i += segmentSize) {
            int startIndex = i;
            int endIndex = i + segmentSize;
            
            if ((i + segmentSize) >= noOfWords) 
                endIndex = noOfWords;

            CountingThread ct = new CountingThread(words.subList(startIndex, endIndex), minLength);
            ct.start();
            threads.add(ct);
        }
        System.out.println("No of Threads = " + threads.size());
        int result = 0;
        for (CountingThread ct: threads) {
            try {
                ct.join();
            } catch (InterruptedException ex) {
                Logger.getLogger(LengthyWordCounter.class.getName()).log(Level.SEVERE, null, ex);
            }
            result += ct.count();
        }
        return result;
    }

    private static class CountingThread extends Thread {
        private final List<String> words;
        private final int minLength;
        private int count = 0;
        
        CountingThread(List<String> words, int minLength) {
            this.words = words;
            this.minLength = minLength;
        }
        
        @Override
        public void run() {
            for (String w: words) {
                if (w.length() > minLength)
                    count ++;
            }
        }
        
        int count() {
            return this.count;
        }
    }
}
