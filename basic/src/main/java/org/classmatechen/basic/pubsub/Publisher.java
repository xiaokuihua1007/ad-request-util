package org.classmatechen.basic.pubsub;

import java.util.ArrayList;
import java.util.List;

public class Publisher {

    private final List<Listener> listeners = new ArrayList<>();

    private final static Publisher publisher = new Publisher();

    public static void publish(Event event) {

        for (Listener listener : publisher.listeners) {

            try {
                event.publish(listener);
            } catch (Exception e) {

            }
        }
    }

    public static void subscribe(Listener listener) {

        publisher.listeners.add(listener);
    }
}
