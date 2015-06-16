package com.verybadalloc.books.views;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.hannesdorfmann.fragmentargs.annotation.Arg;
import com.hannesdorfmann.mosby.mvp.MvpFragment;
import com.squareup.picasso.Picasso;
import com.verybadalloc.books.R;
import com.verybadalloc.books.model.Book;
import com.verybadalloc.books.presenters.BookDetailsPresenter;

import butterknife.InjectView;
import icepick.Icicle;

/**
 * A fragment representing a single Book detail screen.
 * This fragment is either contained in a {@link BooksListActivity}
 * in two-pane mode (on tablets) or a {@link BookDetailsActivity}
 * on handsets.
 */
public class BookDetailsFragment extends MvpFragment<BookDetailsView, BookDetailsPresenter>
    implements BookDetailsView {

    @Icicle
    @Arg
    Book book;
    @InjectView(R.id.book_image)
    ImageView bookImage;
    @InjectView(R.id.book_name)
    TextView bookName;
    @InjectView(R.id.book_author)
    TextView bookAuthor;

    public static final String BOOK = "book";

    @Override
    public BookDetailsPresenter createPresenter() {
        return new BookDetailsPresenter();
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.book_details_fragment;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        bookName.setText(book.name);
        bookAuthor.setText(book.author);
        Picasso.with(getActivity()).load(book.imgUrl).into(bookImage);
        getActivity().setTitle(book.name);
    }
}
