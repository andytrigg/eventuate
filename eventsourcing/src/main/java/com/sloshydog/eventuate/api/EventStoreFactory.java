package com.sloshydog.eventuate.api;

import java.util.ServiceLoader;

public class EventStoreFactory {
    private static EventStoreFactory service;
    private ServiceLoader<IEventStoreFactory> loader;

    private EventStoreFactory() {
        loader = ServiceLoader.load(IEventStoreFactory.class);
    }

    public static synchronized EventStoreFactory getInstance() {
        if (service == null) {
            service = new EventStoreFactory();
        }
        return service;
    }

    public  EventStore getEventStore() {
        return loader.iterator().next().getEventStore();
    }
}
