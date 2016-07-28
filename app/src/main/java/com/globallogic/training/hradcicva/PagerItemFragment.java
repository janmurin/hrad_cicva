package com.globallogic.training.hradcicva;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by zavadpe on 7/25/16.
 */
public class PagerItemFragment extends Fragment {

    public static final String ARG_TITLE = "arg_title";

    public static PagerItemFragment getInstance(String title) {
        PagerItemFragment inst = new PagerItemFragment();
        Bundle args = new Bundle();
        args.putString(ARG_TITLE, title);
        inst.setArguments(args);
        return inst;
    }

    private String title;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle args = getArguments();
        if (args != null) {
            title = args.getString(ARG_TITLE);
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_pager_item, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (!TextUtils.isEmpty(title)) {
            TextView textView = (TextView) view.findViewById(R.id.title);
            textView.setText(title);
        }
    }
}
