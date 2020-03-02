package com.example.lvyouapp.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.lvyouapp.R;
import com.example.lvyouapp.adapter.FindLvAdapter;
import com.example.lvyouapp.util.DataUtil;
import com.example.lvyouapp.util.Msg;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class FindFragment extends Fragment {

    private ListView lv;
    private List<Msg> msgList;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_find, null);
        lv = view.findViewById(R.id.lv);
        setLv();
        return view;
    }

    private void setLv() {
        //设置lv头部
        View headerView = LayoutInflater.from(getContext()).inflate(R.layout.find_lv_top_layout,null);
        lv.addHeaderView(headerView);
        //准备数据
        final List<Msg> msgList = initData();
        final FindLvAdapter adapter = new FindLvAdapter(getContext(),msgList);
        lv.setAdapter(adapter);
        //设置点赞事件
        adapter.setOnLikeListener(new FindLvAdapter.MyOnLikeListener() {
            @Override
            public void onLike(boolean isLikeCheck, int itemPosition) {
                msgList.get(itemPosition).setLike(isLikeCheck);//更新几何数据
                adapter.notifyDataSetChanged();//更新adapter
            }
        });
    }

    private List<Msg> initData() {
        List<Msg> msgList = new ArrayList<>();
        for (int i = 0; i < DataUtil.findlv_img.length; i++) {
            Msg msg = new Msg(DataUtil.findlv_img[i],DataUtil.findlv_img_txt[i]+i,"今天天气好晴朗，处处好风光", false); msgList.add(msg);
        }
        return msgList;
    }
}
