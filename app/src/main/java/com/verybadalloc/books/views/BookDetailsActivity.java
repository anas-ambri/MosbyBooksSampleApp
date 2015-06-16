package com.verybadalloc.books.views;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import com.verybadalloc.books.R;
import com.verybadalloc.books.model.Book;


/**
 * An activity representing a single Book detail screen. This
 * activity is only used on handset devices. On tablet-size devices,
 * item details are presented side-by-side with a list of items
 * in a {@link BooksListActivity}.
 * <p/>
 * This activity is mostly just a 'shell' activity containing nothing
 * more than a {@link BookDetailsFragment}.
 */
public class BookDetailsActivity extends AppCompatActivity {

    Book book;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.book_details_activity);
        if(this.getIntent().hasExtra(BookDetailsFragment.BOOK)) {
            book = getIntent().getParcelableExtra(BookDetailsFragment.BOOK);
        }

        // Show the Up button in the action bar.
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        if (savedInstanceState == null) {
            Fragment fragment = new BookDetailsFragmentBuilder(book).build();
            getSupportFragmentManager().beginTransaction()
                            .add(R.id.book_details_container, fragment)
                            .commit();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            NavUtils.navigateUpTo(this, new Intent(this, BooksListActivity.class));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
