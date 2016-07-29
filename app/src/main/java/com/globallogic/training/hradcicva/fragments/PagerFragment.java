package com.globallogic.training.hradcicva.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.globallogic.training.hradcicva.data.Database;
import com.globallogic.training.hradcicva.R;

/**
 * Created by zavadpe on 7/25/16.
 */
public class PagerFragment extends Fragment {

    public static final String TAG = "PagerFragment";
    public static final String BUTTON_ID = "button_id";

    private TabLayout tabLayout;
    private ViewPager viewPager;
    private int buttonID;


    public static PagerFragment newInstance(int buttonId) {
        PagerFragment fragment = new PagerFragment();
        Bundle arg = new Bundle();
        arg.putInt(BUTTON_ID, buttonId);
        fragment.setArguments(arg);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        buttonID = getArguments().getInt(BUTTON_ID);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_pager, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        tabLayout = (TabLayout) view.findViewById(R.id.tab_layout);
        viewPager = (ViewPager) view.findViewById(R.id.view_pager);
        viewPager.setAdapter(new PagerAdapter(getChildFragmentManager(), Database.getTabs(buttonID)));
        tabLayout.setupWithViewPager(viewPager);
    }

    public final class PagerAdapter extends FragmentPagerAdapter {

        private String[] tabnames;

        public PagerAdapter(FragmentManager fm, String[] titles) {
            super(fm);
            this.tabnames = titles;
        }

        @Override
        public Fragment getItem(int position) {
            return PagerItemFragment.getInstance(buttonID,position);
        }

        @Override
        public int getCount() {
            return tabnames.length;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return tabnames[position];
        }
    }
}
