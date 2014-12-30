/*
 * Copyright (C) 2014 Yoshiki Shibata. All rights reserved.
 */
package ch06.ex06;

import java.io.File;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import js8ri.util.FileUtil;

/**
 * Repeat the preceding exercise, but use computeIfAbsent instead. What is the
 * advantage of this approach?
 *
 * マップを更新するメソッドとして、merge の代わりにcomputeIfAbsent を使用して、 練習問題5
 * と同じアプリケーションを作成しなさい。この方法の利点は何ですか。
 */
public class ParallelWordIndexing {
private final static String ALICE = "alice.txt";
    private final static String WAR_AND_PEACE = "WarAndPeace.txt";

    private final static ConcurrentHashMap<String, Set<File>> cMap
            = new ConcurrentHashMap<>();

    public static void main(String[] args) {
        Thread t1 = new Thread(new WordIndexing(cMap, ALICE));
        Thread t2 = new Thread(new WordIndexing(cMap, WAR_AND_PEACE));
        
        t1.start();
        t2.start();
        try {
            t1.join();
            t2.join();
        } catch (InterruptedException ex) {
            Logger.getLogger(ch06.ex05.ParallelWordIndexing.class.getName()).log(Level.SEVERE, null, ex);
        }

        System.out.printf("map size = %d%n", cMap.size());
        long oneFileCount = 0;
        long twoFileCount = 0;

        for (Map.Entry<String, Set<File>> entry : cMap.entrySet()) {
            String word = entry.getKey();
            Set<File> set = entry.getValue();
            if (set.size() == 1) {
                oneFileCount++;
            } else if (set.size() == 2) {
                twoFileCount++;
            } else {
                throw new AssertionError("Bug!!");
            }
        }
        System.out.printf("one file: %d, two files: %d%n", oneFileCount, twoFileCount);
    }

    private static class WordIndexing implements Runnable {

        private final ConcurrentHashMap<String, Set<File>> map;
        private final File file;
        private final List<String> words;

        WordIndexing(ConcurrentHashMap<String, Set<File>> map, String fileName) {
            this.map = map;
            this.file = new File(fileName);
            this.words = FileUtil.readAsWords(fileName);
        }

        @Override
        public void run() {
            // This implementation saves a lot of garbages(Set instances)
            for (String word : words) {
                Set<File> set = map.get(word);
                if (set != null) {
                    set.add(file);
                    continue;
                }
                    
                set = ConcurrentHashMap.newKeySet();
                set.add(file);
                set = map.putIfAbsent(word, set);
                if (set != null)
                    set.add(file);
            }
        }
    }
}
