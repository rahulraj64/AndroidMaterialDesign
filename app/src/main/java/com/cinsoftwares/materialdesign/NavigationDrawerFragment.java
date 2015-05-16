package com.cinsoftwares.materialdesign;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;



public class NavigationDrawerFragment extends Fragment {

    private static final String PREFERENCE_NAME = "Navigation_Preferences";
    private static final String KEY_NAVIGATION_DRAWER_OPEN_STATUS = "Navigation_drawer_open_status";
    ActionBarDrawerToggle drawerToggle;
    private boolean isDrawerOpenedBefore;

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
        return inflater.inflate(R.layout.fragment_navigation_drawer, container, false);
    }


    public void setupDrawer(DrawerLayout drawerLayout, Toolbar toolbar) {

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

    public static void saveToPreference(Context context, String key, String value) {

        context.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE).edit().putString(key, value).apply();
    }
    public static String getFromPreferences(Context context, String key) {

        return context.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE).getString(key, "");
    }
}
