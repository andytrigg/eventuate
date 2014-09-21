package com.sloshydog.eventuate.filesystem;

import com.sloshydog.eventuate.api.EventSpecification;
import org.junit.Test;

import java.io.File;

import static org.assertj.core.api.Assertions.assertThat;

public class EventStoreFileResolverTest {

    @Test(expected = NullPointerException.class)
    public void shouldThrowExceptionIfBaseDirectoryIsNUll() {
        new EventStoreFileResolver(null);
    }

    @Test
    public void should() {
        EventSpecification eventSpecification = new EventSpecification("123", "type");

        File baseDirectory = new File(System.getProperty("java.io.tmpdir"));
        File actualFile = new EventStoreFileResolver(baseDirectory).getFileFor(eventSpecification);

        assertThat(actualFile).isEqualTo(new File(baseDirectory, "type/123.evt"));
    }
}