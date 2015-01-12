/*
 * Copyright (C) 2015 Yoshiki Shibata. All rights reserved.
 */
package ch08.ex07;

import java.util.Comparator;

/**
 * Express nullsFirst(naturalOrder()).reversed() without calling reversed.
 *
 * reversed を呼び出すことなく、nullsFirst(naturalOrder()).reversed() を表現しなさい。
 */
public class ComparatorUtil {

    private ComparatorUtil() {

    }

    public static <T extends Comparable<? super T>>
            Comparator<T> reversedNullsLast() {
        return Comparator.nullsLast(Comparator.reverseOrder());
    }

}
