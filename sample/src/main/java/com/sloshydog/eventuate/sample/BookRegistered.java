package com.sloshydog.eventuate.sample;

import java.io.Serializable;

public class BookRegistered implements Serializable {
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
}
