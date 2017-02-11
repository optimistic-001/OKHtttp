package specialeffects.junjie.com.okhtttp.OKHTTPS;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.okhttp.Callback;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import specialeffects.junjie.com.okhtttp.R;
import specialeffects.junjie.com.okhtttp.Sideslip.util.L;

/**
 * OKHttp
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private String LOG = "xiejunjie";
    private Button sync_get, asynchronous_get, post, filedownload,get_String;
    private ImageView img_okhttp;
    private static OkHttpClient client = new OkHttpClient();
    private Request request;
    private Response response;

//    private final String url_img = "http://img.my.csdn.net/uploads/201407/26/1406383291_6518.jpg";
    private final String url_img = "http://tnfs.tngou.net/image/info/161010/a715d3a58168e3feb251de132a1483c2.jpg_80x80";
    private final String url_img_get = "http://img.my.csdn.net/uploads/201407/26/1406383291_8239.jpg";
    private final String url_img_post = "http://image.tianjimedia.com/uploadImages/2016/247/29/NX94C7K04004.jpg";
    private String httpUrl = "http://apis.baidu.com/txapi/mvtp/meinv";
    private String httpArg = "num=10";
    private TextView txt_get;


    BufferedReader reader = null;
    String result = null;
    StringBuffer sbf = new StringBuffer();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    private void init() {
        sync_get = (Button) findViewById(R.id.sync_get);
        asynchronous_get = (Button) findViewById(R.id.asynchronous_get);
        post = (Button) findViewById(R.id.post);
        filedownload = (Button) findViewById(R.id.filedownload);
        get_String = (Button) findViewById(R.id.get_String);
        img_okhttp = (ImageView) findViewById(R.id.img_okhttp);
        txt_get = (TextView) findViewById(R.id.txt_get);
        sync_get.setOnClickListener(this);
        asynchronous_get.setOnClickListener(this);
        post.setOnClickListener(this);
        filedownload.setOnClickListener(this);
        get_String.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.sync_get:
                Log.d(LOG, "发送同步get请求.........");
                imgVisibility(true);
//                syncget2();
                syncget1();
                break;
            case R.id.asynchronous_get:
                Log.d(LOG, "发送异步get请求.........");
                imgVisibility(true);
                asynchronousget2();
                break;
            case R.id.post:
                Log.d(LOG, "发送post提交表单请求.........");
                imgVisibility(true);
                postAsync();
                break;
            case R.id.filedownload:
                Log.d(LOG, "发送文件下载请求.........");
                filedownload.setText("清空");
                txt_get.setText("");
                break;
            case R.id.get_String:
                Log.d(LOG, "文字同步get（有头文件）........");
                imgVisibility(false);
                get_String(httpUrl,httpArg);
                break;
        }
    }
    private void imgVisibility(boolean b){
        if (b){
            img_okhttp.setVisibility(View.VISIBLE);
            txt_get.setVisibility(View.GONE);
        }else {
            img_okhttp.setVisibility(View.GONE);
            txt_get.setVisibility(View.VISIBLE);
        }
    }

    /**
     * 原始同步get
     */
    private void syncget1() {
        request = new Request.Builder().url(url_img).build();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    URL e = new URL(url_img);
                    HttpURLConnection urlConnection = (HttpURLConnection)e.openConnection();
                    InputStream img = urlConnection.getInputStream();
                    final Bitmap bitmap = BitmapFactory.decodeStream(img);
//                    response = client.newCall(request).execute();
//                    InputStream img = response.body().byteStream();
//                    final Bitmap bitmap = BitmapFactory.decodeStream(img);
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            img_okhttp.setImageBitmap(bitmap);
                        }
                    });
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }



    /**
     * 文字同步get（有头文件）
     * @param httpUrl
     * @param httpArg
     */
    private void get_String(String httpUrl,String httpArg) {
        final String url =httpUrl + "?" + httpArg;
        request = new Request.Builder().url(url).header("apikey","1da37c45c04b183b5dd601822e010d4d").build();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    response = client.newCall(request).execute();
                    InputStream is = response.body().byteStream();
                    reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
                    String strRead = null;
                    while ((strRead = reader.readLine()) != null) {
                        sbf.append(strRead);
                    }
                    reader.close();
                    result = sbf.toString();
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            txt_get.setText(result);
                        }
                    });
                } catch (IOException e) {
                    e.printStackTrace();
                    L.i(e.toString());
                }
            }
        }).start();
    }

    /**
     * 封装同步get
     */
    private void syncget2() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    final Bitmap bitmap = OKHttpManager.getSyncAs(url_img);
                    MainActivity.this.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            img_okhttp.setImageBitmap(bitmap);
                        }
                    });
                } catch (IOException e) {
                    e.printStackTrace();
                    Log.i(LOG, "run: "+e);
                }
            }
        }).start();
    }

    /**
     * 原始异步get
     */
    private void asynchronousget1(){
        request = new Request.Builder().url(url_img_get).build();
        Log.d(LOG, "发送异步get请求....2222....." + request);
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {
                Log.i(LOG, "异步get异常: "+e);
            }

            /**
             * 成功
             * @param response
             * @throws IOException
             */
            @Override
            public void onResponse(Response response) throws IOException {
                InputStream img = response.body().byteStream();
                final Bitmap bitmap = BitmapFactory.decodeStream(img);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        img_okhttp.setImageBitmap(bitmap);
                    }
                });
            }
        });
    }

    /**
     * 封装异步get
     */
    private void asynchronousget2(){
        OKHttpManager.getAsync(url_img_post, new OKHttpManager.DataCallBack() {
            @Override
            public void requestFailure(Request request, IOException e) {
                Log.i(LOG, "异步get异常: "+e);
            }
            @Override
            public void requestSuccess(Bitmap result) {
                img_okhttp.setImageBitmap(result);
            }

            @Override
            public void requestSuccess(String result) {

            }
        });
    }

    /**
     * 封装post
     */
    private void postAsync(){
        Map<String,String> parms = new HashMap<String, String>();
//        parms.put("key","value");
        OKHttpManager.postAsync(url_img_get, parms, new OKHttpManager.DataCallBack() {
            @Override
            public void requestFailure(Request request, IOException e) {
                Log.i(LOG, "post异常 "+e);
            }

            @Override
            public void requestSuccess(Bitmap result) {
                img_okhttp.setImageBitmap(result);
            }

            @Override
            public void requestSuccess(String result) {

            }
        });
    }

    private void filedownload(){
//        OKHttpManager.downloadAsync();
    }
}
