package com.example.onetob.diancandemo.fragmentdemo.demo;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.onetob.diancandemo.fragmentdemo.MainActivity;
import com.example.onetob.diancandemo.fragmentdemo.R;

import androidx.fragment.app.Fragment;


public class ChannelFragment extends Fragment {

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        String msg = ((DomeActivity)context).sendMsg();
        Toast.makeText(context,"信息："+msg,Toast.LENGTH_SHORT).show();
        Log.d(MainActivity.TAG,"信息："+msg);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_channel,null);
        TextView tv = v.findViewById(R.id.channel_tv);
        //接受activity传过来的值
        Bundle bundle = getArguments();
        int code = bundle.getInt("code");
       String strMsg =  bundle.getString("msg1");
        tv.setText("编码是："+code+"   信息是："+strMsg);
        return v;
    }


}
