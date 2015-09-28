/*
 * Copyright (C) 2014 Yoshiki Shibata. All rights reserved.
 */
package ch03.ex24;

/**
 * Can you define a flatMap method for Pair<T>? If so, what is it? If not, why
 * not?
 *
 * Pair<T>に対するflatMap メソッドを定義することができますか。できるとしたら、 それは何ですか。できないとしたら、その理由は何ですか。
 */
public class Answer {
    
    /*
    flatMap would be like this:
    
    <R> Pair<R> flatMap(Function<? extends T, Pair<R>> f)
    
    There are two values: first and second. For each value, a Pair will be 
    created. In other words, two Pair will be created; only one Pair cannot be returned.
    */

	/* Memo
	 * Optional<U> flatMap(Optional<T> o, Function<T, Optional<U>> f)
	 * Future<String> flatMap(Future<URL> f, Function<URL, Future<String>> f)
	 * Pair<U> flatMap(Pair<T> p, Function<T, Pair<U> f)
	 */
}
