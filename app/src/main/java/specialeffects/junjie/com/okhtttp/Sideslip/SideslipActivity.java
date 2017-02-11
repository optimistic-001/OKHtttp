package specialeffects.junjie.com.okhtttp.Sideslip;

import android.os.Bundle;
import android.view.View;
import android.view.Window;

import specialeffects.junjie.com.okhtttp.BasActivity;
import specialeffects.junjie.com.okhtttp.R;

/**
 * Created by JIE on 2016/9/6.
 * 侧滑
 */
public class SideslipActivity extends BasActivity {
    private SlidingMenu mMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_sideslip);
        mMenu = (SlidingMenu) findViewById(R.id.id_menu);
    }
    public void toggleMenu2(View view) {
        mMenu.closeMenu();
    }
}
