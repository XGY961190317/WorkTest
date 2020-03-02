package com.example.onetob.diancandemo.viewpagerdemo.usermolder2.adapter;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class MyUserViewPagerDemo2Adapter extends FragmentStateAdapter {
    List<Fragment> mUserVplist;
    public MyUserViewPagerDemo2Adapter(@NonNull FragmentManager fragmentManager,List<Fragment> user_vplist) {
        super(fragmentManager);
        mUserVplist=user_vplist;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return mUserVplist.get(position);
    }

    @Override
    public int getItemCount() {
        return mUserVplist.size();
    }
}
