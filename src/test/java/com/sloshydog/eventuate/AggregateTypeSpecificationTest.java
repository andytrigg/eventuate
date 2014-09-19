package com.sloshydog.eventuate;

import com.sloshydog.eventuate.api.EventSpecification;
import com.sloshydog.eventuate.impl.AggregateTypeSpecification;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class AggregateTypeSpecificationTest {
    @Test
    public void shouldBeASpecification() {
        assertThat(EventSpecification.class).isAssignableFrom(AggregateTypeSpecification.class);
    }

}