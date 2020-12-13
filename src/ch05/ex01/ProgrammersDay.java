/*
 * Copyright (C) 2014, 2020 Yoshiki Shibata. All rights reserved.
 */
package ch05.ex01;

import java.time.LocalDate;
import java.time.Period;

/**
 * Compute Programmer’s Day without using plusDays.
 *
 * <p>plusDays を使用しないで、プログラマーの日を計算しなさい。
 */
public class ProgrammersDay {

  public static void main(String[] args) {
    int thisYear = LocalDate.now().getYear();

    LocalDate programmersDay = LocalDate.of(thisYear, 1, 1).plusDays(255);
    System.out.println(programmersDay);

    programmersDay = LocalDate.of(thisYear, 1, 1).plus(Period.ofDays(255));
    System.out.println(programmersDay);
  }
}
