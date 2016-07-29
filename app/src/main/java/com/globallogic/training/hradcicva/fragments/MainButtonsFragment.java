package com.globallogic.training.hradcicva.fragments;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Point;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.Surface;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.globallogic.training.hradcicva.gui.CustomListViewAdapter;
import com.globallogic.training.hradcicva.data.Database;
import com.globallogic.training.hradcicva.deprecated.ImageUtils;
import com.globallogic.training.hradcicva.R;

import static com.globallogic.training.hradcicva.data.Shared.headerPortraitBitmap;
import static com.globallogic.training.hradcicva.data.Shared.headerLandBitmap;

public class MainButtonsFragment extends Fragment {

    public static final String TAG = MainButtonsFragment.class.getSimpleName();
    private MainButtonsFragmentClickListener mListener;
    // private RecyclerView recyclerView;
    private ImageView headerImageView;
    private ListView buttonsListView;


    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public MainButtonsFragment() {
    }

//    public static MainButtonsFragment newInstance() {
//        MainButtonsFragment fragment = new MainButtonsFragment();
//        return fragment;
//    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, " onCreate " + this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, " onDestroy " + this);
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.d(TAG, " onStart " + this);
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.d(TAG, " onStop " + this);
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d(TAG, " onResume " + this);
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d(TAG, " onPause " + this);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frame_main_layout, container, false);
        Log.d(TAG, " onCreateView " + this);

        headerImageView = (ImageView) view.findViewById(R.id.headerImageView);
        buttonsListView = (ListView) view.findViewById(R.id.buttons_listView);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setImageHeader();
        buttonsListView.setAdapter(new CustomListViewAdapter(getActivity().getApplicationContext(), Database.getButtonNames()));
        buttonsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mListener.OnButtonClick(position);
            }
        });
        setListViewHeightBasedOnChildren(buttonsListView);

        headerImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.OnHeaderClick();
            }
        });
    }

    /**** Method for Setting the Height of the ListView dynamically.
     **** Hack to fix the issue of not showing all the items of the ListView
     **** when placed inside a ScrollView  ****/
    public static void setListViewHeightBasedOnChildren(ListView listView) {
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null)
            return;

        int desiredWidth = View.MeasureSpec.makeMeasureSpec(listView.getWidth(), View.MeasureSpec.UNSPECIFIED);
        int totalHeight = 0;
        View view = null;
        for (int i = 0; i < listAdapter.getCount(); i++) {
            view = listAdapter.getView(i, view, listView);
            if (i == 0)
                view.setLayoutParams(new ViewGroup.LayoutParams(desiredWidth, AbsListView.LayoutParams.WRAP_CONTENT));

            view.measure(desiredWidth, View.MeasureSpec.UNSPECIFIED);
            totalHeight += view.getMeasuredHeight();
        }
        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
        listView.setLayoutParams(params);
    }

    /**
     * nastavi header image, ak uz bol vytvoreny tak ho len pouzije, inak ho vytvori
     * iba 2 verzie headera existuju: portrait a landscape
     */
    private void setImageHeader() {
        Display display = getActivity().getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        int rotation = display.getRotation();
        if (rotation == Surface.ROTATION_90 || rotation == Surface.ROTATION_270) {
            // land mode
            if (headerLandBitmap != null) {
                headerImageView.setImageBitmap(headerLandBitmap);
            } else {
                headerLandBitmap = getHeaderBitmap(size);
                Log.d(TAG, "headerLandBitmap size: " + headerLandBitmap.getByteCount());
                headerImageView.setImageBitmap(headerLandBitmap);
            }
        } else {
            // portrait mode
            if (headerPortraitBitmap != null) {
                headerImageView.setImageBitmap(headerPortraitBitmap);
            } else {
                headerPortraitBitmap = getHeaderBitmap(size);
                Log.d(TAG, "headerPortraitBitmap size: " + headerPortraitBitmap.getByteCount());
                headerImageView.setImageBitmap(headerPortraitBitmap);
            }
        }
    }

    /**
     * loads header image from drawable into calculated dimensions
     *
     * @param size
     * @return
     */
    private Bitmap getHeaderBitmap(Point size) {
        int screenWidth = size.x;
        int screenHeight = size.y;
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(getActivity().getResources(), R.drawable.header_1, options);
        int realImageHeight = options.outHeight;
        int realImageWidth = options.outWidth;
        String imageType = options.outMimeType;
        int imgWidth = screenWidth;
        int imgHeight = (int) (screenWidth * ((double) realImageHeight / realImageWidth));
        Log.d(TAG, "realImageHeight: " + realImageHeight + " " +
                "realImageWidth: " + realImageWidth + " " +
                "imageType: " + imageType + " " +
                "screenWidth: " + screenWidth + " " +
                "screenHeight: " + screenHeight + " " +
                "imgWidth: " + imgWidth + " " +
                "imgHeight: " + imgHeight + " ");
        return ImageUtils.decodeSampledBitmapFromResource(getActivity().getResources(), R.drawable.header_1, imgWidth, imgHeight);
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof MainButtonsFragmentClickListener) {
            mListener = (MainButtonsFragmentClickListener) context;
        } else {
            throw new RuntimeException(context.toString() + " must implement OnMainButtonClickListener");
        }
    }

    public interface MainButtonsFragmentClickListener {
        void OnButtonClick(int id);

        void OnHeaderClick();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }


}
