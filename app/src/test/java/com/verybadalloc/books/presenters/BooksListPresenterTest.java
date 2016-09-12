package com.verybadalloc.books.presenters;

import android.test.suitebuilder.annotation.SmallTest;

import com.verybadalloc.books.model.Book;
import com.verybadalloc.books.network.DataFetcher;
import com.verybadalloc.books.network.FakeDataFetcher;
import com.verybadalloc.books.views.BooksListView;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
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
    private DataFetcher dataFetcher;
    private BooksListPresenter presenter;

    @Mock
    BooksListView view;

    @Test
    public void failureDataFetcher_showError() {
        //Given
        dataFetcher = new FakeDataFetcher(ERROR_DATA_FETCH);
        presenter = new BooksListPresenter(dataFetcher);
        presenter.attachView(view);

        //When
        boolean pullToRefresh = true;
        presenter.loadBooks(pullToRefresh);

        //Then
        verify(view).showLoading(pullToRefresh);
        ArgumentCaptor<Throwable> throwableArgument = ArgumentCaptor.forClass(Throwable.class);
        ArgumentCaptor<Boolean> booleanArgument = ArgumentCaptor.forClass(Boolean.class);
        verify(view).showError(throwableArgument.capture(), booleanArgument.capture());
        assertEquals(ERROR_DATA_FETCH, throwableArgument.getValue().getMessage());
        assertEquals(pullToRefresh, booleanArgument.getValue());
    }

    @Test
    public void successDataFetcher_showContent() {
        //Given
        dataFetcher = new FakeDataFetcher(data);
        presenter = new BooksListPresenter(dataFetcher);
        presenter.attachView(view);

        //When
        boolean pullToRefresh = true;
        presenter.loadBooks(pullToRefresh);

        //Then
        verify(view).showLoading(pullToRefresh);
        ArgumentCaptor<Book[]> booksArgument = ArgumentCaptor.forClass(Book[].class);
        verify(view).setData(booksArgument.capture());
        verify(view).showContent();
        assertEquals(data, booksArgument.getValue());
    }
}