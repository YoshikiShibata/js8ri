/*
 * Copyright (C) 2014 Yoshiki Shibata. All rights reserved.
 */
package ch02.ex08;

import java.util.stream.Stream;

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
        throw new AssertionError("Not Implemented Yet");
    }
}
