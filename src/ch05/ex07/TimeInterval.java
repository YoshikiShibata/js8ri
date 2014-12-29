/*
 * Copyright (C) 2014 Yoshiki Shibata. All rights reserved.
 */
package ch05.ex07;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 * Implement a TimeInterval class that represents an interval of time, suitable
 * for calendar events (such as a meeting on a given date from 10:00 to 11:00).
 * Provide a method to check whether two intervals overlap.
 *
 * （指定された日付の午前10 時から午前11 時といった）カレンダーイベントに適した、時 刻のインターバルを表すTimeInterval
 * クラスを実装しなさい。2 つのインターバルが 重なっているかを検査するメソッドを提供しなさい。
 */
public final class TimeInterval {

    private final LocalDateTime start;
    private final LocalDateTime end;

    /**
     * Constructs a time interval.
     *
     * @param start start date and time (inclusive)
     * @param end end date and time (exculsive)
     * @IllegalArgumentException if start is after end
     */
    public TimeInterval(LocalDateTime start, LocalDateTime end) {
        Objects.requireNonNull(start, "start is null");
        Objects.requireNonNull(end, "end is null");
        
        if (start.compareTo(end) >= 0) {
            throw new IllegalArgumentException("end is before start");
        }

        this.start = start;
        this.end = end;
    }

    @Override
    public final boolean equals(Object other) {
        if (this == other) {
            return true;
        }

        if (!(other instanceof TimeInterval)) {
            return false;
        }

        TimeInterval o = (TimeInterval) other;

        return start.equals(o.start) && end.equals(o.end);
    }

    @Override
    public final int hashCode() {
        return Objects.hash(start, end);
    }
    
    /**
     * Tests if this TimeInterval and other TimeInterval is overlapped.
     * @param other other TimeInterval
     * @return true if two TimeIntervals is overlapped.
     * @throws NullPointerException if other is null.
     */
    public final boolean isOverlapped(TimeInterval other) {
        Objects.requireNonNull(other, "other is null");
        
        if (start.compareTo(other.end) >= 0 )
            return false;
        
        return end.compareTo(other.start) > 0;
    }

}
