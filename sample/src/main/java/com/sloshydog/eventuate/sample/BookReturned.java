package com.sloshydog.eventuate.sample;

import org.joda.time.Duration;

public class BookReturned {

    private final String bookId;
    private final String by;
    public final Duration after;
    public final boolean late;

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
}
