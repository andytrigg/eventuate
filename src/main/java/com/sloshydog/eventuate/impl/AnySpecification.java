package com.sloshydog.eventuate.impl;

import com.sloshydog.eventuate.api.Event;
import com.sloshydog.eventuate.api.EventSpecification;

public class AnySpecification implements EventSpecification {

    @Override
    public boolean matches(Event applicationEvent) {
        return true;
    }
}
