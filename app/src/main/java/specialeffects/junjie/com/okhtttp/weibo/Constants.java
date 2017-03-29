package specialeffects.junjie.com.okhtttp.weibo;

/**
 * Created by JIE on 2017/2/24.
 */

public class Constants {
    public static final String APP_KEY      = "3033624042";		   // 应用的APP_KEY
    public static final String REDIRECT_URL = "https://api.weibo.com/oauth2/default.html";// 应用的回调页
    public static final String SCOPE = 							   // 应用申请的高级权限
            "email,direct_messages_read,direct_messages_write,"
                    + "friendships_groups_read,friendships_groups_write,statuses_to_me_read,"
                    + "follow_app_official_microblog," + "invitation_write";
}
