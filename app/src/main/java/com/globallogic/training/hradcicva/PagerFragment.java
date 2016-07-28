package com.globallogic.training.hradcicva;

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

/**
 * Created by zavadpe on 7/25/16.
 */
public class PagerFragment extends Fragment {

    public static final String TAG = "PagerFragment";

    private TabLayout tabLayout;
    private ViewPager viewPager;

    String[] tabs = new String[] { "First",
            "Second",
            "Third",
            "Fourth"
    };

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
        viewPager.setAdapter(new PagerAdapter(getChildFragmentManager(), tabs));
        tabLayout.setupWithViewPager(viewPager);
    }

    public final class PagerAdapter extends FragmentPagerAdapter {

        private String[] titles;

        public PagerAdapter(FragmentManager fm, String[] titles) {
            super(fm);
            this.titles = titles;
        }

        @Override
        public Fragment getItem(int position) {
            return PagerItemFragment.getInstance(titles[position]);
        }

        @Override
        public int getCount() {
            return titles.length;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return titles[position];
        }
    }
}
