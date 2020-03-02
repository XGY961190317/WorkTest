package com.ontob.greendaodemo;

import android.content.Context;

import com.ontob.greendaodemo.Utils.DataUtils;
import com.ontob.greendaodemo.adapter.RecyclerAdapter;
import com.ontob.greendaodemo.goodsmodule.GoodsModule;
import com.ontob.greendaodemo.goodsmodule.GoodsModuleDao;

import org.greenrobot.greendao.query.QueryBuilder;

import java.util.List;

public class GreenDaoManager {
    private Context mContext;
    private GoodsModuleDao mGoodsModule;

    public GreenDaoManager(Context context) {
        mContext = context;
        mGoodsModule = MyApplication.mSession.getGoodsModuleDao();//获取到实体类
    }

    /**
     * 添加所有数据到数据库
     */
    public void insertGoods() {
        String json = DataUtils.getJson("goods.json", mContext);
        List<GoodsModule> goodsModuleList = DataUtils.getGoodsModels(json);
        mGoodsModule.insertInTx(goodsModuleList);
    }

    /**
     * 查询所有数据
     *
     * @return
     */
    public List<GoodsModule> queryGoods() {
//        queryBuilder
        QueryBuilder<GoodsModule> queryBuilder = mGoodsModule.queryBuilder();
        queryBuilder = queryBuilder.orderAsc(GoodsModuleDao.Properties.GoodsId);//通过goodsId排序
        return queryBuilder.list();
    }

    /**
     * 筛选水果
     *
     * @return
     */
    public List<GoodsModule> queryFruts() {
        QueryBuilder<GoodsModule> query = mGoodsModule.queryBuilder();
        query.where(GoodsModuleDao.Properties.Type.eq(0))
                .orderAsc(GoodsModuleDao.Properties.GoodsId);
        return query.list();
    }

    /**
     * 筛选零食
     *
     * @return
     */
    public List<GoodsModule> querySnaks() {
        QueryBuilder<GoodsModule> queryBuilder = mGoodsModule.queryBuilder()
                .where(GoodsModuleDao.Properties.Type.eq(1))
                .orderAsc(GoodsModuleDao.Properties.GoodsId);
        return queryBuilder.list();
    }

    /**
     * 删除指定的数据
     * @param goodsModule
     */
    public void deleteGoodsInfo(GoodsModule goodsModule){
        mGoodsModule.delete(goodsModule);
    }

    /**
     * 修改指定数据，更新数据
     * @param goodsModule
     */
    public void uodateGoodsInfo(GoodsModule goodsModule){
        mGoodsModule.update(goodsModule);
    }

}
