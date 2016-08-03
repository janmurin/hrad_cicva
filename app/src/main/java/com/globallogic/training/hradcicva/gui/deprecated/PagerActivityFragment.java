/*
 * Copyright (C) 2015 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.globallogic.training.hradcicva.gui.deprecated;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.globallogic.training.hradcicva.R;
import com.globallogic.training.hradcicva.data.Article;
import com.globallogic.training.hradcicva.data.Database;


public class PagerActivityFragment extends Fragment {


    public static final String TOPIC_ID = "topicID";
    public static final String POS_ID = "posID";
    private int topicID;
    private int posID;
    private Article article;

    public static PagerActivityFragment getInstance(int menuID, int pos) {
        PagerActivityFragment inst = new PagerActivityFragment();
        Bundle args = new Bundle();
        args.putInt(TOPIC_ID, menuID);
        args.putInt(POS_ID, pos);
        inst.setArguments(args);
        return inst;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle args = getArguments();
        if (args != null) {
            topicID = args.getInt(TOPIC_ID);
            posID = args.getInt(POS_ID);
            article = Database.getArticle(topicID, posID);
        } else {
            throw new RuntimeException("Pager item fragment without arguments!!!");
        }


    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_pager_fragment, container, false);
        //final Toolbar toolbar = (Toolbar) view.findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        CollapsingToolbarLayout collapsingToolbar = (CollapsingToolbarLayout) view.findViewById(R.id.collapsing_toolbar);
        collapsingToolbar.setTitle(article.title);
        final ImageView imageView = (ImageView) view.findViewById(R.id.backdrop);


        if(!article.imageIDs.isEmpty()) {
            Glide.with(this)
                    .load(article.imageIDs.get((int) (Math.random() * article.imageIDs.size())))
                    .centerCrop()
                    .into(imageView);
        }

        WebView webview = (WebView) view.findViewById(R.id.contentWebView);
        webview.getSettings().setJavaScriptEnabled(true);
        webview.loadUrl("file:///android_asset/" + article.assetUrl);

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }


}
