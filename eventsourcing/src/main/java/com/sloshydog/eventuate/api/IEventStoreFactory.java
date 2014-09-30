package com.sloshydog.eventuate.api;

public interface IEventStoreFactory {
    EventStore getEventStore();
}
