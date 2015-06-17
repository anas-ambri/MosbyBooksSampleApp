package com.verybadalloc.books.views;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;

import com.squareup.otto.Bus;
import com.squareup.otto.Subscribe;
import com.verybadalloc.books.R;
import com.verybadalloc.books.events.BookSelected;
import com.verybadalloc.books.events.BusProvider;


/**
 * An activity representing a list of Eateries. This activity
 * has different presentations for handset and tablet-size devices. On
 * handsets, the activity presents a list of items, which when touched,
 * lead to a {@link BookDetailsActivity} representing
 * item details. On tablets, the activity presents the list of items and
 * item details side-by-side using two vertical panes.
 * <p/>
 */
public class BooksListActivity extends FragmentActivity {

    private final Bus bus = BusProvider.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.books_list_activity);
    }

    @Override
    protected void onResume() {
        super.onResume();
        bus.register(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        bus.unregister(this);
    }

    public boolean isTwoPane() {
        return findViewById(R.id.book_details_container) != null;
    }

    @Subscribe
    public void onBookSelected(BookSelected event) {
        if(isTwoPane()) {
            Fragment fragment = new BookDetailsFragmentBuilder(event.selectedBook).build();
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.book_details_container, fragment)
                    .commit();
        } else {
            Intent detailIntent = new Intent(this, BookDetailsActivity.class);
            detailIntent.putExtra(BookDetailsFragment.BOOK, event.selectedBook);
            startActivity(detailIntent);
        }
    }
}
