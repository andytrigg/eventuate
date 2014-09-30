package com.sloshydog.eventuate.api;

import java.util.Properties;
import java.util.ServiceLoader;

public final class EventStoreFactory {

    private static EventStoreFactory service;
    private final ServiceLoader<IEventStoreFactory> loader;
    private final Properties configurationProperties = new Properties();

    private EventStoreFactory() {
        loader = ServiceLoader.load(IEventStoreFactory.class);
    }

    public static synchronized EventStoreFactory getInstance() {
        if (service == null) {
            service = new EventStoreFactory();
        }
        return service;
    }

    public EventStoreFactory withProperty(String key, String value) {
        configurationProperties.put(key, value);
        return this;
    }

    public EventStore getEventStore() {

        return loader.iterator().next().getEventStore(configurationProperties);
    }
}
