package com.verybadalloc.books.network;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.RequestParams;

/**
 * Created by aambri on 15-06-06.
 */
public class HttpRestClient {

    private static AsyncHttpClient client = new AsyncHttpClient();

    public static void get(String url, RequestParams params, NetworkCallback callback) {
        client.get(url, params, new ResponseHandler(callback));
    }
}
