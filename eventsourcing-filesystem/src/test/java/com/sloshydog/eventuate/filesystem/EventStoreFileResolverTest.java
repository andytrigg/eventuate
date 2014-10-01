package com.sloshydog.eventuate.filesystem;

import com.sloshydog.eventuate.api.EventSpecification;
import org.assertj.core.api.Assertions;
import org.junit.Test;

import java.io.File;

public class EventStoreFileResolverTest {

    @Test(expected = NullPointerException.class)
    public void shouldThrowExceptionIfBaseDirectoryIsNUll() {
        new EventStoreFileResolver(null);
    }

    @Test
    public void should() {
        EventSpecification eventSpecification = new EventSpecification("type", "123");

        File baseDirectory = new File(System.getProperty("java.io.tmpdir"));
        File actualFile = new EventStoreFileResolver(baseDirectory).getFileFor(eventSpecification.getAggregateType(), eventSpecification.getAggregateIdentifier());

        Assertions.assertThat(actualFile).isEqualTo(new File(baseDirectory, "type/123.evt"));
    }
}
