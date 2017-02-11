package specialeffects.junjie.com.okhtttp.OKHTTPS;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Looper;

import com.squareup.okhttp.Callback;
import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by JIE on 2016/8/15.
 * 封装OKHttp网络请求（图片下载）
 */
public class OKHttpManager {
    private OkHttpClient client;
    private static OKHttpManager okHttpManager;
    private Handler mHandler;

    /**
     * 单例获取OKHttpManager实现
     *
     * @return
     */
    private static OKHttpManager getInstance() {
        if (okHttpManager == null) {
            okHttpManager = new OKHttpManager();
        }
        return okHttpManager;
    }

    private OKHttpManager() {
        client = new OkHttpClient();
        mHandler = new Handler(Looper.getMainLooper());
    }

    //***************************内部逻辑处理方法**************************************

    /**
     * 同步get请求
     *
     * @param url
     * @return
     * @throws IOException
     */
    private Response p_getSync(String url) throws IOException {
        Request request = new Request.Builder().url(url).build();
        Response response = client.newCall(request).execute();
        return response;
    }

    private InputStream p_getSyncAsString(String url) throws IOException {
        return p_getSync(url).body().byteStream();
    }

    /**
     * 异步get请求
     *
     * @param url
     * @param callBack
     */
    private void p_getAsync(String url, final DataCallBack callBack) {
        final Request request = new Request.Builder().url(url).build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {
                deliverDataFailure(request, e, callBack);
            }

            @Override
            public void onResponse(Response response) {
                try {
                    InputStream result = response.body().byteStream();
                    Bitmap bitmap = BitmapFactory.decodeStream(result);
                    deliverDataSuccess(bitmap, callBack);
                } catch (IOException e) {
                    deliverDataFailure(request, e, callBack);
                }

            }
        });
    }

    /**
     * post请求
     *
     * @param url
     * @param parms
     * @param callBack
     */
    private void p_postAsync(String url, Map<String, String> parms, final DataCallBack callBack) {
        RequestBody requestBody = null;
        if (parms == null) {
            parms = new HashMap<String, String>();
        }
        FormEncodingBuilder builder = new FormEncodingBuilder();
        for (Map.Entry<String, String> entry : parms.entrySet()) {
            String key = entry.getKey().toString();
            String value = null;
            if (entry.getValue() == null) {
                value = "";
            } else {
                value = entry.getValue().toString();
            }
            builder.add(key, value);
        }
        requestBody = builder.build();
        final Request request = new Request.Builder().url(url).post(requestBody).build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {
                deliverDataFailure(request, e, callBack);
            }

            @Override
            public void onResponse(Response response) throws IOException {
                try {
                    InputStream result = response.body().byteStream();
                    Bitmap bitmap = BitmapFactory.decodeStream(result);
                    deliverDataSuccess(bitmap, callBack);
                } catch (IOException e) {
                    deliverDataFailure(request, e, callBack);
                }
            }
        });
    }

    private void p_downloadAsync(final String url, final String destDir, final DataCallBack callBack) {
        final Request request = new Request.Builder().url(url).build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {

            }

            @Override
            public void onResponse(Response response) {
                InputStream inputStream = null;
                FileOutputStream fos = null;
                try {
                    File file = new File(destDir, getFileName(url));
                    fos = new FileOutputStream(file);
                    inputStream = response.body().byteStream();
                    byte[] buffer = new byte[1024];
                    int len = 0;
                    while ((len = inputStream.read(buffer)) != -1) {
                        fos.write(buffer, 0, len);
                    }
                    fos.flush();
                    deliverDataSuccess(file.getAbsolutePath(), callBack);
                } catch (IOException e) {
                    deliverDataFailure(request, e, callBack);
                } finally {
                    try {
                        if (fos != null) {
                            fos.close();
                        }
                        if (inputStream != null) {
                            inputStream.close();
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }


    //***************************数据分发的方法**************************************
    private void deliverDataFailure(final Request request, final IOException e, final DataCallBack callBack) {
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                if (callBack != null) {
                    callBack.requestFailure(request, e);
                }
            }
        });
    }

    private void deliverDataSuccess(final Bitmap result, final DataCallBack callBack) {
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                if (callBack != null) {
                    callBack.requestSuccess(result);
                }
            }
        });
    }
    private void deliverDataSuccess(final String result, final DataCallBack callBack) {
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                if (callBack != null) {
                    callBack.requestSuccess(result);
                }
            }
        });
    }

    //***************************对外公布的方法**************************************

    /**
     * 同步get请求
     *
     * @param url
     * @return
     * @throws IOException
     */
    public static Response getSync(String url) throws IOException {
        return getInstance().p_getSync(url);
    }

    public static Bitmap getSyncAs(String url) throws IOException {
        final Bitmap bitmap = BitmapFactory.decodeStream(getInstance().p_getSyncAsString(url));
        return bitmap;
    }

    /**
     * 异步get请求
     *
     * @param url
     * @param callBack
     */
    public static void getAsync(String url, DataCallBack callBack) {
        getInstance().p_getAsync(url, callBack);
    }

    /**
     * post请求
     *
     * @param url
     * @param parms
     * @param callBack
     */
    public static void postAsync(String url, Map<String, String> parms, DataCallBack callBack) {
        getInstance().p_postAsync(url, parms, callBack);
    }

    /**
     * 文件下载
     *
     * @param url
     * @param destDir
     * @param callBack
     */
    public static void downloadAsync(final String url, final String destDir, final DataCallBack callBack) {
        getInstance().p_downloadAsync(url, destDir, callBack);
    }


    //***************************数据回调接口**************************************
    public interface DataCallBack {
        void requestFailure(Request request, IOException e);

        void requestSuccess(Bitmap result);

        void requestSuccess(String result);
    }

    /**
     * 根据网址去最后一个“/”后面的数据
     *
     * @param pUil
     * @return
     */
    private String getFileName(String pUil) {
        int separtorIndex = pUil.lastIndexOf("/");
        String path = (separtorIndex < 0) ? pUil : pUil.substring(separtorIndex + 1, pUil.length());
        return path;
    }

}
