package com.sloshydog.eventuate.sample;

import org.joda.time.DateTime;
import org.joda.time.Duration;

public class BookLent {
    private final String bookeId;
    private final String borrower;
    private final DateTime date;
    private final Duration expectedDuration;

    public BookLent(String bookeId, String borrower, DateTime date, Duration expectedDuration) {
        this.bookeId = bookeId;
        this.borrower = borrower;
        this.date = date;
        this.expectedDuration = expectedDuration;
    }

    public String getBookeId() {
        return bookeId;
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
}
