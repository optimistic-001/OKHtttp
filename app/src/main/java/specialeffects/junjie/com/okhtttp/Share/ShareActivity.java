package specialeffects.junjie.com.okhtttp.Share;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.trello.rxlifecycle.components.RxActivity;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;

import butterknife.ButterKnife;
import butterknife.OnClick;
import specialeffects.junjie.com.okhtttp.R;
import specialeffects.junjie.com.okhtttp.Sideslip.util.L;
import specialeffects.junjie.com.okhtttp.weibo.WBAuthActivity;

/**
 * Created by JIE on 2017/2/10.
 * 友盟分享
 */

public class ShareActivity extends RxActivity {
    private UMImage image;
    private UMImage thumb;
    public static Context mContext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share);
        ButterKnife.bind(this);
        mContext= ShareActivity.this;
        image = new UMImage(this,R.drawable.umeng_socialize_wechat);
        thumb =  new UMImage(this, R.drawable.umeng_socialize_wechat);
        image.setThumb(thumb);
    }

    @OnClick(R.id.QQ)
    void shareQQ() {
        new ShareAction(ShareActivity.this)
                .setPlatform(SHARE_MEDIA.QQ)
                .withMedia(image)
                .setCallback(umShareListener)
                .share();
    }

    @OnClick(R.id.QQ_Zone)
    void shareQQ_Zone() {
        new ShareAction(ShareActivity.this)
                .setPlatform(SHARE_MEDIA.QZONE)
                .withText("hello")
                .setCallback(umShareListener)
                .share();
    }

    @OnClick(R.id.Sina_micro_blog)
    void shareSina_micro_blog() {
        Intent intent = new Intent(this, WBAuthActivity.class);
        this.startActivity(intent);
    }

    @OnClick(R.id.WeChat)
    void shareWeChat() {
        new ShareAction(ShareActivity.this)
                .setPlatform(SHARE_MEDIA.WEIXIN)
                .withMedia(image)
                .setCallback(umShareListener)
                .share();
    }

    @OnClick(R.id.WeChat_circle)
    void shareWeChat_circle() {
        new ShareAction(ShareActivity.this)
                .setPlatform(SHARE_MEDIA.WEIXIN_CIRCLE)
                .withMedia(image)
                .setCallback(umShareListener)
                .share();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

    public static UMShareListener umShareListener = new UMShareListener() {
        @Override
        public void onStart(SHARE_MEDIA share_media) {

        }

        @Override
        public void onResult(SHARE_MEDIA platform) {
            L.d("platform" + platform);
            Toast.makeText(mContext, platform + " 分享成功啦", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onError(SHARE_MEDIA platform, Throwable t) {
            Toast.makeText(mContext, platform + " 分享失败啦", Toast.LENGTH_SHORT).show();
            if (t != null) {
                L.d("throw:" + t.getMessage());
            }
        }

        @Override
        public void onCancel(SHARE_MEDIA platform) {
            Toast.makeText(mContext, platform + " 分享取消了", Toast.LENGTH_SHORT).show();
        }
    };
}
