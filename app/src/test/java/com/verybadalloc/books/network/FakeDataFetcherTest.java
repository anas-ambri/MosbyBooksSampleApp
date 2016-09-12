package com.verybadalloc.books.network;

import com.verybadalloc.books.model.Book;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.*;

/**
 * Created by anasambri on 2016-09-12.
 */

public class FakeDataFetcherTest {

    private static final Book[] data;
    private static final String reason = "Could not fetch books";

    static {
        Book b1 = new Book();
        b1.id = 1;
        b1.author = "Paula Hawkins";
        b1.imgUrl = "http://ecx.images-amazon.com/images/I/51-VcOHdoFL._SL160_PIsitb-sticker-arrow-dp,TopRight,12,-18_SH30_OU15_SL150_.jpg";
        b1.name = "The Girl on the Train";
        data = new Book[] {b1};
    }

    @Test
    public void dataFetcher_SuccessReturnsData() {
        dataFetcherSuccess().getBooks(new DataCallback<Book[]>() {
            @Override
            public void onSuccess(Book[] data) {
                assertArrayEquals(data, FakeDataFetcherTest.data);
            }

            @Override
            public void onFailure(String reason) {
                assertTrue("Callback returned unexpected data", false);
            }
        });
    }

    @Test
    public void dataFetcher_FailureReturnsErrorMessage() {
        dataFetcherFailure().getBooks(new DataCallback<Book[]>() {
            @Override
            public void onSuccess(Book[] data) {
                assertTrue("Callback returned unexpected data", false);
            }

            @Override
            public void onFailure(String reason) {
                assertEquals(reason, FakeDataFetcherTest.reason);
            }
        });
    }

    private FakeDataFetcher dataFetcherSuccess() {
        return new FakeDataFetcher(data);
    }

    private FakeDataFetcher dataFetcherFailure() {
        return new FakeDataFetcher(reason);
    }

}