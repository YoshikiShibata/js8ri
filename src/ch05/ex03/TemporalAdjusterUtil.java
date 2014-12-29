package ch05.ex03;


import java.time.LocalDate;
import java.time.temporal.TemporalAdjuster;
import java.util.Objects;
import java.util.function.Predicate;

/*
 * Copyright (C) 2014 Yoshiki Shibata. All rights reserved.
 */

/**
 * Implement a method next that takes a Predicate<LocalDate> and returns an
 * adjuster yielding the next date fulfilling the predicate. For example,
 *
 * <pre>
 * today.with(next(w -> getDayOfWeek().getValue() < 6))
 * </pre>
 *
 * computes the next workday.
 *
 * Predicate<LocalDate>を受け取り、TemporalAdjuster を返すnext メソッド
 * を実装しなさい。返されたTemporalAdjuster は、next メソッドに渡された述語を
 * 満足する翌日の日付を生成します。たとえば、次のコードを見てください。
 * <pre>
 * today.with(next(w -> getDayOfWeek().getValue() < 6))
 * </pre> 
 * このコードは、今日より後にあって平日となる最初の日付を返します。
 */
public class TemporalAdjusterUtil {
    
    private TemporalAdjusterUtil() {
        // non-instantiable
    }
    
    /**
     * Returns a TemporalAdjuster which returns the next date fulfilling the predicate.
     * @param p Predicate
     * @return  a TemporalAdjuster
     * @throws NullPointerException if p is null
     */
    static public TemporalAdjuster next(Predicate<LocalDate> p) {
        Objects.requireNonNull(p, "p is null");
        
        return w -> {
            LocalDate result = (LocalDate) w;
            do {
                result = result.plusDays(1);
            } while (!p.test(result));
            return result;
        };  
    }

}
