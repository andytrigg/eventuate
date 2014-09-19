package com.sloshydog.eventuate.api;

public interface EventSpecification {
    boolean matches(Event applicationEvent);
}
