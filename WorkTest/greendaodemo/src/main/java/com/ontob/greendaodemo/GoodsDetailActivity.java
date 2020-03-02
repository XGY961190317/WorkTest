package com.ontob.greendaodemo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.ontob.greendaodemo.goodsmodule.GoodsModule;


public class GoodsDetailActivity extends Activity {
    public static final String GOODS_MODEL = "goodsModel";
    GreenDaoManager greenDaoManager;
    private GoodsModule goodsModule;
    private EditText et;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goods_detail);
        goodsModule = getIntent().getParcelableExtra(GOODS_MODEL);
        et = findViewById(R.id.et_info);
        et.setText(goodsModule.getInfo());
        greenDaoManager = new GreenDaoManager(this);
    }

    /**
     * 修改信息保存编辑点击事件
     *
     * @param view
     */
    public void onEditClick(View view) {
        String info = et.getText().toString();
        goodsModule.setInfo(info);
        greenDaoManager.uodateGoodsInfo(goodsModule);
        onBackPressed();
    }

    /**
     * 删除商品
     *
     * @param view
     */
    public void onDelClick(View view) {
        String info = et.getText().toString();
        goodsModule.setInfo(info);
        greenDaoManager.deleteGoodsInfo(goodsModule);
        onBackPressed();
    }
}
