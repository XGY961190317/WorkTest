package com.example.storagedemo.internalstorage;

import android.Manifest;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.storagedemo.R;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

public class InternalActivity extends AppCompatActivity {

    private Button saveBtn;
    private Button readBtn;
    private EditText editText;
    private TextView disText;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_internal);
        //请求权限：

        saveBtn = findViewById(R.id.internal_save_btn);
        readBtn = findViewById(R.id.internal_read_btn);
        editText = findViewById(R.id.internal_input_text);
        disText = findViewById(R.id.internal_dis_text);
        //从输入框写入：
        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    File file = new File(getFilesDir(), "getFilesDir.text");
                    if (!file.exists()) {
                        file.createNewFile();
                    }
                    FileOutputStream outputStream = new FileOutputStream(file);
                    String intString  = editText.getText().toString();
                    outputStream.write(intString.getBytes());
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        //从手机内部储存文件读取
        readBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                File file = new File(getFilesDir(),"getFilesDir.text");
                try {
                    FileInputStream inputStream = new FileInputStream(file);
                    byte[] bytes = new byte[1024];
                    int len = inputStream.read(bytes);
                    String strText = new String(bytes,0,len);
                    disText.setText(strText);
                    inputStream.close();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
