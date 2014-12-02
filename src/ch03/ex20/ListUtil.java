/*
 * Copyright (C) 2014 Yoshiki Shibata. All rights reserved.
 */
package ch03.ex20;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;

/**
 * Supply a static method <T, U> List<U> map(List<T>, Function<T, U>).
 * 
 * static メソッドである<T, U> List<U> map(List<T>, Function<T, U>) を提
供しなさい。
 */
public class ListUtil {
    
    private ListUtil() {
        // non-instatiable
    }
    
    /**
     * Converts all elements of the list by appling the function
     * 
     * @param <T> the type of the input list.
     * @param <U> the type of the converted element
     * @param list input list
     * @param f a function to be applied to each element of the list.
     * @return a converted list
     * @throws NullPointerException if either list or f is null.
     */
    public static <T, U> List<U> map(List<T> list, Function<T, U> f) {
        Objects.requireNonNull(list, "list is null");
        Objects.requireNonNull(f, "f is null");
        
        List<U> result = new ArrayList<>();
        
        for (T t: list) {
            result.add(f.apply(t));   
        }
        
        return result;
    }
    
}
