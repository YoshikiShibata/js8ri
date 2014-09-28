/*
 * Copyright (C) 2014 Yoshiki Shibata. All rights reserved.
 */
package ch01.ex09;

import java.util.Collection;
import java.util.function.Consumer;
import java.util.function.Predicate;

/**
 * Form a subinterface Collection2 from Collection and add a default method void
 * forEachIf(Consumer<T> action, Predicate<T> filter) that applies action to
 * each element for which filter returns true. How could you use it?
 *
 * Collection のサブインタフェースである Collection2 を作成して、デフォル ト メ ソ ッ ド と し て void
 * forEachIf(Consumer<T> action, Predicate<T> filter) を追加しなさい。 そのメソッドは、filter
 * が true を返してきた個々の 要素に対して action を適用します。 どのような場面で、そのメソッドを活用できるで しょうか。
 * 
 * Answer: it is not clear to me when forEachIf is useful.
 */
public interface Collection2<T> extends Collection<T> {

    default void forEachIf(Consumer<T> action, Predicate<T> filter) {
        forEach(t -> {
            if (filter.test(t)) {
                action.accept(t);
            }
        });
    }
}
