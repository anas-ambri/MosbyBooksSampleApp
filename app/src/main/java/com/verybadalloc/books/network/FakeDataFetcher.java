package com.verybadalloc.books.network;

import com.verybadalloc.books.model.Book;

/**
 * Created by anasambri on 2016-09-12.
 */

public class FakeDataFetcher implements DataFetcher {

    private String reason;
    private Book[] data;

    public FakeDataFetcher(String reason) {
        this.reason = reason;
    }

    public FakeDataFetcher(Book[] data) {
        this.data = data;
    }

    @Override
    public void getBooks(DataCallback<Book[]> callback) {
        if (reason != null) {
            callback.onFailure(reason);
        } else {
            callback.onSuccess(data);
        }
    }
}
