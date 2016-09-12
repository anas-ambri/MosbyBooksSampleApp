package com.verybadalloc.books.network;

import com.loopj.android.http.AsyncHttpResponseHandler;

import cz.msebera.android.httpclient.Header;

/**
 * Created by aambri on 15-06-07.
 */
public class ResponseHandler extends AsyncHttpResponseHandler {

    private final NetworkCallback callback;

    public ResponseHandler(NetworkCallback callback) {
        this.callback = callback;
    }

    @Override
    public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
        try {
            callback.onSuccess(new String(responseBody, "UTF-8"));
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage(), e);
        }

    }

    @Override
    public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
        callback.onFailure(error.getMessage());
    }
}