/*
 * Copyright (C) 2015 Yoshiki Shibata. All rights reserved.
 */
package ch08.ex09;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

/**
 * Write methods that turn a Scanner into a stream of words, lines, integers, or
 * double values. Hint: Look at the source code for BufferedReader.lines.
 *
 * Scanner を単語、行、整数、あるいは、double 値のストリームへ変換するメソッドを 書きなさい。ヒント：BufferedReader.lines
 * のソースコードを調べなさい。
 */
public class ScannerUtil {

    private ScannerUtil() {

    }

    public static Stream<String> words(Scanner scanner) {
        Iterator<String> iter = new ScannerIterator<>(
                () -> scanner.hasNext(),
                () -> scanner.next());

        return StreamSupport.stream(Spliterators.spliteratorUnknownSize(
                iter, Spliterator.ORDERED | Spliterator.NONNULL), false);
    }

    public static Stream<String> lines(Scanner scanner) {
        Iterator<String> iter = new ScannerIterator<>(
                () -> scanner.hasNextLine(),
                () -> scanner.nextLine());

        return StreamSupport.stream(Spliterators.spliteratorUnknownSize(
                iter, Spliterator.ORDERED | Spliterator.NONNULL), false);
    }

    public static Stream<Integer> integers(Scanner scanner) {
        Iterator<Integer> iter = new ScannerIterator<>(
                () -> scanner.hasNextInt(),
                () -> scanner.nextInt());

        return StreamSupport.stream(Spliterators.spliteratorUnknownSize(
                iter, Spliterator.ORDERED | Spliterator.NONNULL), false);
    }

    public static Stream<Double> doubles(Scanner scanner) {
        Iterator<Double> iter = new ScannerIterator<>(
                () -> scanner.hasNextDouble(),
                () -> scanner.nextDouble());

        return StreamSupport.stream(Spliterators.spliteratorUnknownSize(
                iter, Spliterator.ORDERED | Spliterator.NONNULL), false);
    }

    @FunctionalInterface
    private interface HasNext {

        boolean hasNext();
    }

    @FunctionalInterface
    private interface Next<T> {

        T next();
    }

    private static class ScannerIterator<T> implements Iterator<T> {

        private final HasNext hasNextFunc;
        private final Next<T> nextFunc;
        private T nextValue;

        ScannerIterator(HasNext hasNextFunc, Next<T> nextFunc) {
            this.hasNextFunc = hasNextFunc;
            this.nextFunc = nextFunc;
        }

        @Override
        public boolean hasNext() {
            if (nextValue != null) {
                return true;
            }

            if (hasNextFunc.hasNext()) {
                nextValue = nextFunc.next();
                return true;
            }
            return false;
        }

        @Override
        public T next() {
            if (nextValue != null || hasNext()) {
                T value = nextValue;
                nextValue = null;
                return value;
            }
            throw new NoSuchElementException();
        }

    }

}
