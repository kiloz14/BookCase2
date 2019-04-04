package com.example.bookcase2;

import android.os.Parcel;
import android.os.Parcelable;

import org.json.JSONException;
import org.json.JSONObject;

public class Book implements Parcelable {

    private String author, title, book_cover_URL ,  published;
    private int id;

    public  Book(JSONObject jsonBook) throws JSONException {
        this.title = (jsonBook.getString("title"));
        this.author= (jsonBook.getString("author"));
        this.book_cover_URL =(jsonBook.getString("book_cover_URL"));
        this.published = (jsonBook.getString("published"));
        this.id = (jsonBook.getInt("book_id"));
    }

    protected Book(Parcel in) {
    }

    public static final Creator<Book> CREATOR = new Creator<Book>() {
        @Override
        public Book createFromParcel(Parcel in) {
            return new Book(in);
        }

        @Override
        public Book[] newArray(int size) {
            return new Book[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
    }
}
