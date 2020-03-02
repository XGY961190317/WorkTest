package com.ontob.socketdemo.https;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;

public class HttpUtlis {
    public interface HttpListener {
        void onSuccess(String content);

        void onFail(Exception ex);
    }

    static Handler mHandler = new Handler(Looper.getMainLooper());


    public static void doGet(final String urlString, final HttpListener httpListener) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                InputStream mInputStream = null;
                InputStreamReader isr = null;
                try {
                    URL url = new URL(urlString);
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    conn.setConnectTimeout(3 * 1000);
                    conn.setReadTimeout(3 * 1000);
                    conn.setRequestMethod("GET");
                    int requestCode = conn.getResponseCode();
                    if (requestCode == HttpURLConnection.HTTP_OK) {
                        mInputStream = conn.getInputStream();
                        isr = new InputStreamReader(mInputStream);//通过字节来读，避免出现中文乱码
                        int len;
                        char[] buf = new char[1024];
                        final StringBuilder sb = new StringBuilder();
                        while ((len = isr.read(buf)) != -1) {
                            sb.append(new String(buf, 0, len));
                        }
                        Log.d(HttpActivity.TAG, "resultString:" + sb.toString());
                        mHandler.post(new Runnable() {
                            @Override
                            public void run() {
                                String resultString = sb.toString();
                                if (resultString != null) {
                                    httpListener.onSuccess(resultString);
                                }
                            }
                        });
                    }

                } catch (MalformedURLException e) {
                    e.printStackTrace();
                    httpListener.onFail(e);
                } catch (IOException e) {
                    e.printStackTrace();
                    httpListener.onFail(e);
                } finally {
                    if (mInputStream != null) {
                        try {
                            mInputStream.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    if (isr != null) {
                        try {
                            isr.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }).start();
    }
    public static void doGetTwo(final Context context, final String urlString, final HttpListener httpListener) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                InputStream mInputStream = null;
                InputStreamReader isr = null;
                try {
                    URL url = new URL(urlString);

                    HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
                    conn.setConnectTimeout(3 * 1000);
                    conn.setReadTimeout(3 * 1000);
                    conn.setRequestMethod("GET");



                    SSLContext sslContext = SSLContext.getInstance("TLS");
                    //证书校验：方法1
                    X509Certificate serverCert = getCert(context);
                    TrustManager[]tm = {new MyX509TrustManager(serverCert)};

                    //方法2：获取证书
                    /**
                    X509Certificate serverCert = getCert(context);
                    String keyStoreType = KeyStore.getDefaultType();
                    KeyStore keyStore = KeyStore.getInstance(keyStoreType);
                    keyStore.load(null);
                    keyStore.setCertificateEntry("srca",serverCert);

                    TrustManagerFactory trustManagerFactory =
                            TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
                    trustManagerFactory.init(keyStore);
                    TrustManager[] trustManagers = trustManagerFactory.getTrustManagers();**/

                    sslContext.init(null,tm,new SecureRandom());
                    conn.setSSLSocketFactory(sslContext.getSocketFactory());
                    //域名校验：
                    conn.setHostnameVerifier(new HostnameVerifier() {
                        @Override
                        public boolean verify(String hostname, SSLSession session) {
                            HostnameVerifier defaultHostnameVerifier
                                    = HttpsURLConnection.getDefaultHostnameVerifier();
                            return defaultHostnameVerifier.verify("kyfw.12306.cn", session);
                        }
                    });

                    int requestCode = conn.getResponseCode();
                    if (requestCode == HttpURLConnection.HTTP_OK) {
                        mInputStream = conn.getInputStream();
                        isr = new InputStreamReader(mInputStream);//通过字节来读，避免出现中文乱码
                        int len;
                        char[] buf = new char[1024];
                        final StringBuilder sb = new StringBuilder();
                        while ((len = isr.read(buf)) != -1) {
                            sb.append(new String(buf, 0, len));
                        }
                        Log.d(HttpActivity.TAG, "resultString:" + sb.toString());
                        mHandler.post(new Runnable() {
                            @Override
                            public void run() {
                                String resultString = sb.toString();
                                if (resultString != null) {
                                    httpListener.onSuccess(resultString);
                                }
                            }
                        });
                    }

                } catch (MalformedURLException e) {
                    e.printStackTrace();
                    httpListener.onFail(e);
                } catch (IOException e) {
                    e.printStackTrace();
                    httpListener.onFail(e);
                } catch (NoSuchAlgorithmException e) {
                    e.printStackTrace();
                } catch (KeyManagementException e) {
                    e.printStackTrace();
                } finally {
                    if (mInputStream != null) {
                        try {
                            mInputStream.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    if (isr != null) {
                        try {
                            isr.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }).start();
    }

    private static X509Certificate getCert(Context context) {
        try {
            //获取证书文件
            InputStream is = context.getAssets().open("srca.cer");
            CertificateFactory certificateFactory = CertificateFactory.getInstance("X.509");
           return  (X509Certificate) certificateFactory.generateCertificate(is);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (CertificateException e) {
            e.printStackTrace();
        }
        return null;
    }
}


