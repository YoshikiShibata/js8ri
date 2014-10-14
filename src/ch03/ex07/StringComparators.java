/*
 * Copyright (C) 2014 Yoshiki Shibata. All rights reserved.
 */
package ch03.ex07;

import java.util.Collections;
import java.util.Comparator;
import java.util.EnumSet;
import java.util.Objects;
import java.util.Set;

/**
 * Write a method that generates a Comparator<String> that can be normal or
 * reversed, case-sensitive or case-insensitive, space-sensitive or
 * space-insensitive, or any combination thereof. Your method should return a
 * lambda expression.
 *
 * Comparator<String>を生成するメソッドを書きなさい。普通の順序、逆順、大文字
 * 小文字を区別、大文字小文字を区別しない、空白を含める、空白を除外する、あるいは、
 * これらの組み合わせとすることができるComparator<String>にしなさい。メソッド は、ラムダ式を返すようにしなさい。
 */
public class StringComparators {

    private StringComparators() {
        // Non-instantiable
    }

    public enum Order {
        REVERSED, // Otherwise natural order
        CASE_INSENSITIVE, // Otherwise case-sensitive order
        SPACE_INSENSITIVE,  // Otherwise space-sensitive order
    }

    public static final Comparator<String> NATURAL_ORDER
            = create(Collections.emptySet());
    public static final Comparator<String> REVERSED_ORDER
            = create(EnumSet.of(Order.REVERSED));
    public static final Comparator<String> CASE_SENSITIVE_ORDER
            = NATURAL_ORDER;
    public static final Comparator<String> CASE_INSENSITIVE_ORDER
            = create(EnumSet.of(Order.CASE_INSENSITIVE));
    public static final Comparator<String> SPACE_SENSITIVE_ORDER
            = NATURAL_ORDER;
    public static final Comparator<String> SPACE_INSENSITIVE_ORDER
            = create(EnumSet.of(Order.SPACE_INSENSITIVE));

    /**
     * Returns a String comparator which has specified combined ordered.
     *
     * @param orders set of orders
     * @return a String comparator
     * @throws NullPointerException if orders is null
     */
    public static Comparator<String> create(Set<Order> orders) {
        Objects.requireNonNull(orders, "orders is null");
        
        boolean reversed = orders.contains(Order.REVERSED);
        boolean caseInsensitive = orders.contains(Order.CASE_INSENSITIVE);
        boolean spaceInsensitive = orders.contains(Order.SPACE_INSENSITIVE);
        
        return (o1, o2) -> {
            Objects.requireNonNull(o1);
            Objects.requireNonNull(o2);
            
            String s1 = o1;
            String s2 = o2;
            if (spaceInsensitive) {
                s1 = o1.replaceAll("\\s", "");
                s2 = o2.replaceAll("\\s", "");
            }
            
            if (reversed) {
                String tmp = s1;
                s1 = s2;
                s2 = tmp; 
            }
            
            return caseInsensitive ? s1.compareToIgnoreCase(s2): 
                                     s1.compareTo(s2);
        };
    }

}
