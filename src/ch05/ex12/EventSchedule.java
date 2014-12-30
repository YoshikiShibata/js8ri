/*
 * Copyright (C) 2014 Yoshiki Shibata. All rights reserved.
 */
package ch05.ex12;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Write a program that solves the problem described at the beginning of Section
 * 5.5, “Zoned Time,” on page 109. Read a set of appointments in different time
 * zones and alert the user which ones are due within the next hour in local
 * time.
 *
 * 127 ページの5.5 節「ゾーン付き時刻」の初めで説明した問題を解くプログラムを書きな
 * さい。そのプログラムでは、異なるタイムゾーンにある約束の集まりを読み込んで、ロー カル時刻で1 時間前に約束があることをユーザーに通知するようにしなさい。
 */
public final class EventSchedule {

    private final List<Event> events;
    private final Path eventsFile;
    private final Object lock = new Object();
    private final List<OneHourBeforeEventListener> listeners = new ArrayList<>();

    @FunctionalInterface
    public interface OneHourBeforeEventListener {

        void onOneHourBefore(Event event);
    }

    /**
     * Constructs an EventSchedule from the specified file(eventsFile). If the
     * eventsFile doesn't exists, assume there is no event initially.
     *
     * @param eventsFile path of the file where events will be saved.
     */
    public EventSchedule(Path eventsFile) {
        this.eventsFile = eventsFile;

        if (eventsFile.toFile().exists()) {
            events = load();
        } else {
            events = new ArrayList<>();
        }
        Thread t = new Thread(new OneHourNotifier());
        t.setDaemon(true);
        t.start();
    }

    private List<Event> readEventsFile(Path eventsFile) {
        throw new AssertionError("Not Implemented Yet");
    }

    public final void addEvent(Event event) {
        Objects.requireNonNull(event, "event is null");

        synchronized (lock) {
            int index = Collections.binarySearch(events, event);

            if (index > 0) {
                throw new IllegalArgumentException("duplicated event");
            }

            events.add(-(index + 1), event);
        }
    }

    public final void addOneHourBeforeEventListener(OneHourBeforeEventListener listener) {
        Objects.requireNonNull(listener, "listener is null");

        synchronized (lock) {
            listeners.add(listener);
        }
    }

    public final List<Event> events() {
        synchronized (lock) {
            return new ArrayList<>(events);
        }
    }

    public final void save() {
        synchronized (lock) {
            try (ObjectOutputStream oos = new ObjectOutputStream(Files.newOutputStream(eventsFile))) {
                oos.writeObject(events); // Serialize
            } catch (IOException ex) {
                Logger.getLogger(EventSchedule.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private List<Event> load() {
        synchronized (lock) {
            try (ObjectInputStream ois = new ObjectInputStream(Files.newInputStream(eventsFile))) {
                List<Event> events = (List<Event>) ois.readObject();
                return events;
            } catch (IOException ex) {
                Logger.getLogger(EventSchedule.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(EventSchedule.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        throw new AssertionError("Not Reachable");
    }

    private class OneHourNotifier implements Runnable {

        @Override
        public void run() {
            while (true) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException ex) {
                    Logger.getLogger(EventSchedule.class.getName()).log(Level.SEVERE, null, ex);
                }

                List<OneHourBeforeEventListener> copiedListeners = copyListeners();
                
                if (copiedListeners.isEmpty()) {
                    continue;
                }

                List<Event> events = calculateOneHourEvents();   

                for (Event event : events) {
                    for (OneHourBeforeEventListener listener : copiedListeners) {
                        listener.onOneHourBefore(event);
                    }
                }
            }
        }

        private List<Event> calculateOneHourEvents() {
            ZonedDateTime oneHourLater = ZonedDateTime.now().plusHours(1);
            List<Event> result = new ArrayList<>();
            synchronized (lock) {
                for (Iterator<Event> i = events.iterator(); i.hasNext();) {
                    Event event = i.next();
                    if (event.startTime().compareTo(oneHourLater) < 0) {
                        result.add(event);
                        i.remove();
                    }
                }
            }
            return result;
        }

        private List<OneHourBeforeEventListener> copyListeners() {
            synchronized (lock) {
                return new ArrayList<>(listeners);
            }
        }

    }
}
