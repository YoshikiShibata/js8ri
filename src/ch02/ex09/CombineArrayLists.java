/*
 * Copyright (C) 2014 Yoshiki Shibata. All rights reserved.
 */
package ch02.ex09;

import java.util.ArrayList;
import java.util.Optional;
import java.util.stream.Stream;

/**
 * Join all elements in a Stream<ArrayList<T>> to one ArrayList<T>. Show how to
 * do this with the three forms of reduce.
 *
 * Stream<ArrayList<T>>内のすべての要素を、1 つのArrayList<T>へまとめなさ い。具体的には、3 つの形式のreduce
 * を用いる方法を示しなさい。
 */
public class CombineArrayLists {

    private CombineArrayLists() {
        // non-instantiable
    }

    public interface ArrayOfStreamFlatter<T> {
        /**
         * Flat Stream<ArrayList<T>> into ArrayList<T>
         * 
         * @param stream stream to be flatted.
         * @return flatted list
         */
        ArrayList<T> flat(Stream<ArrayList<T>> stream);
    }

    public static <T> ArrayOfStreamFlatter<T> create1() {
        return new Flatter1<>();
    }
    
    private static class Flatter1<T> implements ArrayOfStreamFlatter<T> {

        @Override
        public ArrayList<T> flat(Stream<ArrayList<T>> stream) {
            
            Optional<ArrayList<T>> result = stream.reduce((x, y) -> {
                ArrayList<T> intermediate = new ArrayList<>();
                intermediate.addAll(x);
                intermediate.addAll(y);
                return intermediate;
            });
            
            return result.orElse(new ArrayList<>());
        }
        
    }

    public static <T> ArrayOfStreamFlatter<T> create2() {
        throw new AssertionError("Not Implmeneted Yet");
    }

    public static <T> ArrayOfStreamFlatter<T> create3() {
        throw new AssertionError("Not Implmeneted Yet");
    }
}
