package com.verybadalloc.books.network;

import com.verybadalloc.books.model.Book;

/**
 * Created by anasambri on 2016-09-12.
 */
public interface DataFetcher {

    void getBooks(final DataCallback<Book[]> callback);
}
