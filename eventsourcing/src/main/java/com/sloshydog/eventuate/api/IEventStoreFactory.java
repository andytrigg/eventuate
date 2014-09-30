package com.sloshydog.eventuate.api;

import java.util.Map;

public interface IEventStoreFactory {

    EventStore getEventStore(Map<Object, Object> configuration);
}
