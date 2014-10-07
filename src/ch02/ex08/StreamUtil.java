/*
 * Copyright (C) 2014 Yoshiki Shibata. All rights reserved.
 */
package ch02.ex08;

import java.util.Iterator;
import java.util.Spliterator;
import java.util.function.Consumer;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

/**
 * Write a method public static <T> Stream<T> zip(Stream<T> first, Stream<T> second)
 * that alternates elements from the streams first and second, stopping when
 * one of them runs out of elements.
 * 
 * public static <T> Stream<T> zip(Stream<T> first, 　Stream<T>
 * second) メソッドを作成しなさい。そのメソッドは、ストリームであるfirst と
 * second から要素を交互に取り出し、どちらかのストリームから要素がなくなったら停止
 * します。
 */
public class StreamUtil {
    
    /**
     * Creates a Stream which alternates elements from the streams first and
     * second, stopping when one of them runs out of elements.
     * 
     * @param <T> type variable
     * @param first first stream
     * @param second second stream
     * @return a Stream which alternates elements from first and second.
     * @throws NullPointerException if either first of second is null.
     */
    public static <T> Stream<T> zip(Stream<T> first, Stream<T> second) {
        if (first == null || second == null)
            throw new NullPointerException(
                first == null ? "first is null" : "second is null");
        
        return StreamSupport.stream(new ZipSpliterator<T>(first, second), false);
    }
    
    private static class ZipSpliterator<T> implements Spliterator<T> {
        final private Iterator<T> first;
        final private Iterator<T> second;
        
        private boolean firstAvailable = false;
        private boolean secondAvailable = false;
        
        ZipSpliterator(Stream<T> first, Stream<T> second) {
            this.first = first.iterator();
            this.second = second.iterator();
        }

        @Override
        public boolean tryAdvance(Consumer<? super T> action) {
            if (!firstAvailable && !secondAvailable ) {
                firstAvailable = first.hasNext();
                secondAvailable = second.hasNext();
                if (!(firstAvailable && secondAvailable)) 
                    return false;
            }
                
            if (firstAvailable) {
                T next = first.next();
                firstAvailable = false;
                action.accept(next);
                return true;
            }
            
            T next = second.next();
            secondAvailable = false;
            action.accept(next);
            return true;
        }

        @Override
        public Spliterator<T> trySplit() {
            return null;
        }

        @Override
        public long estimateSize() {
            return Long.MAX_VALUE;
        }

        @Override
        public int characteristics() {
            return ORDERED | IMMUTABLE;
        }
    }
}
