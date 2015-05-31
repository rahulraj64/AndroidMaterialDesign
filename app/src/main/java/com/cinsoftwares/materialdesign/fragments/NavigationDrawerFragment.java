package com.cinsoftwares.materialdesign.fragments;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.cinsoftwares.materialdesign.R;
import com.cinsoftwares.materialdesign.adapters.NavDrawerAdapter;
import com.cinsoftwares.materialdesign.pojo.ListItem;

import java.util.ArrayList;
import java.util.List;


public class NavigationDrawerFragment extends Fragment {

    private static final String PREFERENCE_NAME = "Navigation_Preferences";
    private static final String KEY_NAVIGATION_DRAWER_OPEN_STATUS = "Navigation_drawer_open_status";
    private static final String TAG = "drawer";
    ActionBarDrawerToggle drawerToggle;
    private boolean isDrawerOpenedBefore;
    RecyclerView recyclerView;
    NavDrawerAdapter adapter;

    public NavigationDrawerFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        isDrawerOpenedBefore = Boolean.valueOf(getFromPreferences(getActivity(), KEY_NAVIGATION_DRAWER_OPEN_STATUS));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View layout = inflater.inflate(R.layout.fragment_navigation_drawer, container, false);
        recyclerView = (RecyclerView) layout.findViewById(R.id.recyclerView1);
        adapter = new NavDrawerAdapter(getActivity(), getItemList());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getActivity(), recyclerView, new ItemTouchListener() {
            @Override
            public void onClick(View view, int position) {

                Toast.makeText(getActivity(), "gesturdetector - clicked @ " + position, Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onLongClick(View v, int position) {
                Toast.makeText(getActivity(), "gesturdetector - long clicked @ " + position, Toast.LENGTH_SHORT).show();

            }
        }));
        return layout;
    }


    public void setupDrawer(DrawerLayout drawerLayout, final Toolbar toolbar) {

        drawerToggle = new ActionBarDrawerToggle(getActivity(), drawerLayout, toolbar, R.string.drawer_open, R.string.drawer_close){

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                if(!isDrawerOpenedBefore) {
                    isDrawerOpenedBefore = true;
                    saveToPreference(getActivity(), KEY_NAVIGATION_DRAWER_OPEN_STATUS, "true");
                }

                getActivity().invalidateOptionsMenu();

            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                getActivity().invalidateOptionsMenu();
            }

            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
                super.onDrawerSlide(drawerView, slideOffset);
                if(slideOffset < 0.7)toolbar.setAlpha( 1- slideOffset);
            }
        };

        if(!isDrawerOpenedBefore) {
            drawerLayout.openDrawer(getActivity().findViewById(R.id.fragment_nav_drawer));
            saveToPreference(getActivity(), KEY_NAVIGATION_DRAWER_OPEN_STATUS, "true");
        }
        drawerLayout.setDrawerListener(drawerToggle);
        drawerLayout.post(new Runnable() {
            @Override
            public void run() {
                drawerToggle.syncState();
            }
        });
    }

    class RecyclerTouchListener implements RecyclerView.OnItemTouchListener
    {

        GestureDetector gestureDetector;
        ItemTouchListener clickListener;
        RecyclerTouchListener(Context context, final RecyclerView recyclerView, final ItemTouchListener clickListener)
        {
            Log.i(TAG, "item touch listener instantiated  ");
            this.clickListener = clickListener;
            gestureDetector = new GestureDetector(context, new GestureDetector.SimpleOnGestureListener(){

                @Override
                public boolean onSingleTapUp(MotionEvent e) {
                    Log.i(TAG, "onSingleTap " + e);
                    //return super.onSingleTapUp(e);
                    return true;
                }

                @Override
                public void onLongPress(MotionEvent e) {
                    Log.i(TAG, "onLongPress " + e);
                    View child = recyclerView.findChildViewUnder(e.getX(), e.getY());
                    if(child != null && clickListener != null)
                    {
                        clickListener.onLongClick(child, recyclerView.getChildPosition(child));
                    }
                    super.onLongPress(e);
                }
            });


        }
        @Override
        public boolean onInterceptTouchEvent(RecyclerView recyclerView, MotionEvent motionEvent) {
            Log.i(TAG, "onInterceptTouchEvent " + motionEvent);
            //gestureDetector.onTouchEvent(motionEvent);
            View child = recyclerView.findChildViewUnder(motionEvent.getX(), motionEvent.getY());
            if(child != null && clickListener != null && gestureDetector.onTouchEvent(motionEvent))
            {
                clickListener.onClick(child, recyclerView.getChildPosition(child));
            }
            return false;
        }

        @Override
        public void onTouchEvent(RecyclerView recyclerView, MotionEvent motionEvent) {

            Log.i(TAG, "onTouchEvent " + motionEvent);
        }
    }

    interface ItemTouchListener {

         void onClick(View view, int position);
         void onLongClick(View v, int position);
    }

    public static List<ListItem>  getItemList() {

        int []icons = {R.drawable.ic_cool, R.drawable.ic_lol, R.drawable.ic_sad, R.drawable.ic_tongue, R.drawable.ic_wink};
        String []titles = {"Cool", "Lol", "Sad", "Tongue", "Wink"};
        List<ListItem> itemList = new ArrayList<>();
        for (int i=0; i<50; i++) {
            ListItem item = new ListItem();
            item.setImageId(icons[i % titles.length]);
            item.setTitle(titles[i % titles.length]);
            itemList.add(item);
        }
        return itemList;
    }
    public static void saveToPreference(Context context, String key, String value) {

        context.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE).edit().putString(key, value).apply();
    }
    public static String getFromPreferences(Context context, String key) {

        return context.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE).getString(key, "");
    }
}
