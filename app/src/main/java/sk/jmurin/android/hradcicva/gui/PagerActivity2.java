package sk.jmurin.android.hradcicva.gui;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.globallogic.training.hradcicva.R;
import sk.jmurin.android.hradcicva.data.Article;
import sk.jmurin.android.hradcicva.data.Database;
import sk.jmurin.android.hradcicva.gui.detail.ImageDetailActivity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class PagerActivity2 extends AppCompatActivity {

    public static final String MENU_ID = "buttonID";
    public static final String TAG = PagerActivity2.class.getSimpleName();
    public static Context CONTEXT;
    private int menuID;
    private String topicName;
    private ImageView imageView;
    private CollapsingToolbarLayout collapsingToolbar;
    private int articlePos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pager2);

        SharedPreferences sharedPref = getSharedPreferences(getString(R.string.preference_file_key), Context.MODE_PRIVATE);
        int hintCount = sharedPref.getInt(getString(R.string.hintToastCount), 0);
        if (hintCount < 3) {
            Toast.makeText(this, "Kliknutím na obrázok zobrazíte galériu.", Toast.LENGTH_LONG).show();
            SharedPreferences.Editor editor = sharedPref.edit();
            editor.putInt(getString(R.string.hintToastCount), hintCount + 1);
            editor.commit();
        }

        CONTEXT = this;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.BLACK);
        }

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            menuID = extras.getInt(MENU_ID);
            // Log.d(TAG, " onCreate with MENU_ID: " + menuID);
            topicName = Database.getTopicName(menuID);
        } else {
            throw new RuntimeException("instantiated fragment without arguments.");
        }

        collapsingToolbar = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        //  Log.i(TAG, "CollapsingToolbarLayout=" + collapsingToolbar + " title=[" + Database.getTopicName(menuID) + "]");
        collapsingToolbar.setTitle(Database.getTopicName(menuID));
        imageView = (ImageView) findViewById(R.id.backdrop);


        ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);
        if (viewPager != null) {
            setupViewPager(viewPager);
        }

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                // Log.d(TAG, "onPageSelected");
                Article article = Database.getArticle(menuID, position);
                if (!article.images.isEmpty()) {
                    Glide.with(PagerActivity2.CONTEXT)
                            .load(article.images.get((int) (Math.random() * article.images.size())).resID)
                            .centerCrop()
                            .into(imageView);
                    articlePos = position;
                }

            }

            @Override
            public void onPageScrollStateChanged(int state) {
                //  Log.d(TAG, "pageScrolled");


            }
        });

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);

        Article article = Database.getArticle(menuID, 0);
        if (!article.images.isEmpty()) {
            Glide.with(PagerActivity2.CONTEXT)
                    .load(article.images.get((int) (Math.random() * article.images.size())).resID)
                    .centerCrop()
                    .into(imageView);
            articlePos = 0;
        }

    }

    public void onImageClicked(View v) {
        // System.out.println("image clicked");
        final Intent i = new Intent(this, ImageDetailActivity.class);
        i.putExtra(ImageDetailActivity.IMAGES, (Serializable) Database.getArticle(menuID, articlePos).images);
        startActivity(i);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.sample_actions, menu);
        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }


    private void setupViewPager(ViewPager viewPager) {
        Adapter adapter = new Adapter(getSupportFragmentManager());
        String[] tabs = Database.getTabs(menuID);
        for (int i = 0; i < tabs.length; i++) {
            adapter.addFragment(PagerActivityFragment2.getInstance(menuID, i), tabs[i]);
        }
        viewPager.setAdapter(adapter);
    }

    static class Adapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragments = new ArrayList<>();
        private final List<String> mFragmentTitles = new ArrayList<>();

        public Adapter(FragmentManager fm) {
            super(fm);
        }

        public void addFragment(Fragment fragment, String title) {
            mFragments.add(fragment);
            mFragmentTitles.add(title);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragments.get(position);
        }

        @Override
        public int getCount() {
            return mFragments.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitles.get(position);
        }


    }


}

