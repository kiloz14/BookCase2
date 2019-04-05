package com.example.bookcase2;

import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;


public class BookDetailsFragment extends Fragment {


    String bookSelected;
    TextView textView;
    ImageView imageView;

    String author, title, publisher;
    Book pagerBooks;
    public static final String BOOK_KEY = "book";


    public BookDetailsFragment() {
        // Required empty public constructor
    }

    public static BookDetailsFragment newInstance(Book bookList) {
        BookDetailsFragment fragment = new BookDetailsFragment();
        Bundle args = new Bundle();
        args.putParcelable(BOOK_KEY,bookList);
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            pagerBooks = getArguments().getParcelable(BOOK_KEY);
        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =inflater.inflate(R.layout.fragment_book_details, container, false);
        textView = view.findViewById(R.id.bookTitle);
       imageView = view.findViewById(R.id.viewPager);
       if(getArguments()!= null) {
           displayedBookSelected(pagerBooks);
       }
       return  view;
    }

    public void displayedBookSelected(Book bookObj) {
    author = bookObj.getAuthor();
    title = bookObj.getTitle();
    publisher = bookObj.getPublished();
    textView.setText(title);
    textView.append("," + author);
    textView.append("\n"+publisher);
    textView.setTextColor(Color.BLACK);
    textView.setTextSize(18);
    String imageUrl = bookObj.getBook_cover_URL();
    Picasso.get().load(imageUrl).into(imageView);
    }


}
