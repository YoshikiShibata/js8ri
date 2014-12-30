/*
 * Copyright (C) 2014 Yoshiki Shibata. All rights reserved.
 */
package ch05.ex12;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author yoshiki
 */
public class EventScheduleTest {

    private final static String EVENTS_FILE = "events_file";
    private final Path eventsFile = Paths.get(EVENTS_FILE);
    
    private class MyEventListener implements EventSchedule.OneHourBeforeEventListener {
        private Event event = null;
        private final long TIME_OUT = 10 * 1000;

        @Override
        public synchronized void onOneHourBefore(Event event) {
            while (this.event != null) {
                try {
                    wait();
                } catch (InterruptedException ex) {
                    Logger.getLogger(EventScheduleTest.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            this.event = event;
            notifyAll(); 
        }
        
        public synchronized Event waitForEvent() {
            long startTime = System.currentTimeMillis();
            while (event == null) {
                try {
                    wait(TIME_OUT);
                    long currentTime = System.currentTimeMillis();
                    if ((currentTime - startTime) > TIME_OUT)
                        return null;
                } catch (InterruptedException ex) {
                    Logger.getLogger(EventScheduleTest.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            Event result = event;
            event = null;
            notifyAll();
            return result;  
        }
        
    }

    @Test
    public void simpleInstantiationWithoutFile() {
        deleteEventsFile();

        EventSchedule sv = new EventSchedule(eventsFile);
    }

    private void deleteEventsFile() {
        try {
            Files.delete(eventsFile);
        } catch (NoSuchFileException e) {
            // do nothing
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void addOneManyEvents() {
        final int numberOfEvents = 100;

        deleteEventsFile();

        EventSchedule es = new EventSchedule(eventsFile);
        ZonedDateTime zdt = ZonedDateTime.now();

        for (int i = 0; i < numberOfEvents; i++) {
            Event event = new Event(zdt, String.format("Event %d", i));
            es.addEvent(event);
            zdt = zdt.plusSeconds(1);
        }

        List<Event> events = es.events();
        assertTrue(events.size() == numberOfEvents);

        for (int i = 0; i < (events.size() - 1); i++) {
            Event event = events.get(i);
            Event nextEvent = events.get(i + 1);
            if (event.compareTo(nextEvent) >= 0) {
                fail("not soreted");
            }
        }
    }

    @Test
    public void saveLoadEvents() {
        // Prepare
        final int numberOfEvents = 100;

        deleteEventsFile();

        EventSchedule es = new EventSchedule(eventsFile);
        ZonedDateTime zdt = ZonedDateTime.now();

        for (int i = 0; i < numberOfEvents; i++) {
            Event event = new Event(zdt, String.format("Event %d", i));
            es.addEvent(event);
            zdt = zdt.plusSeconds(1);
        }

        // action
        es.save();
        es = new EventSchedule(eventsFile); // load again.

        // check
        List<Event> events = es.events();
        assertTrue(events.size() == numberOfEvents);

        for (int i = 0; i < (events.size() - 1); i++) {
            Event event = events.get(i);
            Event nextEvent = events.get(i + 1);
            if (event.compareTo(nextEvent) >= 0) {
                fail("not soreted");
            }
        }
    }
    
    @Test
    public void eventNotifiers() {
        // Prepare
         deleteEventsFile();
         EventSchedule es = new EventSchedule(eventsFile);
         MyEventListener listener = new MyEventListener();
         es.addOneHourBeforeEventListener(listener);
         
         ZonedDateTime zdt = ZonedDateTime.now();
         Event event = new Event(zdt.plusMinutes(30), "30 minutes later");
         es.addEvent(event);
         
         // Action
         Event notifiedEvent = listener.waitForEvent();
         
         // Check
         assertNotNull(notifiedEvent);
         
         es.addEvent(event);
         notifiedEvent = listener.waitForEvent();
         assertNotNull(notifiedEvent);
         
         notifiedEvent = listener.waitForEvent();
         assertNull(notifiedEvent);
         
    }

}
