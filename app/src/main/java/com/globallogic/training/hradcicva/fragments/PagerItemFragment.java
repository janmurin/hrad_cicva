package com.globallogic.training.hradcicva.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.ImageView;

import com.globallogic.training.hradcicva.data.Article;
import com.globallogic.training.hradcicva.data.Database;
import com.globallogic.training.hradcicva.R;

import java.util.List;

/**
 * Created by zavadpe on 7/25/16.
 */
public class PagerItemFragment extends Fragment {

    public static final String BUTTON_ID = "buttonID";
    public static final String POSITION = "position";
    public static final String TAG = PagerItemFragment.class.getSimpleName();
    private int buttonID;
    private int position;
    private ImageView articleImageView;
    private Article article;

    public static PagerItemFragment getInstance(int buttonID, int position) {
        PagerItemFragment inst = new PagerItemFragment();
        Bundle args = new Bundle();
        args.putInt(BUTTON_ID, buttonID);
        args.putInt(POSITION, position);
        inst.setArguments(args);
        return inst;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle args = getArguments();
        if (args != null) {
            buttonID = args.getInt(BUTTON_ID);
            position = args.getInt(POSITION);
            article = Database.getArticle(buttonID, position);
        } else {
            throw new RuntimeException("Pager item fragment without arguments!!!");
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
        articleImageView = (ImageView) view.findViewById(R.id.articleImageView);
        WebView webview = (WebView) view.findViewById(R.id.contentWebView);
        webview.getSettings().setJavaScriptEnabled(true);
        //webview.loadDataWithBaseURL("file:///android_asset/sponzori.html", "", "text/html", "utf-8", null);
        webview.loadUrl("file:///android_asset/" + article.assetUrl);
        System.out.println(TAG + " article ids: " + article.imageIDs);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }
}
