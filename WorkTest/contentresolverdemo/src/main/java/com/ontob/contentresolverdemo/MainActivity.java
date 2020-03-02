package com.ontob.contentresolverdemo;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.TargetApi;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.CursorAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {
    ContentResolver resolver;
    String genderText = "男";
    private ListView listView;
    private EditText etNam;
    private EditText etAge;
    private RadioGroup rg;
    private EditText etId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        resolver = getContentResolver();

    }

    private void initView() {
        listView = findViewById(R.id.base_lv_dis);
        etNam = findViewById(R.id.name_et);
        etId = findViewById(R.id._id_et);
        etAge = findViewById(R.id.age_et);
        rg = findViewById(R.id.gender_rg);

        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.man_rb:
                        genderText = "男";
                        break;
                    case R.id.gender_rb:
                        genderText = "女";
                        break;
                }
            }
        });
    }


    public void bastBtnOnclick(View v) {
        String name = etNam.getText().toString();
        String age = etAge.getText().toString();
        String eId = etId.getText().toString();
        Uri uri = Uri.parse("content://com.ontob.contentprovider.MyContentProvider");
        switch (v.getId()) {
            case R.id.insert_btn://插入
                ContentValues values = new ContentValues();
                values.put("name", name);
                values.put("age", age);
                values.put("gender", genderText);
                Uri uri1 = resolver.insert(uri, values);
                long id = ContentUris.parseId(uri1);
                Toast.makeText(this, "添加成功，新学生学号是" + id, Toast.LENGTH_SHORT).show();
                break;
            case R.id.select_btn://为空代表查询所有列
                Cursor c = resolver.query(uri, null, null, null, null);
                /**
                 * 参数1：上下文
                 * 参数2：item布局
                 * 参数3：数据源
                 * 参数4：查询结果中的列名
                 * 参数5：数据未来所要加载到对应控件的id数据
                 * 参数6：刷新数据库的参数
                 */
                SimpleCursorAdapter adapter = new SimpleCursorAdapter(this, R.layout.lv_item, c, new String[]{"_id", "name", "age", "gender"}, new int[]{R.id._id, R.id.name, R.id.age, R.id.gender}, CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);
                listView.setAdapter(adapter);
                break;
            case R.id.delete_btn:
                int resultId = resolver.delete(uri, "_id=?", new String[]{eId});
                if (resultId > 0) {
                    Toast.makeText(this, "删除成功，删除的学员学号是" + resultId, Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.update_btn:
                ContentValues values1 = new ContentValues();
                values1.put("name", name);
                values1.put("age", age);
                values1.put("gender", genderText);
                int resultIdu = resolver.update(uri, values1, "_id=?", new String[]{eId});
                if (resultIdu > 0) {
                    Toast.makeText(this, "修改成功，删除的学员学号是", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.urimatcher_btn:
                String uri2 = "content://com.ontob.contentprovider.MyContentProvider/helloword";
                resolver.delete(Uri.parse("content://com.ontob.contentprovider.MyContentProvider/helloword"), null, null);
                resolver.delete(Uri.parse("content://com.ontob.contentprovider.MyContentProvider/helloword/ABC"), null, null);
                resolver.delete(Uri.parse("content://com.ontob.contentprovider.MyContentProvider/helloword/123"), null, null);
                resolver.delete(Uri.parse("content://com.ontob.contentprovider.MyContentProvider/helloword/325422"), null, null);
                resolver.delete(Uri.parse("content://com.ontob.contentprovider.MyContentProvider/helloword/SDF345"), null, null);
                break;
            case R.id.uri_btn:
                resolver.insert(Uri.parse("content://com.ontob.contentprovider.MyContentProvider/whatever?name=张三&age=23&gender=男"), new ContentValues());
                break;
        }
    }
}
