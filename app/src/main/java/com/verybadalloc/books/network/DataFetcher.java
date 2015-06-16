package com.verybadalloc.books.network;

import com.google.gson.Gson;
import com.verybadalloc.books.model.Book;

/**
 * Created by aambri on 15-06-06.
 */

public class DataFetcher {

    private static final String BOOKS_API = "http://verybadalloc.com/downloads/data/apps/com_verybadalloc_books.json";

    public static void getBooks(final DataCallback<Book[]> callback) {
        HttpRestClient.get(BOOKS_API, null, new NetworkCallback() {

            @Override
            public void onSuccess(String response) {
                Book[] eateries = new Gson().fromJson(response, Book[].class);
                callback.onSuccess(eateries);
            }

            @Override
            public void onFailure(String reason) {
                callback.onFailure(reason);
            }
        });
    }
}
