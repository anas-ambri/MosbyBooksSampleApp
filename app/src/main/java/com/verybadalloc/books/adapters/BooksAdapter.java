package com.verybadalloc.books.adapters;

import android.content.Context;

import com.verybadalloc.books.model.Book;

import java.util.Arrays;

import uk.co.ribot.easyadapter.EasyRecyclerAdapter;
import uk.co.ribot.easyadapter.ItemViewHolder;

/**
 * Created by aambri on 15-06-07.
 */
public class BooksAdapter extends EasyRecyclerAdapter<Book> {

    private static final String TAG = "BooksAdapter";

    public BooksAdapter(Context context, Class<? extends ItemViewHolder> itemViewHolderClass) {
        super(context, itemViewHolderClass);
    }

    public void setBooks(Book[] books) {
        removeItems(getItems());
        addItems(Arrays.asList(books));
    }
}
