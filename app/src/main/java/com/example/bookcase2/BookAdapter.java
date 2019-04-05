package com.example.bookcase2;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class BookAdapter extends BaseAdapter {
    Context context;
    ArrayList<Book> bookArrayList;

    public BookAdapter(Context context, ArrayList <Book> bookArrayList){
        this.context = context;
        this.bookArrayList = bookArrayList;
    }
    @Override
    public int getCount() {
        return bookArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return bookArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        TextView textView = new TextView(context);
        textView.setText(bookArrayList.get(position).getTitle());
        textView.setTextSize(20);

        return textView;
    }
}
