/*
 * Copyright (C) 2014, 2020 Yoshiki Shibata. All rights reserved.
 */
package ch05.ex06;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;

/**
 * List all Friday the 13th in the twentieth century.
 * 
 * 20 世紀のすべての13 日の金曜日を列挙しなさい。
 */
public class AllFridays13Of20Century {
    
    private static final int START_YEAR = 1901;
    private static final int END_YEAR = 2000;
    
    public static void main(String[] args) {
        for (int year = START_YEAR; year <= END_YEAR; year++) {
            
            for (Month month: Month.values()) {
                LocalDate the13th = LocalDate.of(year, month, 13);
                
                if (the13th.getDayOfWeek() == DayOfWeek.FRIDAY)
                    System.out.println(the13th);
            }
        }  
    }

}
