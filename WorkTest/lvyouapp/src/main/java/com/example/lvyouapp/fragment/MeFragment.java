package com.example.lvyouapp.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.example.lvyouapp.R;
import com.example.lvyouapp.util.DataUtil;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class MeFragment extends Fragment {

    private List<Map<String, Object>>  meListData = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_me,null);
        setListView(view);

        return view;
    }

    private void setListView(View view) {
        //1,获取控件
        ListView meLv = view.findViewById(R.id.meLv);
        //2,准备数据

        initData();
        //3,设置适配器
        String from []= {"img","text"};//map中的key值
        int to []= {R.id.lv_item_img,R.id.lv_item_tv};
        SimpleAdapter adapter = new SimpleAdapter(getContext(),meListData,R.layout.me_lv_item,from,to);
        meLv.setAdapter(adapter);
        meLv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });
    }
    public void initData(){
        for (int i=0;i<6;i++){
        Map<String,Object> map = new HashMap<>();
            map.put("img",DataUtil.me_img[i]);
            map.put("text",DataUtil.me_test[i]);
            meListData.add(map);
        }
    }
}
