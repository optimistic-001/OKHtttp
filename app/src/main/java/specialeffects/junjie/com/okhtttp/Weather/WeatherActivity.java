package specialeffects.junjie.com.okhtttp.Weather;

import android.app.Activity;
import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.RelativeLayout;

import com.readystatesoftware.systembartint.SystemBarTintManager;

import butterknife.Bind;
import butterknife.ButterKnife;
import specialeffects.junjie.com.okhtttp.R;

/**
 * 天气
 * Created by JIE on 2016/10/24.
 */

public class WeatherActivity  extends Activity{
    private SystemBarTintManager tintManager;
    @Bind(R.id.weather_layout1)
    RelativeLayout weather_layout1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.weather_activity_main);
        ButterKnife.bind(this);
        initData();
        initView();
    }

    private void initData() {
        //设置第一个布局的高度
        Resources resources = this.getResources();
        DisplayMetrics dm = resources.getDisplayMetrics();
        float density1 = dm.density;
        int width3 = dm.widthPixels;
        int height3 = dm.heightPixels;
        ViewGroup.LayoutParams params=weather_layout1.getLayoutParams();
        double h =height3/1.8;
        params.height =(int) h;
        weather_layout1.setLayoutParams(params);
    }

    private void initView() {
        //更改状态栏颜色
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            setTranslucentStatus(true);
        }
        tintManager = new SystemBarTintManager(this);
        tintManager.setStatusBarTintEnabled(true);
        tintManager.setStatusBarTintResource(R.color.colorPrimaryDark);
    }
    private void setTranslucentStatus(boolean on) {
        Window win = getWindow();
        WindowManager.LayoutParams winParams = win.getAttributes();
        final int bits = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
        if (on) {
            winParams.flags |= bits;
        } else {
            winParams.flags &= ~bits;
        }
        win.setAttributes(winParams);
    }
}
