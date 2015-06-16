package com.verybadalloc.books.views;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.hannesdorfmann.mosby.mvp.lce.MvpLceFragment;
import com.verybadalloc.books.R;
import com.verybadalloc.books.adapters.BooksAdapter;
import com.verybadalloc.books.adapters.viewHolders.BookItemViewHolder;
import com.verybadalloc.books.model.Book;
import com.verybadalloc.books.presenters.BooksListPresenter;

import butterknife.InjectView;

/**
 * Created by aambri on 15-06-09.
 */
public class BooksListFragment extends MvpLceFragment<SwipeRefreshLayout, Book[], BooksListView, BooksListPresenter>
        implements BooksListView, SwipeRefreshLayout.OnRefreshListener {

    private static final String TAG = "BooksListFragment";
    @InjectView(R.id.recyclerView)
    RecyclerView recyclerView;
    @InjectView(R.id.contentView)
    SwipeRefreshLayout refreshLayout;
    BooksAdapter adapter;

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        contentView.setOnRefreshListener(this);
        adapter = new BooksAdapter(getActivity(), BookItemViewHolder.class);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        loadData(false);
    }

    @Override
    protected String getErrorMessage(Throwable throwable, boolean b) {
        return throwable.getMessage();
    }

    @Override
    public BooksListPresenter createPresenter() {
        return new BooksListPresenter();
    }

    @Override
    public void setData(Book[] data) {
        adapter.setBooks(data); //notifyDatasetChanged called implicitly
    }

    @Override protected int getLayoutRes() {
        return R.layout.books_list_fragment;
    }

    @Override
    public void loadData(boolean pullToRefresh) {
        presenter.loadEateries(pullToRefresh);
    }

    @Override
    public void onRefresh() {
        loadData(true);
    }

    @Override
    public void showContent() {
        super.showContent();
        refreshLayout.setRefreshing(false);
    }
}
