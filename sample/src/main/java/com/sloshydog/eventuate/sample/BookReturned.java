package com.sloshydog.eventuate.sample;

import org.joda.time.Duration;

import java.io.Serializable;

import static org.apache.commons.lang3.builder.ToStringBuilder.reflectionToString;

class BookReturned implements Serializable {

    private final String bookId;
    private final String by;
    private final Duration after;
    private final boolean late;

    public BookReturned(String bookId, String by, Duration after, boolean late) {
        this.bookId = bookId;
        this.by = by;
        this.after = after;
        this.late = late;
    }

    public String getBookId() {
        return bookId;
    }

    public String getBy() {
        return by;
    }

    public Duration getAfter() {
        return after;
    }

    public boolean isLate() {
        return late;
    }

    @Override
    public String toString() {
        return reflectionToString(this);
    }
}
