package com.verybadalloc.books.presenters;

import android.util.Log;

import com.hannesdorfmann.mosby.mvp.MvpBasePresenter;
import com.verybadalloc.books.model.Book;
import com.verybadalloc.books.network.DataCallback;
import com.verybadalloc.books.network.DataFetcher;
import com.verybadalloc.books.views.BooksListView;

/**
 * Created by aambri on 15-06-09.
 */
public class BooksListPresenter extends MvpBasePresenter<BooksListView> {


    private static final String TAG = "BooksListPresenter";
    private DataFetcher dataFetcher;

    public BooksListPresenter(DataFetcher dataFetcher) {
        this.dataFetcher = dataFetcher;
    }

    public void loadBooks(final boolean pullToRefresh) {

        if(isViewAttached()) {
            getView().showLoading(pullToRefresh);
        }

        dataFetcher.getBooks(new DataCallback<Book[]>() {

            @Override
            public void onSuccess(Book[] books) {
                if (isViewAttached()) {
                    getView().setData(books);
                    getView().showContent();
                }
            }

            @Override
            public void onFailure(String reason) {
                String message = "Failed to load books because " + reason;
                Log.e(TAG, message);
                if (isViewAttached()) {
                    getView().showError(new Throwable(message), pullToRefresh);
                }
            }
        });
    }
}
