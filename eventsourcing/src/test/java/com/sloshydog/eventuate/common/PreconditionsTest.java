package com.sloshydog.eventuate.common;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.failBecauseExceptionWasNotThrown;

public class PreconditionsTest {
    @Test
    public void shouldThrowWhenStringArgumentIsNull() {
        try {
            Preconditions.checkArgumentProvided(null, "argument");
            failBecauseExceptionWasNotThrown(IllegalArgumentException.class);
        } catch (IllegalArgumentException e) {
            assertThat(e).hasMessage("argument is null");
        }
    }

    @Test
    public void shouldThrowWhenStringArgumentIsEmpty() {
        try {
            Preconditions.checkArgumentProvided("", "argument");
            failBecauseExceptionWasNotThrown(IllegalArgumentException.class);
        } catch (IllegalArgumentException e) {
            assertThat(e).hasMessage("argument is blank or empty");
        }

    }

    @Test
    public void shouldThrowWhenStringArgumentIsBlank() {
        try {
            Preconditions.checkArgumentProvided("  ", "argument");
            failBecauseExceptionWasNotThrown(IllegalArgumentException.class);
        } catch (IllegalArgumentException e) {
            assertThat(e).hasMessage("argument is blank or empty");
        }

    }

    @Test
    public void shouldThrowWhenObjectArgumentIsBlank() {
        try {
            Preconditions.<Integer>checkArgumentProvided((Integer) null, "argument");
            failBecauseExceptionWasNotThrown(IllegalArgumentException.class);
        } catch (IllegalArgumentException e) {
            assertThat(e).hasMessage("argument is null");
        }
    }
}
