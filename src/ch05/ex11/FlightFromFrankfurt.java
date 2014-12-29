/*
 * Copyright (C) 2014 Yoshiki Shibata. All rights reserved.
 */
package ch05.ex11;

import java.time.ZoneId;
import java.time.ZonedDateTime;

/**
 * Your return flight leaves Frankfurt at 14:05 and arrives in Los Angeles at
 * 16:40. How long is the flight? Write a program that can handle calculations
 * like this.
 *
 * 帰りの便は、フランクフルトを14 時5 分に出発し、ロサンジェルスに16 時40 分に到着
 * します。飛行時間は、何時間何分ですか。このような計算を処理できるプログラムを書き なさい。
 */
public class FlightFromFrankfurt {

    private static final String LOS_ANGELES = "America/Los_Angeles";
    private static final String FRANKFURT = "Europe/Berlin";

    public static void main(String[] args) {
        ZonedDateTime departure = ZonedDateTime.of(
                2014, 12, 30, 14, 5, 0, 0, ZoneId.of(FRANKFURT));
        ZonedDateTime arrival = ZonedDateTime.of(
                2014, 12, 30, 16, 40, 0, 0, ZoneId.of(LOS_ANGELES));
        System.out.println(departure);
        System.out.println(arrival);
        long flightTimeInSeconds = arrival.toEpochSecond() - departure.toEpochSecond();
        System.out.printf("%d hours %d minutes%n",
                flightTimeInSeconds / 3600,
                (flightTimeInSeconds % 3600) / 60);
    }

}
