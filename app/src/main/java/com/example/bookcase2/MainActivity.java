package com.example.bookcase2;

import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity implements BookListFragment.BookInterface{

    Boolean singlePane;
    BookDetailsFragment bookDetailsFragment;
    BookListFragment bookListFragment;
    ViewPagerFragment  viewPagerFragment;

    EditText searchText;
    Button button;
    JSONArray bookArray;
    String searchBook;
    String getSearchBook[];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        singlePane = findViewById(R.id.container2) == null;
        searchText = findViewById(R.id.search_box);
        button = findViewById(R.id.search_button);


        bookDetailsFragment = new BookDetailsFragment();
        bookListFragment = new BookListFragment();

        viewPagerFragment = new ViewPagerFragment();

        if(!singlePane){
            addfragment(bookListFragment, R.id.container1);
            addfragment(bookDetailsFragment, R.id.container2);
        }
        else{
            addfragment(bookListFragment, R.id.container3);
            addfragment(viewPagerFragment, R.id.container3);
        }

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchBook = searchText.getText().toString();
                getSearchBook = searchBook.split(",");

                for(int x = 0; x < getSearchBook.length; x ++){
                    Log.d("String", getSearchBook[x]);
                    downloadbook(getSearchBook[x]);
                }
            }
        });
    }

    public void  downloadbook(final String search){
        new Thread(){
            public void run(){
                try{
                    String urlString = "https://kamorris.com/lab/audlib/booksearch.php?search=" + search;
                    URL url = new URL (urlString);
                    BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));
                    StringBuilder builder = new StringBuilder();
                    String tmpString;

                    while((tmpString = reader.readLine()) != null){
                        builder.append(tmpString);
                    }
                    Message msg = Message.obtain();
                    msg.obj = builder.toString();
                    urlHandler.sendMessage(msg);
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }.start();

    }


    Handler urlHandler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            try{
                bookArray = new JSONArray((String)msg.obj);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            bookListFragment.getBooks(bookArray);
            if(singlePane){
                viewPagerFragment.addPager(bookArray);
            }
            return false;
        }
    });
    private void addfragment(Fragment fragment, int container1) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(container1, fragment)
                .addToBackStack(null)
                .commit();
    }


    @Override
    public void bookSelected(Book bookObj) {

        bookDetailsFragment.displayedBookSelected(bookObj);

    }
}
