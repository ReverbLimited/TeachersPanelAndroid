/*
 * Copyright (c) 2016. Ted Park. All Rights Reserved
 */

package com.gun0912.tedpicker;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.Log;


public class PagerAdapter_Picker extends FragmentPagerAdapter {


    String[] tab_titles;
    String swap;
    final int swapInt;


    public PagerAdapter_Picker(Context context, FragmentManager fm, int i) {
        super(fm);
            tab_titles = context.getResources().getStringArray(R.array.tab_titles);
            swapInt = i;
            if (i==1){

                swap = tab_titles[0];
                tab_titles[0] = tab_titles[1];
                tab_titles[1] = swap;
                Log.d( "pageAdapter", "Swapping and i = "+i );
            }
//            if (i==1){
//                swapInt = tab_titles[]
//            }

    }

    @Override
    public CharSequence getPageTitle(int position) {
        return tab_titles[position];
    }

    @Override
    public int getCount() {
        return tab_titles.length;
    }

    @Override
    public Fragment getItem(int position) {

        Log.d( "pageAdapter","position  ------> "+position );
        if (position != swapInt){
            Log.d( "pageAdapter","Enter If condition" );
            CwacCameraFragment profileInfoFragment = new CwacCameraFragment();
            CwacCameraFragment.setConfig(ImagePickerActivity.getConfig());
            return profileInfoFragment;
        }else {
            Log.d( "pageAdapter","Enter Else condition" );

            return new GalleryFragment();
        }

//        switch (position) {
//
//            case swapInt:
//
//
//            case swapInt:
//
//
//            default:
//                return null;
//        }


    }


}
