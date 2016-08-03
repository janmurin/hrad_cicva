package com.globallogic.training.hradcicva.gui.detail;

import android.graphics.Point;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.globallogic.training.hradcicva.R;

/**
 * Created by jan.murin on 29-Jul-16.
 */
public class ImageDetailFragment extends Fragment {

    private static final String IMAGE_RES_ID = "pos";
    public static final String TAG = ImageDetailFragment.class.getSimpleName();
    private ImageView mImageView;
    private int imageResID;

    static ImageDetailFragment newInstance(int imageResID) {
        final ImageDetailFragment f = new ImageDetailFragment();
        final Bundle args = new Bundle();
        args.putInt(IMAGE_RES_ID, imageResID);
        f.setArguments(args);
        return f;
    }

    // Empty constructor, required as per Fragment docs
    public ImageDetailFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        imageResID = getArguments() != null ? getArguments().getInt(IMAGE_RES_ID) : -1;
        Log.d(TAG, " onCreate, paramaters: " + imageResID);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // image_detail_fragment.xml contains just an ImageView
        final View v = inflater.inflate(R.layout.image_detail_fragment2, container, false);
        mImageView = (ImageView) v.findViewById(R.id.imageView);
        return v;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        Log.d(TAG, " onActivityCreated, image set from " + imageResID );
        Glide.with(ImageDetailActivity.CONTEXT)
                .load(imageResID)
                .into(mImageView);
//        int resizeSize = (int) Math.sqrt(size.x * size.y);
//        Picasso.with(getContext())
//                .load(imageResID)
//                .transform(new BitmapTransform(size.x, size.y))
//                .skipMemoryCache()
//                .resize(resizeSize, resizeSize)
//                .centerInside()
//                .into(mImageView);
    }



    ;
}