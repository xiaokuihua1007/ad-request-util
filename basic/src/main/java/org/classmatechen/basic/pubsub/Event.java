package org.classmatechen.basic.pubsub;

import lombok.Getter;

@Getter
public abstract class Event {

    private final String topic;

    public Event(String topic) {
        this.topic = topic;
    }

    public abstract void publish(Listener listener);
}
