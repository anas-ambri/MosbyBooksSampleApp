package com.verybadalloc.books.events;

import com.verybadalloc.books.model.Book;

/**
 * Created by aambri on 15-06-10.
 */
public class BookSelected {
    public final Book selectedBook;

    public BookSelected(Book book) {
        this.selectedBook = book;
    }
}
