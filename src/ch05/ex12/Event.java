/*
 * Copyright (C) 2014 Yoshiki Shibata. All rights reserved.
 */
package ch05.ex12;

import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.Objects;

/**
 * Class reprents a event. 
 */
public final class Event implements Serializable, Comparable<Event> {
    private static final long serialVersionUID = 1L;
    
    private final ZonedDateTime startTime;
    private final String event;
    
    /**
     * Constructs an instance with the specified start time and event.
     * @param startTime a ZonedDataTime representing the start time
     * @param event contents of this event.
     * @throws NullPointerException if either startTime or event is null.
     */
    public Event(ZonedDateTime startTime, String event) {
        Objects.requireNonNull(startTime, "startTime is null");
        Objects.requireNonNull(event, "event is null");
        
        this.startTime = startTime;
        this.event = event;
    }
    
    @Override
    public final boolean equals(Object o) {
        if (this == o)
            return true;
        
        if (!(o instanceof Event))
            return false;
        
        Event other = (Event)o;
        
        return startTime.equals(other.startTime) & event.equals(other.event);
    }
    
    @Override
    public final int hashCode() {
        return Objects.hash(startTime, event);
    }
    
    public final ZonedDateTime startTime() {
        return startTime;
    }
    
    public final String event() {
        return event;
    }

    @Override
    public int compareTo(Event o) {
        int diffOfStartTime = startTime.compareTo(o.startTime);
        
        if (diffOfStartTime < 0)
            return -1;
        else if (diffOfStartTime > 0)
            return 1;
        
        return event.compareTo(o.event);
    }
    
    @Override
    public final String toString() {
        return String.format("[%s, %s]", startTime, event);
    }
    
}
