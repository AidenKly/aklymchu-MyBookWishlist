package com.example.aklymchu_mybookwishlist;

import java.time.Year;
import java.util.Date;

public class Book {
    //Purpose: Represent a book and has getters and setters for the book title, author,
    // genre, publication year and read status.
    //Design rationale: This class is used as a simple representation for a book. It will be
    // used in an array of books and each book can be modified.
    private String title;
    private String author;
    private String genre;
    private Integer year;
    private Boolean isRead;

    public Book(String title, String author, String genre, Integer year, Boolean isRead) {
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.year = year;
        this.isRead = isRead;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Boolean getRead() {
        return isRead;
    }

    public void setRead(Boolean read) {
        isRead = read;
    }
}
