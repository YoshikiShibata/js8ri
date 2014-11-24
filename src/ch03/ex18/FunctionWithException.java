/*
 * Copyright (C) 2014 Yoshiki Shibata. All rights reserved.
 */
package ch03.ex18;

/**
 * Represents a function that accepts one argument and produces a result, and
 * possibly throws an checked exception.
 * 
 * This is a functional interface whose functional method is apply(Object).
 * 
 * @param <T> the type of the input of the function
 * @param <U> the type of the result of the function
 */
@FunctionalInterface
public interface FunctionWithException<T, U> {
    
    /**
     * Applies this function to the given argument.
     * 
     * @param t the function argument
     * @return the function result
     * @throws Exception if something illegal occurs
     */
    U apply(T t) throws Exception;
}
