package com.verybadalloc.books.presenters;

import android.test.suitebuilder.annotation.SmallTest;

import com.verybadalloc.books.model.Book;
import com.verybadalloc.books.network.DataCallback;
import com.verybadalloc.books.network.DataFetcher;
import com.verybadalloc.books.views.BooksListView;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

/**
 * Created by anasambri on 2016-09-12.
 */
@RunWith(MockitoJUnitRunner.class)
@SmallTest
public class BooksListPresenterTest {

    private static final String ERROR_DATA_FETCH = "Could not find data";
    private static final Book[] data;
    static {
        Book b1 = new Book();
        b1.id = 1;
        b1.author = "Paula Hawkins";
        b1.imgUrl = "http://ecx.images-amazon.com/images/I/51-VcOHdoFL._SL160_PIsitb-sticker-arrow-dp,TopRight,12,-18_SH30_OU15_SL150_.jpg";
        b1.name = "The Girl on the Train";
        data = new Book[] {b1};
    }
    private BooksListPresenter presenter;

    @Mock
    BooksListView view;
    @Mock
    DataFetcher dataFetcher;

    @Test
    public void failureDataFetcher_showError() {
        //Given
        presenter = new BooksListPresenter(dataFetcher);
        presenter.attachView(view);
        boolean pullToRefresh = true;

        //When
        presenter.loadBooks(pullToRefresh);

        //Then
        ArgumentCaptor<DataCallback> dataCallbackArg = ArgumentCaptor.forClass(DataCallback.class);
        verify(view).showLoading(pullToRefresh);
        verify(dataFetcher, times(1)).getBooks(dataCallbackArg.capture());

        //When
        dataCallbackArg.getValue().onFailure(ERROR_DATA_FETCH);

        //Then
        ArgumentCaptor<Throwable> throwableArg = ArgumentCaptor.forClass(Throwable.class);
        ArgumentCaptor<Boolean> booleanArg = ArgumentCaptor.forClass(Boolean.class);
        verify(view).showError(throwableArg.capture(), booleanArg.capture());
        assertEquals(ERROR_DATA_FETCH, throwableArg.getValue().getMessage());
        assertEquals(pullToRefresh, booleanArg.getValue());
    }

    @Test
    public void successDataFetcher_showContent() {
        //Given
        presenter = new BooksListPresenter(dataFetcher);
        presenter.attachView(view);
        boolean pullToRefresh = true;

        //When
        presenter.loadBooks(pullToRefresh);

        //Then
        ArgumentCaptor<DataCallback> dataCallbackArg = ArgumentCaptor.forClass(DataCallback.class);
        verify(view).showLoading(pullToRefresh);
        verify(dataFetcher, times(1)).getBooks(dataCallbackArg.capture());

        //When
        dataCallbackArg.getValue().onSuccess(data);

        //Then
        verify(view).showLoading(pullToRefresh);
        ArgumentCaptor<Book[]> booksArgument = ArgumentCaptor.forClass(Book[].class);
        verify(view).setData(booksArgument.capture());
        verify(view).showContent();
        assertArrayEquals(data, booksArgument.getValue());
    }
}