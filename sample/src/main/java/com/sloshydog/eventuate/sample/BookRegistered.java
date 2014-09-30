package com.sloshydog.eventuate.sample;

import java.io.Serializable;

import static org.apache.commons.lang3.builder.ToStringBuilder.reflectionToString;

class BookRegistered implements Serializable {
    private final String bookId;
    private final String title;
    private final String author;
    private final String isbn;

    public BookRegistered(String bookId, String title, String author, String isbn) {
        this.bookId = bookId;
        this.title = title;
        this.author = author;
        this.isbn = isbn;
    }

    public String getBookId() {
        return bookId;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getIsbn() {
        return isbn;
    }

    @Override
    public String toString() {
        return reflectionToString(this);
    }
}
