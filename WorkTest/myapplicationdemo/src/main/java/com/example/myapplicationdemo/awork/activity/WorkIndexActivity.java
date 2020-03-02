package com.example.myapplicationdemo.awork.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplicationdemo.R;
import com.example.myapplicationdemo.awork.asynctask.ImageToBitmapTask;
import com.example.myapplicationdemo.awork.myview.MyViewPager;
import com.example.myapplicationdemo.awork.adapter.VpAdapter;
import com.example.myapplicationdemo.awork.bean.Article;
import com.example.myapplicationdemo.awork.net.UserBiz;
import com.example.myapplicationdemo.awork.utils.L;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

public class WorkIndexActivity extends AppCompatActivity {
    public static final String TAG = "WorkIndexActivity-X";
    public static final int CODE = 1000;
    private int page;//判断当前在第几页，方便后面操作
    UserBiz ub = new UserBiz();//初始化逻辑处理对象
    private MyViewPager disVp;
    private VpAdapter adapter;
    private List<Article> listData;//显示数据集合
    private ArrayList<Integer> keepList = new ArrayList<>();//收藏数的集合，保存索引

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workindex);
        if (listData == null) {
            Toast.makeText(WorkIndexActivity.this, "正在加载，耐心等待", Toast.LENGTH_SHORT).show();
        }
        initView();//初始化控件
        loadData(true);//获取网络：参数：用于判断是否从数据库取数据，第一次进入应用数据库没有数据，获取到的值为null，则从网络获取数据，解析，并将数据保存至数据库
    }

    private void initView() {
        disVp = findViewById(R.id.dis_vp);
        disVp.setCanSwipe(false);//设置不能左右滑动
        /**
         * 监听页面滑动时的index，获取当前页的index
         */
        disVp.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {//滑动，滚动页面时调用
            }

            @Override
            public void onPageSelected(int position) {//滑动后调用，返回出选在哪一页的index
                page = position;//将这个index返回出去，方便选页，收藏操作
            }

            @Override
            public void onPageScrollStateChanged(int state) {//滚动状态已更改
            }
        });

    }

    /**
     * 获取数据的方法
     *
     * @param isCache 判断是否从数据库读取，true为是
     */
    private void loadData(boolean isCache) {
        final List<Article> mDatas = new ArrayList<>();
        ub.loadData(WorkIndexActivity.this, new UserBiz.CallBack() {
            @Override
            public void onSccess(List<Article> articleList) {
                if (articleList != null) {
                    listData = articleList;
                    List<View> viewList = getViewList(listData);
                    if (viewList != null) {
                        adapter = new VpAdapter(WorkIndexActivity.this, viewList);
                        disVp.setAdapter(adapter);
                    }
                }
            }

            @Override
            public void onFail(Exception ex) {
                ex.printStackTrace();
                L.d(TAG, "Exception:" + ex.toString());
            }
        }, isCache);
    }

    public void indexOnClick(View v) {
        L.d(WorkIndexActivity.TAG, "page=" + page);//查看获取的页面index是否正确
        switch (v.getId()) {

            case R.id.last://上一篇，点击执行上一篇，page上滑一页，index减1
                if (page > 0) {
                    --page;//这里要在设置之前就得到上一页的index，所以用--page
                } else {//当到第一页时，点击直接跳到最后一页
                    page = 29;
                }
                disVp.setCurrentItem(page, false);//，显示到对应的页，参数2，false时，滑动动画去除
                break;
            case R.id.keep://收藏，
                if (listData != null) {
                    Article article = listData.get(page);//获取到这也页的数据对象，设置是否收藏
                    if (!article.isKeep()) {//判断是否已添加收藏
                        keepList.add(page + 1);//，将索引添加到收藏夹集合，为了后面方便查数据库数据，所以加1，因为数据库id是从1开始的
                        article.setKeep(true);
                        Toast.makeText(this, "收藏成功", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(this, "已经在收藏夹", Toast.LENGTH_SHORT).show();
                    }
                }else {
                    Toast.makeText(WorkIndexActivity.this, "正在加载，耐心等待", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.next://下一篇，点击执行下一篇，page下滑一页，index加1

                if (page < 30) {//小于页面总数的时候执行下一页操作
                    ++page;
                } else {//当到了最后一页的时候，点击下一页，直接跳转到第一页
                    page = 0;
                }
                disVp.setCurrentItem(page, false);//设置viewPager显示到对应的页，参数2，false时，滑动动画去除
                break;
            case R.id.my_keep://添加打开收藏夹内容，跳转界面时，要把收藏夹索引数据集合带过去
                Intent intent = new Intent(WorkIndexActivity.this, MyKeepActivity.class);
                intent.putIntegerArrayListExtra("keepList", keepList);//装载要传递的集合
                startActivity(intent);
                break;
        }
    }


    public List<View> getViewList(List<Article> articleList) {
        List<View> viewList = new ArrayList<>();
        L.d(TAG, "getViewList(:" + articleList);
        for (int i = 0; i < articleList.size(); i++) {
            final View view = LayoutInflater.from(this).inflate(R.layout.index_vp, null);
            TextView title = view.findViewById(R.id.article_title);
            TextView author = view.findViewById(R.id.article_author);
            TextView content = view.findViewById(R.id.article_content);
            int imgValues = new Random().nextInt(30) % 3;
            Article article = articleList.get(imgValues);
            title.setText(article.getTitle());
            author.setText(article.getAuthor());
            content.setText(article.getContent());
            //获取文章的图片
            ImageToBitmapTask.loadImgBitmap(articleList.get(i).getImg(), new ImageToBitmapTask.ImageBitmapTask.MyCallback() {
                @Override
                public void onSuccess(Bitmap bitmap) {
                    ImageView img = view.findViewById(R.id.article_img);
                    img.setImageBitmap(bitmap);
                }

                @Override
                public void onFail(Exception ex) {
                    ex.printStackTrace();
                }
            });

            viewList.add(view);
        }

        return viewList;
    }


}
