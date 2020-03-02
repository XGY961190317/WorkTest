package com.ontob.servicedemo.work.adapter;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.ontob.servicedemo.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ApkInfoAdapter extends BaseAdapter {
    private Context mContect;
    private List<PackageInfo> mPackageInfos;
    private LayoutInflater inflater;

    public ApkInfoAdapter(Context mcontect, List<PackageInfo> mPackageInfos) {
        this.mContect = mcontect;
        this.mPackageInfos = mPackageInfos;
        inflater = LayoutInflater.from(mContect);
    }

    @Override
    public int getCount() {
        return mPackageInfos.size();
    }

    @Override
    public Object getItem(int position) {
        return mPackageInfos.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.apk_info_layout, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.apkName.setText(mPackageInfos.get(position).applicationInfo.loadLabel(mContect.getPackageManager()).toString());
        holder.apkIcon.setImageDrawable(mPackageInfos.get(position).applicationInfo.loadIcon(mContect.getPackageManager()));
        return convertView;
    }

    static class ViewHolder {
        @BindView(R.id.id_apk_name)
        TextView apkName;
        @BindView(R.id.id_apk_icon)
        ImageView apkIcon;

        public ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
