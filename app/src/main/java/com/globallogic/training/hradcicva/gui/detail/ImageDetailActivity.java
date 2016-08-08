package com.globallogic.training.hradcicva.gui.detail;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.globallogic.training.hradcicva.R;
import com.globallogic.training.hradcicva.data.Image;

import java.util.ArrayList;

public class ImageDetailActivity extends FragmentActivity {

    public static final String IMAGES = "extra_image";
   // private static final String TAG = ImageDetailActivity.class.getSimpleName();
   // public static Context CONTEXT;

    private ImagePagerAdapter mAdapter;
    private ViewPager mPager;
    private ArrayList<Image> images;
    private TextView imageTextView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.image_detail_pager2); // Contains just a ViewPager
       // CONTEXT = this;
        imageTextView = (TextView) findViewById(R.id.imageTextView);

        images = (ArrayList<Image>) getIntent().getSerializableExtra(IMAGES);
//        if (images == null) {
//            throw new RuntimeException(TAG + " started without image res ids");
//        }

        mAdapter = new ImagePagerAdapter(getSupportFragmentManager(), images);
        mPager = (ViewPager) findViewById(R.id.pager);
        mPager.setAdapter(mAdapter);
        mPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                imageTextView.setText(images.get(position).title);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        imageTextView.setText(images.get(0).title);
    }

    public static class ImagePagerAdapter extends FragmentStatePagerAdapter {
        private final ArrayList<Image> images;

        public ImagePagerAdapter(FragmentManager fm, ArrayList<Image> images) {
            super(fm);
            this.images = images;
        }

        @Override
        public int getCount() {
            return images.size();
        }

        @Override
        public Fragment getItem(int position) {
            return ImageDetailFragment.newInstance(images.get(position));
        }
    }
}
