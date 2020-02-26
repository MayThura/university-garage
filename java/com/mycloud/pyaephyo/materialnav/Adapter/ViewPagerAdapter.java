package com.mycloud.pyaephyo.materialnav.Adapter;

/**
 * Created by Windows on 10/26/2016.
 */

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.mycloud.pyaephyo.materialnav.Fragment.DetailDataFragment;
import com.mycloud.pyaephyo.materialnav.Fragment.LivingFragment;
import com.mycloud.pyaephyo.materialnav.Fragment.OpportunityFragment;

/**
 * 
 */
public class ViewPagerAdapter extends FragmentPagerAdapter {

    CharSequence Titles[]; // This will Store the Titles of the Tabs which are Going to be passed when ViewPagerAdapter is created
    int NumbOfTabs; // Store the number of tabs, this will also be passed when the ViewPagerAdapter is created



    public ViewPagerAdapter(FragmentManager fm,CharSequence mTitles[], int mNumbOfTabsumb) {
        super(fm);

        this.Titles = mTitles;
        this.NumbOfTabs = mNumbOfTabsumb;

    }

    //This method return the fragment for the every position in the View Pager
    @Override
    public Fragment getItem(int position) {
        switch(position) {
            case 0:
                DetailDataFragment detailData = new DetailDataFragment();
                return detailData;
            case 1:
            OpportunityFragment scholar = new OpportunityFragment();
            return scholar;
            case 2:
                LivingFragment livingFragment = new LivingFragment();
                return livingFragment;
        }
        return null;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return Titles[position];
    }

    // This method return the Number of tabs for the tabs Strip

    @Override
    public int getCount() {
        return NumbOfTabs;
    }
}