/*
 * Copyright (C) 2014 Yoshiki Shibata. All rights reserved.
 */
package ch02.ex07;

import java.util.stream.Stream;

/**
 * Your manager asks you to write a method public static <T> boolean
 * isFinite(Stream<T> stream). Why isn’t that such a good idea? Go ahead and
 * write it anyway.
 *
 * 上司が、メソッドとしてpublic static <T> boolean isFinite( Stream<T>
 * stream) を作成するように求めています。それは、よくない考えでしょうか。まずは作 成してから、考えてみなさい。
 */
public class StreamUtil {

    public static <T> boolean isFinite(Stream<T> stream) {
        // Bascially Stream is an interface, so it is impossible to determine
        // if it is infinite or not. Any infinite stream provided by 
        // java.util.stream package might be detected by inspecting their
        // implementation class, but it is still not a correct solution.
        throw new UnsupportedOperationException("Cannot be implemented");
    }

}
