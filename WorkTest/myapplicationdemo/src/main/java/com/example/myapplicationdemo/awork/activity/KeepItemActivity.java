package com.example.myapplicationdemo.awork.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.myapplicationdemo.R;
import com.example.myapplicationdemo.awork.asynctask.ImageToBitmapTask;
import com.example.myapplicationdemo.awork.bean.Article;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class KeepItemActivity extends AppCompatActivity {
    private TextView itemTitle;
    private TextView itemAuthor;
    private TextView itemContent;
    private ImageView itemImg;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_keepitem);
        Intent intent = getIntent();
        Article article = (Article) intent.getSerializableExtra("articleContent");//接受传过来的对象信息
        inteView();
        setView(article);
    }

    private void inteView() {
        itemTitle = findViewById(R.id.keeplv_item_title);
        itemAuthor = findViewById(R.id.keeplv_item_author);
        itemContent = findViewById(R.id.keeplv_item_content);
        itemImg = findViewById(R.id.keeplv_item_img);
    }

    private void setView(Article article) {
        if (article != null) {
            itemTitle.setText(article.getTitle());
            itemAuthor.setText(article.getAuthor());
            itemContent.setText(article.getContent());
            ImageToBitmapTask.loadImgBitmap(article.getImg(), new ImageToBitmapTask.ImageBitmapTask.MyCallback() {
                @Override
                public void onSuccess(Bitmap bitmap) {

                    itemImg.setImageBitmap(bitmap);
                }

                @Override
                public void onFail(Exception ex) {

                }
            });
        }

    }
}
