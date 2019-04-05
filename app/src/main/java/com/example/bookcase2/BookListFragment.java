package com.example.bookcase2;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;



public class BookListFragment extends Fragment {


    ListView listView;
    Context c;
    ArrayList<Book> bookList;
    BookAdapter adapter;
    Book books;

    private BookInterface mListener;

    public BookListFragment() {
        // Required empty public constructor
    }


    public static BookListFragment newInstance(String param1, String param2) {
        BookListFragment fragment = new BookListFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_book_list, container, false);
        listView = v.findViewById(R.id.bookList);
        bookList = new ArrayList<>();
        return v;
    }

    public void getBooks (final JSONArray bookArray){

        for(int i = 0 ; i < bookArray.length(); i++){
            try{
                JSONObject jsonObject = bookArray.getJSONObject(i);
                bookList.add(new Book(bookArray.getJSONObject(i)));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        Log.d("Book List", bookList.toString());
        updateList();
    }

    private void updateList() {
        // Updates my list every time there is a change with the list
        final  ArrayList<Book> newList = new ArrayList<>();

        newList.addAll(bookList);
        Log.d("Books", newList.toString());
        adapter = new BookAdapter(c , newList);

        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                books = newList.get(position);
                ((BookInterface) c ).bookSelected(books);
            }
        });

        bookList.clear();
        adapter.notifyDataSetChanged();
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof BookInterface) {
            mListener = (BookInterface) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
        this.c = context;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }


    public interface BookInterface {
        // TODO: Update argument type and name
        void bookSelected(Book bookObj);
    }
}
