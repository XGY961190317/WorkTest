package com.ontob.servicedemo.work.service;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Binder;
import android.os.IBinder;

import java.util.List;

public class InitApkInfoService extends Service {
    List<PackageInfo> packageInfos;

    public InitApkInfoService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
        packageInfos = getUserApkInfo();//初始化数据
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        //返回的值里面就包含了所需要的的apk信息集合
        return new ServiceBinder();
    }

    /**
     * 启动服务
     *
     * @param context 谁开启的服务
     */
    public static void startService(Context context) {
        context.startService(new Intent(context, InitApkInfoService.class));
    }

    public static void bindService(Context context, ServiceConnection conn) {
        context.bindService(new Intent(context, InitApkInfoService.class), conn, BIND_AUTO_CREATE);
    }

    public class ServiceBinder extends Binder {
        //获取到apk信息,将其返回
        public List<PackageInfo> getUserApkInfo() {
            return packageInfos;
        }
    }

    public List<PackageInfo> getUserApkInfo() {
        //获取所有的应用信息
       List<PackageInfo> packageInfos = this.getPackageManager().getInstalledPackages(PackageManager.MATCH_UNINSTALLED_PACKAGES);
       return packageInfos;
    }
}
