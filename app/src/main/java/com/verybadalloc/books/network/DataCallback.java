package com.verybadalloc.books.network;

/**
 * Created by aambri on 15-06-09.
 */
public interface DataCallback<T> {
    void onSuccess(T data);
    void onFailure(String reason);
}
