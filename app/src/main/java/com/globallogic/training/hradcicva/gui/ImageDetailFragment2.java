package com.globallogic.training.hradcicva.gui;

import android.graphics.Bitmap;
import android.graphics.Point;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.globallogic.training.hradcicva.R;
import com.globallogic.training.hradcicva.data.Database;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;

import java.util.List;

/**
 * Created by jan.murin on 29-Jul-16.
 */
public class ImageDetailFragment2 extends Fragment {

    private static final String IMAGE_Position_ID = "pos";
    public static final String IMAGE_GROUP_ID = "imageGroupID";
    public static final String TAG = ImageDetailFragment2.class.getSimpleName();
    private int imagePositionID;
    private ImageView mImageView;
    private int imageGroupID;

    static ImageDetailFragment2 newInstance(int groupID, int imageNum) {
        final ImageDetailFragment2 f = new ImageDetailFragment2();
        final Bundle args = new Bundle();
        args.putInt(IMAGE_Position_ID, imageNum);
        args.putInt(IMAGE_GROUP_ID, groupID);
        f.setArguments(args);
        return f;
    }

    // Empty constructor, required as per Fragment docs
    public ImageDetailFragment2() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        imagePositionID = getArguments() != null ? getArguments().getInt(IMAGE_Position_ID) : -1;
        imageGroupID = getArguments() != null ? getArguments().getInt(IMAGE_GROUP_ID) : -1;
        Log.d(TAG, " onCreate, paramaters: " + imageGroupID + " " + imagePositionID);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // image_detail_fragment.xml contains just an ImageView
        final View v = inflater.inflate(R.layout.image_detail_fragment2, container, false);
        mImageView = (ImageView) v.findViewById(R.id.imageView);
        return v;
    }

    private static final int MAX_WIDTH = 1920;
    private static final int MAX_HEIGHT = 1080;

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        // mImageView.setImageResource(Database.getImageResIDFromGroupAndPosition(imageGroupID, imagePositionID)); // Load image into ImageView
        Display display = getActivity().getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
//        Picasso.with(getContext())
//                .load(Database.getImageResIDFromGroupAndPosition(imageGroupID, imagePositionID))
//                .fit()
//                .centerCrop()
//                .into(mImageView);
        Log.d(TAG, " onActivityCreated, image set from " + Database.getImageResIDFromGroupAndPosition(imageGroupID, imagePositionID)
                + "display size: " + size.x + "x" + size.y);

        int resizeSize = (int) Math.sqrt(size.x * size.y);
// Loads given image
        Picasso.with(getContext())
                .load(Database.getImageResIDFromGroupAndPosition(imageGroupID, imagePositionID))
                .transform(new BitmapTransform(size.x, size.y))
                .skipMemoryCache()
                .resize(resizeSize, resizeSize)
                .centerInside()
                .into(mImageView);
    }

    private class BitmapTransform implements Transformation {
        int maxWidth;
        int maxHeight;

        public BitmapTransform(int maxWidth, int maxHeight) {
            this.maxWidth = maxWidth;
            this.maxHeight = maxHeight;
        }

        @Override
        public Bitmap transform(Bitmap source) {
            int targetWidth, targetHeight;
            double aspectRatio;

            if (source.getWidth() > source.getHeight()) {
                targetWidth = maxWidth;
                aspectRatio = (double) source.getHeight() / (double) source.getWidth();
                targetHeight = (int) (targetWidth * aspectRatio);
            } else {
                targetHeight = maxHeight;
                aspectRatio = (double) source.getWidth() / (double) source.getHeight();
                targetWidth = (int) (targetHeight * aspectRatio);
            }

            Bitmap result = Bitmap.createScaledBitmap(source, targetWidth, targetHeight, false);
            if (result != source) {
                source.recycle();
            }
            return result;
        }

        @Override
        public String key() {
            return maxWidth + "x" + maxHeight;
        }

    }

    ;
}