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
        id = in.readInt();
        author = in.readString();
        title = in.readString();
        book_cover_URL = in.readString();
        published = in.readString();

    }

    public void setId(int id) {
        this.id = id;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setBook_cover_URL(String book_cover_URL) {
        this.book_cover_URL = book_cover_URL;
    }

    public void setPublished(String published) {
        this.published = published;
    }

    public void setTitle(String title){
        this.title = title;
    }

    public int getId() {
        return id;
    }

    public String getAuthor() {
        return author;
    }

    public String getBook_cover_URL() {
        return book_cover_URL;
    }

    public String getPublished() {
        return published;
    }

    public String getTitle() {
        return title;
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

        dest.writeString(author);
        dest.writeString(title);
        dest.writeString(book_cover_URL);
        dest.writeString(published);
        dest.writeInt(id);
    }
}
