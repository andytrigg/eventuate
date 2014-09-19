package com.sloshydog.eventuate.impl;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class SpecificationsTest {
    @Test
    public void shouldBeAbleToGetAnySpecification() {
        assertThat(Specifications.any()).isInstanceOf(AnySpecification.class);
    }

    @Test
    public void shouldBeAbleToGetAggregateTypeSpecification() {
        assertThat(Specifications.aggregateTypeMatching("someType")).isInstanceOf(AggregateTypeSpecification.class);
    }


}