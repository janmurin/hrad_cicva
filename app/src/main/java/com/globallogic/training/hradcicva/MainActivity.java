package com.globallogic.training.hradcicva;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Point;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements MainButtonsFragment.MainButtonsFragmentClickListener {

    public static final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, " onCreate " + this);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Hrad cicva");
        setSupportActionBar(toolbar);
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
//        getSupportFragmentManager()
//                .beginTransaction()
//                .add(R.id.content_frame, MainButtonsFragment.newInstance(), MainButtonsFragment.TAG)
//                .commit();
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d(TAG, " onPause " + this);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
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

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void OnButtonClick(int id) {
        Toast.makeText(
                this,
                "clicked button id: " + id, Toast.LENGTH_SHORT)
                .show();
        if (id == 0) {
//            getSupportFragmentManager()
//                    .beginTransaction()
//                    .add(R.id.content_frame, new PagerFragment(), PagerFragment.TAG)
//                    .addToBackStack(PagerFragment.TAG)
//                    .commit();
            Log.d(TAG, "onButtonClick id: " + id);
            Intent detailActivity = new Intent(this, PagerActivity.class);
            Bundle extras = new Bundle();
            extras.putInt(PagerActivity.MENU_ID, id);
            detailActivity.putExtras(extras);
            startActivity(detailActivity);
        }
    }
}
