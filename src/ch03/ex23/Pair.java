/*
 * Copyright (C) 2014 Yoshiki Shibata. All rights reserved.
 */
package ch03.ex23;

import java.util.function.Function;

/**
 * Define a map operation for a class Pair<T> that represents a pair of objects
 * of type T.
 *
 * T 型の対となる2 つのオブジェクトを表すPair<T>クラスに対するmap 操作を定義し なさい。
 */
public class Pair<T> {
    private final T first;
    private final T second;
    
    public Pair(T first, T second) {
        this.first = first;
        this.second = second;
    }
    
    public T first() {
        return first;
    }
    
    public T second() {
        return second;
    }
    
    public <U> Pair<U> map(Function<T, ? extends U> f) {
        return new Pair<>(f.apply(first), f.apply(second));
    }
}
