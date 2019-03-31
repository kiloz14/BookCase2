package com.example.bookcase2;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity implements BookListFragment.BookInterface{

    Boolean singlePane;
    BookDetailsFragment bookDetailsFragment;
    BookListFragment bookListFragment;
    ViewPagerFragment  viewPagerFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        singlePane = findViewById(R.id.container2) == null;

        bookDetailsFragment = new BookDetailsFragment();
        bookListFragment = new BookListFragment();
        viewPagerFragment = new ViewPagerFragment();

        if(!singlePane){
            addfragment(bookListFragment, R.id.container1);
            addfragment(bookDetailsFragment, R.id.container2);
        }
        else{
            addfragment(viewPagerFragment, R.id.container3);
        }

    }

    private void addfragment(Fragment fragment, int container1) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(container1, fragment)
                .addToBackStack(null)
                .commit();
    }


    @Override
    public void bookSelected(String bookTitle) {

        bookDetailsFragment.displayedBookSelected(bookTitle);

    }
}
