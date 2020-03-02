package com.example.lvyouapp.adapter;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class MyVp2Adapter  extends FragmentStateAdapter {
    List<Fragment> mListData;
    public MyVp2Adapter(@NonNull FragmentManager fragmentManager,List<Fragment> listData) {
        super(fragmentManager);
        mListData = listData;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return mListData.get(position);
    }

    @Override
    public int getItemCount() {
        return mListData.size();
    }
}
