/*
 * Copyright (C) 2014 Yoshiki Shibata. All rights reserved.
 */
package ch05.ex09;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZoneOffset;

/**
 * Again using stream operations, find all time zones whose offsets aren’t full
 * hours.
 *
 * 再度、ストリーム操作を使用して、オフセットに1 時間未満の情報が含まれるすべてのタ イムゾーンを見つけなさい。
 */
public class ShowNotHourAlignedOffsets {
public static void main(String[] args) {
        Instant now = Instant.now();
        
        ZoneId.getAvailableZoneIds().stream().forEach(
            zoneId -> {
                ZoneOffset offset = now.atZone(ZoneId.of(zoneId)).getOffset();
                if ((offset.getTotalSeconds() % 3600) != 0)
                    System.out.printf("zoneID: %s offset: %s%n", zoneId, offset);
            });
    }
}
