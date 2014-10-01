package com.sloshydog.eventuate.inmemory;

import com.sloshydog.eventuate.api.EventStore;
import com.sloshydog.eventuate.internal.IEventStoreFactory;

import java.util.Map;

public class InMemoryEventStoreFactory implements IEventStoreFactory {
    @Override
    public EventStore getEventStore(Map<Object, Object> configuration) {
        return new InMemoryEventStore();
    }
}
