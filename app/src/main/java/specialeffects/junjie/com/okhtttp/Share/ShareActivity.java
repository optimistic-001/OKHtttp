package specialeffects.junjie.com.okhtttp.Share;

import android.os.Bundle;
import android.widget.Toast;

import com.trello.rxlifecycle.components.RxActivity;
import com.umeng.socialize.Config;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;

import butterknife.ButterKnife;
import butterknife.OnClick;
import specialeffects.junjie.com.okhtttp.R;
import specialeffects.junjie.com.okhtttp.Sideslip.util.L;

/**
 * Created by JIE on 2017/2/10.
 * 友盟分享
 */

public class ShareActivity extends RxActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.QQ)
    void shareQQ() {
        Toast.makeText(this, "QQ", Toast.LENGTH_LONG).show();
        new ShareAction(ShareActivity.this)
                .setPlatform(SHARE_MEDIA.QQ)
                .withText("hello")
                .setCallback(umShareListener)
                .share();
        Config.DEBUG = true;
    }

    @OnClick(R.id.QQ_Zone)
    void shareQQ_Zone() {
        new ShareAction(ShareActivity.this)
                .setPlatform(SHARE_MEDIA.QZONE)
                .withText("hello")
                .setCallback(umShareListener)
                .share();
        Config.DEBUG = true;
    }

    @OnClick(R.id.Sina_micro_blog)
    void shareSina_micro_blog() {
        new ShareAction(ShareActivity.this)
                .withText("hello")
//                .setPlatform(SHARE_MEDIA.SINA)
                .setDisplayList(SHARE_MEDIA.SINA, SHARE_MEDIA.QQ, SHARE_MEDIA.WEIXIN)
                .setCallback(umShareListener)
                .open();
//        Config.DEBUG = true;
    }

    @OnClick(R.id.WeChat)
    void shareWeChat() {
    }

    @OnClick(R.id.WeChat_circle)
    void shareWeChat_circle() {
    }

    private UMShareListener umShareListener = new UMShareListener() {
        @Override
        public void onResult(SHARE_MEDIA platform) {
            L.d("platform" + platform);
            Toast.makeText(ShareActivity.this, platform + " 分享成功啦", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onError(SHARE_MEDIA platform, Throwable t) {
            Toast.makeText(ShareActivity.this, platform + " 分享失败啦", Toast.LENGTH_SHORT).show();
            if (t != null) {
                L.d("throw:" + t.getMessage());
            }
        }

        @Override
        public void onCancel(SHARE_MEDIA platform) {
            Toast.makeText(ShareActivity.this, platform + " 分享取消了", Toast.LENGTH_SHORT).show();
        }
    };
}
