package com.sloshydog.eventuate.sample;

import org.joda.time.DateTime;
import org.joda.time.Duration;

import java.io.Serializable;

import static org.apache.commons.lang3.builder.ToStringBuilder.reflectionToString;

public class BookLent implements Serializable {
    private final String bookId;
    private final String borrower;
    private final DateTime date;
    private final Duration expectedDuration;

    public BookLent(String bookId, String borrower, DateTime date, Duration expectedDuration) {
        this.bookId = bookId;
        this.borrower = borrower;
        this.date = date;
        this.expectedDuration = expectedDuration;
    }

    public String getBookId() {
        return bookId;
    }

    public String getBorrower() {
        return borrower;
    }

    public DateTime getDate() {
        return date;
    }

    public Duration getExpectedDuration() {
        return expectedDuration;
    }

    @Override
    public String toString() {
        return reflectionToString(this);
    }
}
