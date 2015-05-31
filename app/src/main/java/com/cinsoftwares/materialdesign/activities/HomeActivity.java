package com.cinsoftwares.materialdesign.activities;

import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.cinsoftwares.materialdesign.adapters.SlidingTabPagerAdapter;
import com.cinsoftwares.materialdesign.fragments.NavigationDrawerFragment;
import com.cinsoftwares.materialdesign.R;
import com.cinsoftwares.materialdesign.views.tabs.SlidingTabLayout;


public class HomeActivity extends ActionBarActivity {


    Toolbar toolbar;
    DrawerLayout drawerLayout;
    NavigationDrawerFragment navigationDrawerFragment;

    ViewPager viewPager;
    SlidingTabLayout slidingTabLayout;


    public static final int MOVIES_BOX_OFFICE = 0;
    public static final int MOVIES_UPCOMMING = 1;
    public static final int MOVIES_SEARCH = 2;

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
        viewPager.setAdapter(new SlidingTabPagerAdapter(this, getSupportFragmentManager()));
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




    /*public static class MyFragment extends Fragment {

        private TextView text;

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

            View layout = inflater.inflate(R.layout.fragment_sample, container, false);
            text = (TextView) layout.findViewById(R.id.textView);
            Bundle bundle = getArguments();
            if (null != bundle) text.setText("The tab selected is @ " + bundle.getInt("position"));


            setUpVolley();


            return layout;
        }

        public static MyFragment getInstance(int position) {

            MyFragment fragment = new MyFragment();
            Bundle bundle = new Bundle();
            bundle.putInt("position", position);
            fragment.setArguments(bundle);
            return fragment;
        }

        public void setUpVolley() {

            RequestQueue queue = VolleySingleton.getInstance().getRequestQueue();
            StringRequest request = new StringRequest(Request.Method.GET, "http://api.rottentomatoes.com/api/public/v1.0/lists/movies/box_office.json?apikey=efeertvrdgg75jxakpjdrmtj",

                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {

                            Toast.makeText(getActivity(), response, Toast.LENGTH_SHORT).show();
                        }
                    },

                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {

                            Toast.makeText(getActivity(), error.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
            queue.add(request);

        }
    }*/
}



