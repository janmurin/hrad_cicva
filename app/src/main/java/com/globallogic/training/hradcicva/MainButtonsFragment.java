package com.globallogic.training.hradcicva;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Point;
import android.os.Bundle;
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

import static com.globallogic.training.hradcicva.Shared.headerPortraitBitmap;
import static com.globallogic.training.hradcicva.Shared.headerLandBitmap;

public class MainButtonsFragment extends Fragment {

    public static final String TAG = MainButtonsFragment.class.getSimpleName();
    private MainButtonsFragmentClickListener mListener;
    // private RecyclerView recyclerView;
    private String[] buttonNames = {"O hrade", "Prehliadka hradu", "Podujatia", "PFHC o.z."};
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
        setImageHeader();

//        // Set the adapter
//        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
//        // use this setting to improve performance if you know that changes
//        // in content do not change the layout size of the RecyclerView
//        recyclerView.setHasFixedSize(true);
//
//        // use a linear layout manager
//        LinearLayoutManager mLayoutManager = new LinearLayoutManager(getContext());
//        recyclerView.setLayoutManager(mLayoutManager);
//
//        // specify an adapter (see also next example)
//        recyclerView.setAdapter(new MainButtonsRecyclerViewAdapter(buttonNames, new MainButtonsRecyclerViewAdapter.OnMainButtonClickListener() {
//            @Override
//            public void OnMainButtonClick(int id) {
//                mListener.OnButtonClick(id);
//            }
//        }));

        buttonsListView = (ListView) view.findViewById(R.id.buttons_listView);
        buttonsListView.setAdapter(new CustomListViewAdapter(getActivity().getApplicationContext(), buttonNames));
        buttonsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mListener.OnButtonClick(position);
            }
        });
        setListViewHeightBasedOnChildren(buttonsListView);

        return view;
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
        BitmapFactory.decodeResource(getActivity().getResources(), R.drawable.cicvazapadna, options);
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
        return ImageUtils.decodeSampledBitmapFromResource(getActivity().getResources(), R.drawable.cicvazapadna, imgWidth, imgHeight);
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
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }


}
