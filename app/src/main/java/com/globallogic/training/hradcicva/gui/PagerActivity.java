package com.globallogic.training.hradcicva.gui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.globallogic.training.hradcicva.data.Database;
import com.globallogic.training.hradcicva.fragments.PagerFragment;
import com.globallogic.training.hradcicva.R;

import java.io.IOException;
import java.util.Arrays;

public class PagerActivity extends AppCompatActivity {

    public static final String MENU_ID = "menu_id";
    public static final String TAG = PagerActivity.class.getSimpleName();
    private int menuID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pager);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            menuID = extras.getInt(MENU_ID);
            Log.d(TAG, " onCreate with MENU_ID: " + menuID);

            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.pager_activity_content_frame, PagerFragment.newInstance(menuID), PagerFragment.TAG)
                    .commit();
        } else {
            throw new RuntimeException("instantiated fragment without arguments.");
        }

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbarPager);
        toolbar.setTitle(Database.getTopicName(menuID));
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().hide();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        if (id == android.R.id.home) {
            onBackPressed();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Log.d(TAG, "onBackPressed");
    }
}
