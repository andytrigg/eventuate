package com.sloshydog.eventuate.sample;

public class BookRegistered {

    private final String title;
    private final String author;
    private final String isbn;

    public BookRegistered(String title, String author, String isbn) {

        this.title = title;
        this.author = author;
        this.isbn = isbn;
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
