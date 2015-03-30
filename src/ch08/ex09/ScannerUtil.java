/*
 * Copyright (C) 2015 Yoshiki Shibata. All rights reserved.
 */
package ch08.ex09;

import java.util.Iterator;
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
        throw new AssertionError("Not Implemented Yet");
    }

    public static Stream<String> lines(Scanner scanner) {
        Iterator<String> iter = new Iterator<String>() {

            @Override
            public boolean hasNext() {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public String next() {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
            
        };
        return StreamSupport.stream(Spliterators.spliteratorUnknownSize(
                iter, Spliterator.ORDERED | Spliterator.NONNULL), false);
    }

    public static Stream<Integer> integers(Scanner scanner) {
        throw new AssertionError("Not Implemented Yet");
    }

    public static Stream<Double> doubles(Scanner scanner) {
        throw new AssertionError("Not Implemented Yet");
    }
    
}
