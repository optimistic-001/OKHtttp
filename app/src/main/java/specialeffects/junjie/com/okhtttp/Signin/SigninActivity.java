package specialeffects.junjie.com.okhtttp.Signin;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.trello.rxlifecycle.components.RxActivity;
import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.bean.SHARE_MEDIA;

import java.util.Map;
import java.util.Set;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import specialeffects.junjie.com.okhtttp.R;
import specialeffects.junjie.com.okhtttp.Sideslip.util.L;

/**
 * Created by JIE on 2017/2/10.
 * 三方登录
 */

public class SigninActivity extends RxActivity {
    private UMShareAPI mShareAPI;
    private Context mContext;
    @Bind(R.id.media)
    TextView media;
    @Bind(R.id.name)
    TextView name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);
        ButterKnife.bind(this);
        mContext = SigninActivity.this;
        media.setText("未知:");
        name.setText("未知");
        mShareAPI = UMShareAPI.get(this);
    }

    @OnClick(R.id.QQ_login)
    void signinQQ_login() {
        mShareAPI.getPlatformInfo(SigninActivity.this, SHARE_MEDIA.QQ, umAuthListener);
    }

    @OnClick(R.id.QQ_Cancel)
    void signinQQ_Cancel() {
        mShareAPI.deleteOauth(SigninActivity.this, SHARE_MEDIA.QQ, umAuthListener);
    }

//    @OnClick(R.id.WeChat_login)
//    void signinWeChat_login() {
//        mShareAPI = UMShareAPI.get(this);
//        mShareAPI.getPlatformInfo(SigninActivity.this, SHARE_MEDIA.QQ, umAuthListener);
//        Config.DEBUG = true;
//    }
//    @OnClick(R.id.WeChat_Cancel)
//    void signinWeChat_Cancel() {
//        mShareAPI.deleteOauth(SigninActivity.this, SHARE_MEDIA.QQ, umAuthListener);
//        Config.DEBUG = true;
//    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        mShareAPI.onActivityResult(requestCode, resultCode, data);
    }

    public UMAuthListener umAuthListener = new UMAuthListener() {
        @Override
        public void onStart(SHARE_MEDIA share_media) {

        }

        @Override
        public void onComplete(SHARE_MEDIA platform, int action, Map<String, String> data) {
            if (action == 2)
                ll(platform);
            else {
                media.setText("未知:");
                name.setText("未知");
            }
            Toast.makeText(getApplicationContext(), "Authorize succeed", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onError(SHARE_MEDIA platform, int action, Throwable t) {
            Toast.makeText(getApplicationContext(), "Authorize fail", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onCancel(SHARE_MEDIA platform, int action) {
            Toast.makeText(getApplicationContext(), "Authorize cancel", Toast.LENGTH_SHORT).show();
        }
    };

    //获取qq上的数据
    public void ll(SHARE_MEDIA platform) {
        mShareAPI.getPlatformInfo(this, platform, new UMAuthListener() {
            @Override
            public void onError(SHARE_MEDIA arg0, int arg1, Throwable arg2) {
                // TODO Auto-generated method stub
            }

            @Override
            public void onStart(SHARE_MEDIA share_media) {

            }

            @Override
            public void onComplete(SHARE_MEDIA arg0, int arg1, Map<String, String> arg2) {
                // TODO Auto-generated method stub
                //转换为set
                Set<String> keySet = arg2.keySet();
                //遍历循环，得到里面的key值----用户名，头像....
                for (String string : keySet) {
                    //打印下
                    L.e(string);
                }
                //得到key值得话，可以直接的到value
                String screen_name = arg2.get("screen_name");
                String url = arg2.get("profile_image_url");
                L.e(screen_name + ">>>>" + url);
                media.setText(arg0+":");
                name.setText(screen_name);
                /*ni1.setText(name);
                iit.displayImage(url,ni);*/
            }

            @Override
            public void onCancel(SHARE_MEDIA arg0, int arg1) {
                // TODO Auto-generated method stub
            }
        });
    }
}
