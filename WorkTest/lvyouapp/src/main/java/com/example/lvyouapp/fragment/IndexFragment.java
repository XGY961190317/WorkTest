package com.example.lvyouapp.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.example.lvyouapp.MainActivity;
import com.example.lvyouapp.R;
import com.example.lvyouapp.adapter.IndexVpAdapter;
import com.example.lvyouapp.util.DataUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

public class IndexFragment  extends Fragment {

    private ViewPager2 indexVp2;
    private LinearLayout pointers;
    private int index = 1;
    private Handler handler;
    private GridView gridView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_index,null);
        indexVp2 = view.findViewById(R.id.index_vp2);//获取控件
        pointers = view.findViewById(R.id.pointers);
        gridView = view.findViewById(R.id.grid);
        setViewPager();//设置vp
        setThread();
        setGridView();
        handler = new Handler(){
            @Override
            public void handleMessage(@NonNull Message msg) {
                super.handleMessage(msg);
                if (msg.what==1001){
                    indexVp2.setCurrentItem(msg.arg1);
                }
            }
        };
        indexVp2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Toast.makeText(getActivity(),v.getId()+"",Toast.LENGTH_SHORT).show();
            }
        });
        return view;
    }


//设置viewpager
    private void setViewPager() {
        //准备数据,通过DataUtile,indexVp_img获取
        //准备适配器
        IndexVpAdapter adapter = new IndexVpAdapter(getActivity(),DataUtil.idexVp_img);
        //设置适配器
        indexVp2.setAdapter(adapter);
        //设置与指示条关联
        indexVp2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                //选中当前页的时候调用
                //获取指示条的数量，通过其循环设置所有的指示条为未选中状态
                int ChildCount =  pointers.getChildCount();
                Log.d(MainActivity.TAG,ChildCount+"    indexVp2.reg");
                for (int i = 0; i <ChildCount ; i++) {
                    //随着滑动，指示条的索引页要做取长度的模处理
                    Log.d(MainActivity.TAG,i%DataUtil.idexVp_img.length+"    =i");
                    ((ImageView)pointers.getChildAt(i%DataUtil.idexVp_img.length)).setImageResource(R.drawable.dot_unselected);
                }
                //随着滑动，指示条的索引页要做取长度的模处理
                Log.d(MainActivity.TAG,position%DataUtil.idexVp_img.length+"    =position");
                View view = pointers.getChildAt(position%DataUtil.idexVp_img.length);
                Log.d(MainActivity.TAG,view.getClass().toString()+"    =view");
                ((ImageView)pointers.getChildAt(position%DataUtil.idexVp_img.length)).setImageResource(R.drawable.dot_selected);
                //设置当前页的指示为选中状态
            }
        });
    }
    //设置自动滑动
    private void setThread() {
        new Thread(){
            @Override
            public void run() {
                super.run();
                while(true){
                    try {
                        Thread.sleep(3000);//每隔3秒滑动一次
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    Message message = Message.obtain();
                    message.what=1001;
                    message.arg1=index;
                    handler.sendMessage(message);
                    index++;
                }
            }
        }.start();
    }
    //设置GridView
    private void setGridView() {
        //2，准备数据
        List<Map<String,Object>> gvList = initGvData();
        String from []={"img","txt"};
        int to[] ={R.id.gv_item_img,R.id.gv_item_txt};
        //3，设置适配器
        //准备适配器
        SimpleAdapter adapter = new SimpleAdapter(getContext(),gvList,R.layout.gv_item,from,to);
        gridView.setAdapter(adapter);
    }
//准备数据
    private List<Map<String,Object>> initGvData() {
        List<Map<String,Object>> gvList = new ArrayList<>();
        for (int i = 0; i <DataUtil.indexGv_img.length ; i++) {
            Map<String,Object> map = new HashMap<>();
            map.put("img",DataUtil.indexGv_img[i]);
            map.put("txt",DataUtil.indexGv_txt[i]);
            gvList.add(map);
        }
        return gvList;
    }

}
