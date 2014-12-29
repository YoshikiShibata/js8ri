/*
 * Copyright (C) 2014 Yoshiki Shibata. All rights reserved.
 */
package ch05.ex01;

import java.time.LocalDate;
import java.time.Month;
import java.time.Period;

/**
 * Compute Programmer’s Day without using plusDays.
 *
 * plusDays を使用しないで、プログラマーの日を計算しなさい。
 */
public class ProgrammersDay {

    public static void main(String[] args) {
        LocalDate programmersDay = LocalDate.of(2014, 1, 1).plusDays(255);
        System.out.println(programmersDay);
        
        programmersDay = LocalDate.of(2014, 1, 1).plus(Period.ofDays(255));
        System.out.println(programmersDay);
    }
}
