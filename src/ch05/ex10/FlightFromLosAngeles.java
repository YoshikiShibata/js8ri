/*
 * Copyright (C) 2014 Yoshiki Shibata. All rights reserved.
 */
package ch05.ex10;

import java.time.ZoneId;
import java.time.ZonedDateTime;

/**
 * Your flight from Los Angeles to Frankfurt leaves at 3:05 pm local time and
 * takes 10 hours and 50 minutes. When does it arrive? Write a program that can
 * handle calculations like this.
 *
 * ロサンジェルスからフランクフルト行きの便は、ローカル時刻の3 時5 分に出発し、10 時間50
 * 分の飛行です。何時に到着しますか。このような計算を処理できるプログラムを 書きなさい。
 */
public class FlightFromLosAngeles {

    private static final String LOS_ANGELES = "America/Los_Angeles";
    private static final String FRANKFURT = "Europe/Berlin";

    public static void main(String[] args) {
        ZonedDateTime departure = ZonedDateTime.of(2014, 12, 30, 15, 5, 0, 0,
                ZoneId.of(LOS_ANGELES)
        );
        ZonedDateTime arrival = advance(departure, 60 * 10 + 50, FRANKFURT);
        
        System.out.println(departure);
        System.out.println(arrival);
    }

    private static ZonedDateTime advance(
            ZonedDateTime zdt, int minutes, String zoneId) {
        return zdt.plusMinutes(minutes).toInstant().atZone(ZoneId.of(zoneId));
    }

}
