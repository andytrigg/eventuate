package com.sloshydog.eventuate.impl;

import com.sloshydog.eventuate.api.EventSpecification;

public class Specifications {

    private static final AnySpecification ANY_SPECIFICATION = new AnySpecification();

    private Specifications() {
    }

    public static EventSpecification any() {
        return ANY_SPECIFICATION;
    }
}
