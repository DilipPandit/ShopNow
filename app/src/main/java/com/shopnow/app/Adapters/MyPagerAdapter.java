package com.shopnow.app.Adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import com.halzhang.android.library.BottomTabFragmentPagerAdapter;
import com.shopnow.app.Fragments.CartFragment;
import com.shopnow.app.Fragments.ProductFragment;

public class MyPagerAdapter extends BottomTabFragmentPagerAdapter {

    int[] tabIcons;
    String[] tabTitle;

    public MyPagerAdapter(String[] tabTitle, int[] tabIcons, FragmentManager fragmentManager) {
        super(fragmentManager);
        this.tabIcons = tabIcons;
        this.tabTitle = tabTitle;
    }


    @Override
    public int getCount() {
        return tabTitle.length;
    }

    @Override
    public Fragment getItem(int position) {
        if (position == 0)
            return new ProductFragment();
        else
            return new CartFragment();
    }

    @Override
    public int getPageIcon(int position) {
        return tabIcons[position];
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return tabTitle[position];
    }


}