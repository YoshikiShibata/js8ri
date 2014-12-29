/*
 * Copyright (C) 2014 Yoshiki Shibata. All rights reserved.
 */
package ch05.ex05;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

/**
 * Write a program that prints how many days you have been alive.
 *
 * 今までに、あなたが生きてきた日数を表示するプログラムを書きなさい。
 */
public class NumberOfDaysSinceBirthday {

    public static void main(String[] args) {
        if (args.length != 3) {
            showUsage();
            System.exit(1);
        }
        
        int year = Integer.parseInt(args[0]);
        int month = Integer.parseInt(args[1]);
        int day = Integer.parseInt(args[2]);
        
        LocalDate birthday = LocalDate.of(year, month, day);
        LocalDate now = LocalDate.now();
        System.out.printf("Numer of days since you were born(%s): %d days%n",
                birthday,
                birthday.until(now, ChronoUnit.DAYS));

    }

    private static void showUsage() {
        System.out.println("usage: NumberOfDaysSinceBirthday year month day");
    }

}
