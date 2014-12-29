/*
 * Copyright (C) 2014 Yoshiki Shibata. All rights reserved.
 */
package ch05.ex08;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZoneOffset;

/**
 * Obtain the offsets of today’s date in all supported time zones for the
 * current time instant, turning ZoneId.getAvailableZoneIds() into a stream and
 * using stream operations.
 *
 * 現在の時刻インスタントに対してサポートされるすべてのタイムゾーンにお いて、今日の日付のオフセット（UTC との差）を取得しなさい。その際、
 * ZoneId.getAvailableIds をストリームへ変換してから、ストリーム操作を 使用することによって取得しなさい。
 */
public class ShowAllOffsets {
    
    public static void main(String[] args) {
        Instant now = Instant.now();
        
        ZoneId.getAvailableZoneIds().stream().forEach(
            zoneId -> {
                ZoneOffset offset = now.atZone(ZoneId.of(zoneId)).getOffset();
                System.out.printf("zoneID: %s offset: %s%n", zoneId, offset);
            });
    }

}
