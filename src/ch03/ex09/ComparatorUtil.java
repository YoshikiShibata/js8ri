/*
 * Copyright (C) 2014 Yoshiki Shibata. All rights reserved.
 */
package ch03.ex09;

import java.lang.reflect.Field;
import java.util.Comparator;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Write a method lexicographicComparator(String... fieldNames) that yields a
 * comparator that compares the given fields in the given order. For example, a
 * lexicographicComparator("lastname", "firstname") takes two objects and, using
 * reflection, gets the values of the lastname field. If they are different,
 * return the difference, otherwise move on to the firstname field. If all
 * fields match, return 0.
 *
 * 指定された順序で、指定されたフィールドを比較するコンパレータを生成する lexicographicComparator(String...
 * fieldNames) メソッドを書きなさい。 たとえば、lexicographicComparator("lastname", "firstname")
 * は、2 つ のオブジェクトを受け取り、リフレクションを使用して、lastname フィールドの値を 取得します。2 つのオブジェクトのlastname
 * フィールドが異なれば、その差を返しま す。同じであれば、firstname フィールドに移ります。すべてのフィールドが同じであ れば、0 を返します。
 */
public class ComparatorUtil {

    private ComparatorUtil() {
        // Non-instantiable
    }

    /**
     * Constructs a Comparator which compares the give fields in the given
     * order.
     *
     * @param <T> a type variable
     * @param fieldNames names of fields
     * @return a Comparator
     * @throws NullPointerException if fieldNames is null
     * @throws IllegalArgumentException if the length of fieldNames is zero.
     */
    public static <T> Comparator<? super T> lexicographicComparator(
            String... fieldNames) {
        Objects.requireNonNull(fieldNames, "fieldNames is null");

        if (fieldNames.length == 0) {
            throw new IllegalArgumentException("fieldNames is empty");
        }

        return (o1, o2) -> {
            Objects.requireNonNull(o1, "o1 is null");
            Objects.requireNonNull(o2, "o2 is null");
            
            String[] fields1 = new String[fieldNames.length];
            String[] fields2 = fields1.clone();

            getAllFields(fieldNames, fields1, o1);
            getAllFields(fieldNames, fields2, o2);

            for (int i = 0; i < fieldNames.length; i++) {
                int result = fields1[i].compareTo(fields2[i]);
                if (result != 0) {
                    return result;
                }
            }
            return 0;
        };
    }

    private static <T> void getAllFields(String[] fieldNames, String[] fields, T o) {
        Class<?> cl = o.getClass();

        for (int i = 0; i < fieldNames.length; i++) {
            try {
                Field f = cl.getDeclaredField(fieldNames[i]);
                f.setAccessible(true);
                fields[i] = (String) f.get(o);
            } catch (NoSuchFieldException | IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        }
    }

}
