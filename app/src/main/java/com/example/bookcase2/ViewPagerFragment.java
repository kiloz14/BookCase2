package com.example.bookcase2;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.JsonReader;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;



public class ViewPagerFragment extends Fragment {
    ViewPager viewPager;
    PagerAdapter pagerAdapter;
    BookDetailsFragment newFragment;

    Book bookObj;
    //private OnFragmentInteractionListener mListener;

    public ViewPagerFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_view_pager,container,false);

        viewPager = view.findViewById(R.id.viewPager);

        pagerAdapter = new PagerAdapter(getChildFragmentManager());

        return view;
    }

    public void addPager(JSONArray bookArray) {
        for(int i = 0 ; i < bookArray.length(); i++){
            try{
                JSONObject pagerData = bookArray.getJSONObject(i);
                bookObj = new Book(pagerData);
                newFragment = BookDetailsFragment.newInstance(bookObj);
                pagerAdapter.add(newFragment);
                pagerAdapter.notifyDataSetChanged();

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        viewPager.setAdapter(pagerAdapter);
    }


   class PagerAdapter extends FragmentStatePagerAdapter{

        ArrayList<BookDetailsFragment> pagerFragments;
        public PagerAdapter(FragmentManager fm){
            super(fm);
            pagerFragments = new ArrayList<>();

        }


        public void add(BookDetailsFragment fr){
            pagerFragments.add(fr);
        }

       @Override
       public int getItemPosition(@NonNull Object object) {
           return PagerAdapter.POSITION_NONE;
       }
       @Override
       public Fragment getItem(int i) {
           return pagerFragments.get(i);
       }

       @Override
       public int getCount() {
           return pagerFragments.size();
       }
   }
}
