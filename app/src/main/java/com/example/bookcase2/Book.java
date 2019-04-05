package com.example.bookcase2;

import android.os.Parcel;
import android.os.Parcelable;

import org.json.JSONException;
import org.json.JSONObject;

public class Book implements Parcelable {

    private String author, title, cover_url ,  published;
    private int id;

    public  Book(JSONObject jsonBook) throws JSONException {
        this.id = (jsonBook.getInt("book_id"));
        this.title = (jsonBook.getString("title"));
        this.author= (jsonBook.getString("author"));
        this.published = (jsonBook.getString("published"));
        this.cover_url= (jsonBook.getString("cover_url"));
    }

    protected Book(Parcel in) {
        id = in.readInt();
        title = in.readString();
        author = in.readString();
        cover_url = in.readString();
        published = in.readString();

    }

    public static  final Creator<Book> CREATOR = new Creator<Book>() {
        @Override
        public Book createFromParcel(Parcel source) {
            return new Book(source);
        }

        @Override
        public Book[] newArray(int size) {
            return new Book[size];
        }
    };


    public void setId(int id)
    {
        this.id = id;
    }

    public void setAuthor(String author)
    {
        this.author = author;
    }

    public void setBook_cover_URL(String cover_url) {
        this.cover_url = cover_url;
    }

    public void setPublished(String published)
    {
        this.published = published;
    }

    public void setTitle(String title)
    {
        this.title = title;
    }

    public int getId()
    {
        return id;
    }

    public String getAuthor()
    {
        return author;
    }

    public String getBook_cover_URL()
    {
        return cover_url;
    }

    public String getPublished()
    {
        return published;
    }

    public String getTitle()
    {
        return title;
    }



    @Override
    public int describeContents()
    {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

        dest.writeInt(id);
        dest.writeString(title);
        dest.writeString(author);
        dest.writeString(published);
        dest.writeString(cover_url);
    }
}
