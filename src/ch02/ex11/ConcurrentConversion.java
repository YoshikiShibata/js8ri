/*
 * Copyright (C) 2014 Yoshiki Shibata. All rights reserved.
 */
package ch02.ex11;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Stream;

/**
 * It should be possible to concurrently collect stream results in a single
 * ArrayList, instead of merging multiple array lists, provided it has been
 * constructed with the stream’s size, since concurrent set operations at
 * disjoint positions are threadsafe. How can you achieve that?
 *
 * 単一のArrayList がストリームの大きさと同じ大きさで生成されている場合、複数の ArrayList
 * をマージすることなく、その単一のArrayListにストリームの結果を並行して収集できるはずです。なぜなら、互いに異なる位置へ並行して行うset 操作であ
 * れば、スレッドセーフとなるからです。みなさんは、この収集をどうやって達成すること ができますか。
 */
public class ConcurrentConversion {

    private ConcurrentConversion() {
        // Non-instantiable
    }

    /**
     * Converts a stream into a list concurrently. But the order of the elements
     * is not kept. If the stream is empty, then a empty List will be returned.
     *
     * @param <T> type of the stream
     * @param stream stream to be converted into a list
     * @param size the size of the stream
     * @return a converted list
     * @throws NullPointerException if stream is null
     * @throws IllegalArgumentException if the size of stream is incorrect or
     * negative
     */
    public static <T> List<T> toList(Stream<T> stream, int size) {
        if (stream == null) {
            throw new NullPointerException("stream is null");
        }

        if (size < 0) {
            throw new IllegalArgumentException("size is negative: " + size);
        }

        AtomicInteger ai = new AtomicInteger();
        List<T> result = new ArrayList<>(size);
        for (int i = 0; i < size; i++)
            result.add(null);
        
        stream.parallel().forEach(x -> {
           int index = ai.getAndIncrement();
           if (index >= size)
               return;
           result.set(index, x);
        });
        
        if (ai.get() != size)
            throw new IllegalArgumentException("size is too large");
        
        return result;
    }
}
