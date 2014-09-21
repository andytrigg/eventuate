package com.sloshydog.eventuate.filesystem;

import com.sloshydog.eventuate.filesystem.IOUtils;
import org.junit.Test;

import java.io.Closeable;
import java.io.IOException;

import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class IOUtilsTest {

    @Test
    public void shouldBeAbleToCloseAClosable() throws IOException {
        Closeable closeable = mock(Closeable.class);

        IOUtils.closeQuietly(closeable);

        verify(closeable).close();
    }

    @Test
    public void shouldSwallowIOExceptionWhenClosingQuietly() throws IOException {
        Closeable closeable = mock(Closeable.class);

        doThrow(new IOException()).when(closeable).close();

        IOUtils.closeQuietly(closeable);
    }

    @Test
    public void shouldQuietlyIgnoreNullParameterToCloseQuietly() {
        IOUtils.closeQuietly(null);
    }

}