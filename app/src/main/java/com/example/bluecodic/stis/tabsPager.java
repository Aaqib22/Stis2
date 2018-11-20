package com.example.bluecodic.stis;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

/**
 * Created by BlueCodic on 4/2/2018.
 */

public class tabsPager extends FragmentStatePagerAdapter {


    String[] titles = new String[]{"","",""};

    public tabsPager(FragmentManager fm) {
        super(fm);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titles[position];
    }

    @Override
    public Fragment getItem(int position) {

    switch (position)
    {
        case 0:
            BlankFragment blankFragment=new BlankFragment();
            return blankFragment;
        case 1:
            Blank2Fragment blank2Fragment = new Blank2Fragment();
            return blank2Fragment;
        case 2:
            Blank3Fragment blank3Fragment = new Blank3Fragment();
            return blank3Fragment;
    }

        return null;
    }

    @Override
    public int getCount() {
        return 3;
    }



}
