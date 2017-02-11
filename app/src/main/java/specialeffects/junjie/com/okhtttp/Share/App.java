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
//        PlatformConfig.setWeixin("wx967daebe835fbeac", "5bb696d9ccd75a38c8a0bfe0675559b3");
        PlatformConfig.setSinaWeibo("3033624042", "c9a2499883c88623b38203e4593e43d3");
        PlatformConfig.setQQZone("1105979962", "a6EHkLmTgkbwnEgg");
        Config.REDIRECT_URL = "http://sns.whalecloud.com/sina2/callback";
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
    }
}
