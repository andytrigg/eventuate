package com.sloshydog.eventuate.inmemory;

import com.sloshydog.eventuate.api.EventStore;
import com.sloshydog.eventuate.api.IEventStoreFactory;

public class InMemoryEventStoreFactory implements IEventStoreFactory {
    @Override
    public EventStore getEventStore() {
        return new InMemoryEventStore();
    }
}
