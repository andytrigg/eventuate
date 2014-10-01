package com.sloshydog.eventuate.internal;

import com.sloshydog.eventuate.api.EventStore;

import java.util.Map;

public interface IEventStoreFactory {

    EventStore getEventStore(Map<Object, Object> configuration);
}
