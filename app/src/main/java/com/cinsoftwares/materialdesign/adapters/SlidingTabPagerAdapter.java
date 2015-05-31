package com.cinsoftwares.materialdesign.adapters;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ImageSpan;

import com.cinsoftwares.materialdesign.R;
import com.cinsoftwares.materialdesign.activities.HomeActivity;
import com.cinsoftwares.materialdesign.fragments.MovieBoxOfficeFragment;
import com.cinsoftwares.materialdesign.fragments.MovieSearchFragment;
import com.cinsoftwares.materialdesign.fragments.MovieUpcommingFragment;

public class SlidingTabPagerAdapter extends FragmentStatePagerAdapter {

    Context context;
    String tabTitle[];
    int tabIcons[];

    public SlidingTabPagerAdapter(Context context, FragmentManager fm) {

        super(fm);
        this.context = context;
        tabTitle = context.getResources().getStringArray(R.array.tabTitle);
        tabIcons = new int[]{ R.drawable.ic_tongue, R.drawable.ic_wink, R.drawable.ic_lol };

    }

    @Override
    public Fragment getItem(int position) {

        Fragment currentItem = null;
        switch(position) {
            case HomeActivity.MOVIES_SEARCH:
                currentItem = MovieSearchFragment.newInstance("", "");
                break;
            case HomeActivity.MOVIES_BOX_OFFICE:
                currentItem = MovieBoxOfficeFragment.newInstance("", "");
                break;
            case HomeActivity.MOVIES_UPCOMMING:
                currentItem = MovieUpcommingFragment.newInstance("", "");
                break;
        }

        return currentItem;

    }

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public CharSequence getPageTitle(int position) {

        Drawable drawable = context.getResources().getDrawable(tabIcons[position]);
        drawable.setBounds(0, 0, 36, 36);
        ImageSpan imageSpan = new ImageSpan(drawable);
        SpannableString spannableString = new SpannableString(" ");
        spannableString.setSpan(imageSpan, 0, spannableString.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        return spannableString;
    }
}
