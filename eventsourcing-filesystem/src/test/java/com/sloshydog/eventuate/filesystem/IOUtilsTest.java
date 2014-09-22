package com.sloshydog.eventuate.filesystem;

import org.junit.Test;
import org.mockito.Mockito;

import java.io.Closeable;
import java.io.IOException;

public class IOUtilsTest {

    @Test
    public void shouldBeAbleToCloseAClosable() throws IOException {
        Closeable closeable = Mockito.mock(Closeable.class);

        IOUtils.closeQuietly(closeable);

        Mockito.verify(closeable).close();
    }

    @Test
    public void shouldSwallowIOExceptionWhenClosingQuietly() throws IOException {
        Closeable closeable = Mockito.mock(Closeable.class);

        Mockito.doThrow(new IOException()).when(closeable).close();

        IOUtils.closeQuietly(closeable);
    }

    @Test
    public void shouldQuietlyIgnoreNullParameterToCloseQuietly() {
        IOUtils.closeQuietly(null);
    }

}