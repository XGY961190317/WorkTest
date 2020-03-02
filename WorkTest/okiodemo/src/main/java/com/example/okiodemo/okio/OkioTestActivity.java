package com.example.okiodemo.okio;

import android.os.Bundle;
import android.util.Log;

import com.example.okiodemo.R;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import okio.Buffer;
import okio.BufferedSource;
import okio.ByteString;
import okio.Okio;

public class OkioTestActivity extends AppCompatActivity {
    public static final String TAG = "OkioTestActivity-X";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_okiotest);
        byteStringTest();
        imgTest();
        bufferTest();
        bufferStreamTest();
        bufferSoureAndSinkTest();
    }

    /**
     * ByteString测试
     */
    private void byteStringTest() {
        String str = "This is a string";
        Log.d(TAG, str.length() + "");
        //创建ByteString对象：
        ByteString byteString = ByteString.encodeUtf8("abc");
        Log.d(TAG, "byteString=" + byteString);//查看这个对象
        //数据类型的转换
        String base64 = byteString.base64();//获取这个字符串的base64 值
        Log.d(TAG, "base64=" + base64);
        ByteString MD5 = byteString.md5();//获取其MD5值
        Log.d(TAG, "MD5=" + MD5.hex());
        String sha1 = byteString.sha1().hex();
        Log.d(TAG, "sha1=" + sha1);
        //解码，将base64数据转为字符
        //通过base64编码获取String
        ByteString base64ToStr = ByteString.decodeBase64("YWJj");
        Log.d(TAG, "base64ToStr=" + base64ToStr);
        //imgTest();
        return;
    }

    /**
     * ByteString直接对文件处理，直接处理数据流的数据
     *
     * @throws IOException
     */
    private void imgTest() {
        //直接处理文件这里异常要直接抛出
        try {
            FileInputStream is = new FileInputStream("in.png");
            ByteString readStr = ByteString.read(is, is.available());//读取流文件输入流的数据
            Log.d(TAG, "readStr=" + readStr);
            FileOutputStream out = new FileOutputStream("out.png");
            readStr.write(out);//直接将数据写入到文件输出流
            out.close();
            is.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /**
     * buffer测试
     */
    private void bufferTest() {
        Buffer buffer = new Buffer();//创建buffer，在内存中新建
        Log.d(TAG, "buffer=" + buffer);
        buffer.writeUtf8("abcd");//buffer写的操作  往内存里面写数据
        //读取操作
        while (buffer.exhausted()) {
            try {
                Log.d(TAG, "buffer read=" + buffer.readUtf8(1));//读取数据
            } catch (EOFException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * buffer直接对数据流进行读取和写入
     */
    private void bufferStreamTest() {

        try {
            Buffer buffer = new Buffer();
            buffer.readFrom(new FileInputStream("in.png"));//直接从文件输入流读取数据
            buffer.writeTo(new FileOutputStream("out2.png")); //直接将数据写入到文件输出流
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Soure Sink 操作
     */
    private void bufferSoureAndSinkTest() {
        try {
            BufferedSource source = Okio.buffer(Okio.source(new FileInputStream("in.txt")));//通过Okio.buffer获取Source，参数，需要传入流
            Okio.buffer(Okio.sink(new FileOutputStream("out.txt")));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
