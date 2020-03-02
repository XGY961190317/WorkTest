package com.ontob.servicedemo.work.activity;

import androidx.appcompat.app.AppCompatActivity;
import butterknife.BindView;
import butterknife.ButterKnife;

import android.content.ComponentName;
import android.content.ServiceConnection;
import android.content.pm.PackageInfo;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.ontob.servicedemo.R;
import com.ontob.servicedemo.work.adapter.ApkInfoAdapter;
import com.ontob.servicedemo.work.service.InitApkInfoService;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private List<PackageInfo> packageInfos;
    @BindView(R.id.search_et)
    EditText searchEt;
    @BindView(R.id.apk_lv)
    ListView searchResultLv;
    private List<PackageInfo> infos = new ArrayList<>();
    /**
     * 处理service返回回来的数据
     */
    ServiceConnection conn = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder binder) {
            InitApkInfoService.ServiceBinder serviceBinder = (InitApkInfoService.ServiceBinder) binder;
            List<PackageInfo>  ServiceGetinfos = serviceBinder.getUserApkInfo();
            packageInfos = ServiceGetinfos;
            infos.addAll(ServiceGetinfos);
            initEvent(packageInfos);
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };
    private ApkInfoAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        InitApkInfoService.bindService(this, conn);

    }

    private void initEvent(List<PackageInfo> baseInfoList) {
        adapter = new ApkInfoAdapter(this, packageInfos);
        searchResultLv.setAdapter(adapter);
    }

    /**
     * 点击开始搜索
     *
     * @param view
     */
    public void startSearch(View view) {
        packageInfos.addAll(infos);//每次进来保证查询的list都是所有的里面查
        Log.d("TAG",infos.size()+"");
        searchApk();
    }

    private void searchApk() {
        String searchContent = searchEt.getText().toString();
            List<PackageInfo> resultList = new ArrayList<>();
            for (PackageInfo packages : packageInfos) {//
                String apkName = packages.applicationInfo.loadLabel(this.getPackageManager()).toString();

                if (apkName.contains(searchContent)) {//查看手否包含输入的内容
                    resultList.add(packages);
                }
            }

            if (resultList.isEmpty()) {
                packageInfos.clear();
                Toast.makeText(this, "不存在相关apk,请重新输入", Toast.LENGTH_SHORT).show();
                adapter.notifyDataSetChanged();
                return;
            }
            packageInfos.clear();
            packageInfos.addAll(resultList);
            adapter.notifyDataSetChanged();

    }
}
