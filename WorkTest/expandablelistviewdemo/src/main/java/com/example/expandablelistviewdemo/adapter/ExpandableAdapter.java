package com.example.expandablelistviewdemo.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.expandablelistviewdemo.R;
import com.example.expandablelistviewdemo.bean.Chapter;
import com.example.expandablelistviewdemo.bean.ChapterItem;

import java.util.List;

public class ExpandableAdapter extends BaseExpandableListAdapter {
    private Context mContext;
    private List<Chapter> mListData;
    private LayoutInflater inflater;

    public ExpandableAdapter(Context context, List<Chapter> listData) {
        this.mContext = context;
        this.mListData = listData;
        inflater = LayoutInflater.from(context);
    }

    //分组的个数
    @Override
    public int getGroupCount() {
        return mListData.size();
    }

    //分组下面子项的个数
    @Override
    public int getChildrenCount(int groupPosition) {
        return mListData.get(groupPosition).getChildren().size();
    }

    //分组
    @Override
    public Object getGroup(int groupPosition) {
        return mListData.get(groupPosition);
    }

    //分组的子项
    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return mListData.get(groupPosition).getChildren().get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return mListData.get(groupPosition).getId();
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return mListData.get(groupPosition).getChildren().get(childPosition).getId();
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    /**
     * boolean isExpanded  判断是否选中
     * @param groupPosition
     * @param isExpanded
     * @param convertView
     * @param parent
     * @return
     */
    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {

        ParentViewHolder pvh = null;
        if(convertView==null){
            //参数2：为null时，父类控件的margin属性没有作用，显示看起来会很乱，别扭

            //参数3：false 返回的是expan_parent_layout
            //true  返回的是parent
            convertView = inflater.inflate(R.layout.expan_parent_layout,parent,false);
            pvh = new ParentViewHolder();
            pvh.parentName = convertView.findViewById(R.id.item_parent_name);
            pvh.parentIv = convertView.findViewById(R.id.iv_parent);
            convertView.setTag(pvh);
        }else {
            pvh = (ParentViewHolder) convertView.getTag();
        }
        Chapter chapter = mListData.get(groupPosition);
        pvh.parentName.setText(chapter.getName());
        pvh.parentIv.setSelected(isExpanded);
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {

        ChildViewHolder cvh = null;
        if(convertView==null){
            convertView = inflater.inflate(R.layout.expan_child_layout,parent,false);
            cvh = new ChildViewHolder();
            cvh.childName = convertView.findViewById(R.id.item_child_name);
            convertView.setTag(cvh);
        }else {
            cvh = (ChildViewHolder) convertView.getTag();
        }
        ChapterItem chapterItem = mListData.get(groupPosition).getChildren().get(childPosition);
        cvh.childName.setText(chapterItem.getName());
        return convertView;
    }

    //子项是否可以点击
    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
    static class ParentViewHolder{
        TextView parentName;
        ImageView parentIv;
    }
    static class ChildViewHolder{
        TextView childName;
    }
}
