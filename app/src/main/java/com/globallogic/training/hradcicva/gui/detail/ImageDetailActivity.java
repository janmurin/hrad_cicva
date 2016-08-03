package com.globallogic.training.hradcicva.gui.detail;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.util.Log;

import com.globallogic.training.hradcicva.R;

import java.util.ArrayList;
import java.util.List;

public class ImageDetailActivity extends FragmentActivity {

    public static final String IMAGE_RES_IDS = "extra_image";
    private static final String TAG = ImageDetailActivity.class.getSimpleName();
    public static Context CONTEXT;

    private ImagePagerAdapter mAdapter;
    private ViewPager mPager;
    private ArrayList<Integer> imageResIDs;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.image_detail_pager2); // Contains just a ViewPager
        CONTEXT = this;

        imageResIDs = getIntent().getIntegerArrayListExtra(IMAGE_RES_IDS);
        if (imageResIDs == null) {
            throw new RuntimeException(TAG + " started without image res ids");
        }
        Log.d(TAG, "onCreate with groupid: " + imageResIDs);

        mAdapter = new ImagePagerAdapter(getSupportFragmentManager(), imageResIDs);
        mPager = (ViewPager) findViewById(R.id.pager);
        mPager.setAdapter(mAdapter);
    }

    public static class ImagePagerAdapter extends FragmentStatePagerAdapter {
        private final List<Integer> imageResIDs;

        public ImagePagerAdapter(FragmentManager fm, List<Integer> imageResIDs) {
            super(fm);
            this.imageResIDs = imageResIDs;
        }

        @Override
        public int getCount() {
            return imageResIDs.size();
        }

        @Override
        public Fragment getItem(int position) {
            Log.d("ImagePagerAdapter", "getItem " + position);
            return ImageDetailFragment.newInstance(imageResIDs.get(position));
        }
    }
}
