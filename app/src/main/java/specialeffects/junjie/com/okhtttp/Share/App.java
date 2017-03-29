package specialeffects.junjie.com.okhtttp.Share;

import android.app.Application;

import com.umeng.message.IUmengRegisterCallback;
import com.umeng.message.PushAgent;
import com.umeng.socialize.Config;
import com.umeng.socialize.PlatformConfig;
import com.umeng.socialize.UMShareAPI;

import specialeffects.junjie.com.okhtttp.Sideslip.util.L;

/**
 * Created by JIE on 2017/2/10.
 */

public class App extends Application {
    {
        PlatformConfig.setWeixin("wxba8b00ce84191c93", "40d2728fbb4a3a749ce166a1af914830");
        PlatformConfig.setSinaWeibo("3033624042", "c9a2499883c88623b38203e4593e43d3","https://api.weibo.com/oauth2/default.html");
        PlatformConfig.setQQZone("1105979962", "a6EHkLmTgkbwnEgg");
    }

    @Override
    public void onCreate() {
        super.onCreate();
        PushAgent mPushAgent = PushAgent.getInstance(this);
        //注册推送服务，每次调用register方法都会回调该接口
        mPushAgent.register(new IUmengRegisterCallback() {
            @Override
            public void onSuccess(String deviceToken) {
                //注册成功会返回device token
                L.e(deviceToken);
            }

            @Override
            public void onFailure(String s, String s1) {
                L.e(s+s1);

            }
        });
        UMShareAPI.get(this);
        Config.DEBUG = true;
    }
}
