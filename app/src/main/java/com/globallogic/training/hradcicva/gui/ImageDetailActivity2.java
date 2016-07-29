package com.globallogic.training.hradcicva.gui;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.globallogic.training.hradcicva.R;
import com.globallogic.training.hradcicva.data.Database;

public class ImageDetailActivity2 extends FragmentActivity {

    public static final String IMAGE_GROUP_ID = "extra_image";
    private static final String TAG = ImageDetailActivity2.class.getSimpleName();

    private ImagePagerAdapter mAdapter;
    private ViewPager mPager;
    private int imageGroupID;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.image_detail_pager2); // Contains just a ViewPager

        imageGroupID = getIntent().getIntExtra(IMAGE_GROUP_ID, -1);
        if (imageGroupID == -1) {
            throw new RuntimeException(TAG + " started without image group id");
        }
        Log.d(TAG, "onCreate with groupid: " + imageGroupID);

        mAdapter = new ImagePagerAdapter(getSupportFragmentManager(), Database.getImageIDsFromGroupID(imageGroupID).size(), imageGroupID);
        mPager = (ViewPager) findViewById(R.id.pager);
        mPager.setAdapter(mAdapter);
    }

    public static class ImagePagerAdapter extends FragmentStatePagerAdapter {
        private final int mSize;
        private final int group;

        public ImagePagerAdapter(FragmentManager fm, int size, int groupID) {
            super(fm);
            mSize = size;
            this.group = groupID;
        }

        @Override
        public int getCount() {
            return mSize;
        }

        @Override
        public Fragment getItem(int position) {
            Log.d("ImagePagerAdapter", "getItem " + position);
            return ImageDetailFragment2.newInstance(group, position);
        }
    }
}
