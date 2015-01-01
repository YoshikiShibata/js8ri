/*
 * Copyright (C) 2014 Yoshiki Shibata. All rights reserved.
 */
package ch06.ex07;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * In a ConcurrentHashMap<String, Long>, find the key with maximum value
 * (breaking ties arbitrarily). Hint: reduceEntries.
 *
 * ConcurrentHashMap<String, Long>内で、最大値を持つキーを見つけなさ
 * い（同じ最大値を持つキーがあれば、どちらのキーでもかまいません）。ヒント： reduceEntries。
 */
public class StringWithLongMapUtil {

    private StringWithLongMapUtil() {
        // non-instantiable
    }

    public static Map.Entry<String, Long> max(
            ConcurrentHashMap<String, Long> map) {
        return map.reduceEntries(10, (e1, e2) -> {
            return e1.getValue() > e2.getValue() ? e1: e2;
        });
    }
}
