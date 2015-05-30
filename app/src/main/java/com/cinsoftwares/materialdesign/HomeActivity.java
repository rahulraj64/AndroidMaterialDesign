package com.cinsoftwares.materialdesign;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ImageSpan;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.cinsoftwares.materialdesign.tabs.SlidingTabLayout;


public class HomeActivity extends ActionBarActivity {


    Toolbar toolbar;
    DrawerLayout drawerLayout;
    NavigationDrawerFragment navigationDrawerFragment;

    ViewPager viewPager;
    SlidingTabLayout slidingTabLayout;

    int []tabIcons = {R.drawable.ic_sad, R.drawable.ic_lol, R.drawable.ic_tongue};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

         setContentView(R.layout.activity_home_nav_drawer_overlap);
        //setContentView(R.layout.activity_home_nav_drawer_non_overlap);

        toolbar = (Toolbar) findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);

        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        navigationDrawerFragment = (NavigationDrawerFragment)
                                    getSupportFragmentManager().findFragmentById(R.id.fragment_nav_drawer);

        navigationDrawerFragment.setupDrawer(drawerLayout, toolbar);


        viewPager = (ViewPager) findViewById(R.id.pager);
        viewPager.setAdapter(new MyPagerAdapter(getSupportFragmentManager()));
        slidingTabLayout = (SlidingTabLayout) findViewById(R.id.slidingTab);
        slidingTabLayout.setCustomTabView(R.layout.custom_tab, R.id.tabTxt);
        slidingTabLayout.setDistributeEvenly(true);
        slidingTabLayout.setBackgroundColor(getResources().getColor(R.color.primaryColor));
        slidingTabLayout.setSelectedIndicatorColors(getResources().getColor(R.color.accentColor));
        slidingTabLayout.setViewPager(viewPager);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {


        int id = item.getItemId();

        if (id == R.id.navigate) {

            startActivity(new Intent(this, SubActivity.class));
        }

        return super.onOptionsItemSelected(item);
    }



    class  MyPagerAdapter extends FragmentPagerAdapter {

        String tabTitle[];

        private MyPagerAdapter(FragmentManager fm) {
            super(fm);
            tabTitle = getResources().getStringArray(R.array.tabTitle);

        }

        @Override
        public Fragment getItem(int position) {

            MyFragment fragment = MyFragment.getInstance(position);
            return fragment;
        }

        @Override
        public int getCount() {
            return 3;
        }

        @Override
        public CharSequence getPageTitle(int position) {

            Drawable drawable = getResources().getDrawable(tabIcons[position]);
            drawable.setBounds(0,0,36,36 );
            ImageSpan imageSpan = new ImageSpan(drawable);
            SpannableString spannableString = new SpannableString(" ");
            spannableString.setSpan(imageSpan, 0, spannableString.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

            return spannableString;
        }
    }



    public static class MyFragment extends Fragment {

        private TextView text;
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

            View layout = inflater.inflate(R.layout.fragment_sample, container, false);
            text = (TextView) layout.findViewById(R.id.textView);
            Bundle bundle = getArguments();
            if(null != bundle) text.setText("The tab selected is @ " + bundle.getInt("position"));
            return layout;
        }

        public static MyFragment getInstance(int position) {

            MyFragment fragment = new MyFragment();
            Bundle bundle = new Bundle();
            bundle.putInt("position", position);
            fragment.setArguments(bundle);
            return fragment;
        }
    }
}



