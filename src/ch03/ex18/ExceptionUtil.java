/*
 * Copyright (C) 2014 Yoshiki Shibata. All rights reserved.
 */
package ch03.ex18;

import java.util.Objects;
import java.util.function.Function;

/**
 * Implement a version of the unchecked method in Section 3.8, “Dealing with
 * Exceptions,” on page 58, that generates a Function<T, U> from a lambda that
 * throws checked exceptions. Note that you will need to find or provide a
 * functional interface whose abstract method throws arbitrary exceptions.
 *
 * 71 ページの3.8 節「例外の取り扱い」のunchecked メソッドを次の内容に従って実装しなさい。
 *
 * 具体的には、チェックされる例外をスローするラムダ式からFunction<T,
 * U>を生成するようにしなさい。任意の例外をスローする抽象メソッドを持つ関数型インタフェースを見つけるか、作成する必要があることに注意しなさい。
 */
public class ExceptionUtil {

    /**
     * Converts FunctionWithException into Function.
     * 
     * @param <T> the ype of the input of the function
     * @param <U> the type of the result of the function
     * @param f function which throws a checked exception
     * @return function which throws a unchecked exception
     * @throws NullPointerException if f is null
     */
    public static <T, U> Function<T, U> unchecked(FunctionWithException<T, U> f) {
        Objects.requireNonNull(f, "f is null");
        
        return (u) -> {
            try {
                return f.apply(u);
            } catch (Exception e) {
                throw new RuntimeException(e);
            } catch (Throwable t) { // Throwable or Error
                throw t;
            }
        };
    }
}
