package com.ontob.cntentproviderdemo.contentprovider;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;
import android.util.Log;

public class MyContentProvider extends ContentProvider {
    /**
     * URI解析：
     * 1.UrlMatcher:在ContentProvider创建的时候，制定好匹配规则，当调用了ContentProvider中的操作时，利用匹配类去匹配传的Uri,根据不同的uri给出不同的结果
     * 2.Uri自带解析方法
     */
    public static final int VISION = 1;
    public static final String STU_INFO = "stu_info";
    private SQLiteDatabase db;
    private UriMatcher matcher;

    public MyContentProvider() {
    }

    /**
     * 新建数据库的操作，在第一次进来的时候就要完成，所以在这里做，这个方法是第一次启动应用的时候创建contentprovider就会调用
     *
     * @return
     */
    @Override
    public boolean onCreate() {
        // TODO: Implement this to initialize your content provider on startup.
        //SQLiteOpenHelper
        SQLiteOpenHelper helper = new SQLiteOpenHelper(getContext(), STU_INFO + ".db", null, VISION) {
            @Override
            public void onCreate(SQLiteDatabase db) {
                String sql = "create table " + STU_INFO + "(_id integer primary key autoincrement," +
                        "name varchar(20) not null," +
                        "age integer not null," +
                        "gender varchar(2))";
                db.execSQL(sql);
            }

            @Override
            public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

            }
        };
        db = helper.getReadableDatabase();
        /**
         * 参数：代表无法匹配
         */
        matcher = new UriMatcher(UriMatcher.NO_MATCH);
        matcher.addURI("com.ontob.contentprovider.MyContentProvider", "helloword", 1000);//添加uri规则
        matcher.addURI("com.ontob.contentprovider.MyContentProvider", "helloword/ABC", 1001);//匹配字符abc路径
        matcher.addURI("com.ontob.contentprovider.MyContentProvider", "helloword/123", 1002);//匹配数字123
        matcher.addURI("com.ontob.contentprovider.MyContentProvider", "helloword/#", 1003);//所有的数字
        matcher.addURI("com.ontob.contentprovider.MyContentProvider", "helloword/*", 1004);//所有的字符
        return true;
    }

    @Override
    public String getType(Uri uri) {
        // TODO: Implement this to handle requests for the MIME type of the data
        // at the given URI.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        // Implement this to handle requests to delete one or more rows.
        int id = 0;
        /**
         * 解析Uri并作出操作
         */
        int code = matcher.match(uri);//解析uri的方法
        switch (code) {
            case 1000:
                Log.d("TAG", "code = " + code + "匹配到的路径是hollowword");
                break;
            case 1001:
                Log.d("TAG", "code = " + code + "匹配到的路径是hollowword/ABC");
                break;
            case 1002:
                Log.d("TAG", "code = " + code + "匹配到的路径是helloword/123");
                break;
            case 1003:
                Log.d("TAG", "code = " + code + "匹配到的路径是helloword/#  任意数字");
                break;
            case 1004:
                Log.d("TAG", "code = " + code + "匹配到的路径是helloword/*  任意字符");
                break;

            default:
                id = db.delete(STU_INFO, selection, selectionArgs);
        }
        return id;
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        // TODO: Implement this to handle requests to insert a new row.
        Log.d("TAG", values.getAsString("name") + "   uri=" + uri);
        //参数2，非空列
        long id = 0;
        if (values.size() > 0) {
            id = db.insert(STU_INFO, null, values);
            if (id != 0) {
                Log.d("TAG", "添加成功");
            }
        } else {
            String authority = uri.getAuthority();
            String path = uri.getPath();
            String query = uri.getQuery();
            String name = uri.getQueryParameter("name");
            String age = uri.getQueryParameter("age");
            String gender = uri.getQueryParameter("gender");
            Log.d("TAG","主机名："+authority+",路径："+path+",查询数据："+query+",姓名："+name+",年龄："+age+",性别："+gender);
        }
        return ContentUris.withAppendedId(uri, id);
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder) {
        /**
         * 参数1：表名
         * 参数2：要查询的列，
         * 参数3：条件
         * 参数4：条件值
         * 参数5：分组
         * 参数6：分组条件，
         * 参数7,：排序
         */
        Cursor c = db.query(STU_INFO, projection, selection, selectionArgs, null, null, sortOrder);
        return c;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection,
                      String[] selectionArgs) {
        // TODO: Implement this to handle requests to update one or more rows.
        int result = db.update(STU_INFO, values, selection, selectionArgs);
        return result;
    }


}
