package specialeffects.junjie.com.okhtttp.weibo;

import android.app.Activity;
import android.content.Context;

import com.sina.weibo.sdk.api.TextObject;
import com.sina.weibo.sdk.api.WeiboMultiMessage;
import com.sina.weibo.sdk.api.share.IWeiboShareAPI;
import com.sina.weibo.sdk.api.share.SendMultiMessageToWeiboRequest;
import com.sina.weibo.sdk.api.share.WeiboShareSDK;

/**
 * Created by JIE on 2017/2/24.
 */

public class WBText {

    /***********************分享参数*************************/
    /** 微博微博分享接口实例 */
    public static IWeiboShareAPI mWeiboShareAPI = null;
    public static TextObject textObject;

    public static void wBCreate(Context mContext){
        mWeiboShareAPI = WeiboShareSDK.createWeiboAPI(mContext, Constants.APP_KEY);
        mWeiboShareAPI.registerApp();	// 将应用注册到微博客户端
    }
    public static TextObject getTextObj(String text) {
        textObject = new TextObject();
        textObject.text = text;
        return textObject;
    }
    public static void sendMultiMessage(Activity mContext, boolean hasText) {
        // 1. 初始化微博的分享消息
        WeiboMultiMessage weiboMessage = new WeiboMultiMessage();
        if (hasText) {
            weiboMessage.textObject = textObject;
        }

        SendMultiMessageToWeiboRequest request = new SendMultiMessageToWeiboRequest();
        request.transaction = String.valueOf(System.currentTimeMillis());
        request.multiMessage = weiboMessage;
        mWeiboShareAPI.sendRequest(mContext,request); //发送请求消息到微博，唤起微博分享界面
    }
}
