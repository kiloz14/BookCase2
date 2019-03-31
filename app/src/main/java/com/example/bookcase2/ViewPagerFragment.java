package com.example.bookcase2;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ViewPagerFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ViewPagerFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ViewPagerFragment extends Fragment {
    ViewPager viewPager;
    PagerAdapter pagerAdapter;
    BookDetailsFragment newFragment;
    TextView textView;
    //private OnFragmentInteractionListener mListener;

    public ViewPagerFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ViewPagerFragment.

    // TODO: Rename and change types and number of parameters
    public static ViewPagerFragment newInstance(String param1, String param2) {
        ViewPagerFragment fragment = new ViewPagerFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }
*/
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_view_pager,container,false);
        final  String [] bookList = this.getResources().getStringArray(R.array.bookList);

        viewPager = view.findViewById(R.id.viewPager);

        newFragment = new BookDetailsFragment();
        pagerAdapter = new PagerAdapter(getChildFragmentManager());

        for (int i = 0 ; i < bookList.length; i++){
            newFragment = BookDetailsFragment.newInstance(bookList[i]);
            pagerAdapter.add(newFragment);
        }

        viewPager.setAdapter(pagerAdapter);
        return view;
    }

    class  PagerAdapter extends FragmentStatePagerAdapter{
        ArrayList<BookDetailsFragment> pagerFragments;


        public void add(BookDetailsFragment fragment){
            pagerFragments.add(fragment);
        }

        public PagerAdapter(FragmentManager fm) {
            super(fm);
            pagerFragments = new ArrayList<>();
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
