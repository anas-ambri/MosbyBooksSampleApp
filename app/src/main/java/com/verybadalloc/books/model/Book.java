package com.verybadalloc.books.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.hannesdorfmann.parcelableplease.annotation.ParcelablePlease;

/**
 * Created by aambri on 15-06-06.
 */
@ParcelablePlease
public class Book implements Parcelable {

    public int id;

    public String imgUrl;

    public String name;

    public String author;

    @Override public int describeContents() {
        return 0;
    }

    @Override public void writeToParcel(Parcel dest, int flags) {
        BookParcelablePlease.writeToParcel(this, dest, flags);
    }

    public static final Creator<Book> CREATOR = new Creator<Book>() {
        public Book createFromParcel(Parcel source) {
            Book target = new Book();
            BookParcelablePlease.readFromParcel(target, source);
            return target;
        }

        public Book[] newArray(int size) {
            return new Book[size];
        }
    };

}
