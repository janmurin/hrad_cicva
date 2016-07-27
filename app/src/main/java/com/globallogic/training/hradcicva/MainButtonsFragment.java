package com.globallogic.training.hradcicva;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SimpleCursorAdapter;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.Arrays;

public class MainButtonsFragment extends Fragment {

    public static final String TAG = MainButtonsFragment.class.getSimpleName();
    private OnMainButtonClickListener mListener;
    private ListView listView;
    private String[] buttonNames = {"O hrade", "Prehliadka hradu", "Podujatia", "PFHC o.z."};

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public MainButtonsFragment() {
    }

    public static MainButtonsFragment newInstance() {
        MainButtonsFragment fragment = new MainButtonsFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_mainbuttons_list, container, false);

        // Set the adapter
        listView = (ListView) view.findViewById(R.id.list_view);
        listView.setAdapter(new SimpleAdapter(container));
        listView.setOnClickListener(new RecyclerView.OnItemTouchListener());

        return view;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnMainButtonClickListener) {
            mListener = (OnMainButtonClickListener) context;
        } else {
            throw new RuntimeException(context.toString() + " must implement OnMainButtonClickListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnMainButtonClickListener {
        void OnMainButtonClick(int id);
    }
}
