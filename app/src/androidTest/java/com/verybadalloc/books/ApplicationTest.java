package com.verybadalloc.books;

import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.verybadalloc.books.views.BooksListActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.Espresso.pressBack;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class ApplicationTest {

    @Rule
    public ActivityTestRule<BooksListActivity> activityTestRule = new ActivityTestRule<>(BooksListActivity.class);

    @Test
    public void listBooksClickOpenDetails() {
        onView(withId(R.id.recyclerView))
                .perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));
        onView(withId(R.id.book_author)).check(matches(withText("Paula Hawkins")));
        onView(withId(R.id.book_name)).check(matches(withText("The Girl on the Train")));
        //Should have a way to test the image too...
    }

    @Test
    public void scrollThroughListBooks() {
        onView(withId(R.id.recyclerView))
                .perform(RecyclerViewActions.scrollToPosition(4))
                .perform(RecyclerViewActions.actionOnItemAtPosition(4, click()));
        onView(withId(R.id.book_author)).check(matches(withText("Andy Weir")));
        onView(withId(R.id.book_name)).check(matches(withText("The Martian: A Novel")));
    }

    @Test
    public void fromListToDetailsAndBack() {
        onView(withId(R.id.recyclerView))
                .perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));
        pressBack();
        onView(withId(R.id.recyclerView)).check(matches(isDisplayed()));
    }
}