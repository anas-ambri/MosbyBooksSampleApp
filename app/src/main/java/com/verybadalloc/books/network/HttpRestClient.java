package com.verybadalloc.books.network;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by aambri on 15-06-06.
 */
public class HttpRestClient {

    private static OkHttpClient client = new OkHttpClient();

    public static void get(String url, final NetworkCallback callback) {
        final Request request = new Request.Builder()
                .url(url)
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                callback.onFailure(e.getMessage());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (!response.isSuccessful()) {
                    callback.onFailure(response.body().string());
                } else {
                    callback.onSuccess(response.body().string());
                }
            }
        });
    }
}
