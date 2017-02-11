package specialeffects.junjie.com.okhtttp.Screenshot;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import specialeffects.junjie.com.okhtttp.BasActivity;
import specialeffects.junjie.com.okhtttp.R;
import specialeffects.junjie.com.okhtttp.Sideslip.util.ScreenUtils;

/**
 * Created by JIE on 2016/9/6.
 * 截屏
 */
public class ScreenshotActivity extends BasActivity implements View.OnClickListener{
    Button btu_scre_yes,btu_scre_no,btu_huifu;
    ImageView img_scre;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screenshot);
        initFind();
        initListener();
    }

    @Override
    protected void initFind() {
        super.initFind();
        btu_scre_yes = (Button) findViewById(R.id.btu_scre_yes);
        btu_scre_no = (Button) findViewById(R.id.btu_scre_no);
        btu_huifu= (Button) findViewById(R.id.btu_huifu);
        img_scre = (ImageView) findViewById(R.id.img_scre);
    }

    @Override
    protected void initListener() {
        super.initListener();
        btu_scre_yes.setOnClickListener(this);
        btu_scre_no.setOnClickListener(this);
        btu_huifu.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btu_scre_yes:
                img_scre.setImageBitmap(ScreenUtils.snapShotWithStatusBar(this));
                break;
            case R.id.btu_scre_no:
                img_scre.setImageBitmap(ScreenUtils.snapShotWithoutStatusBar(this));
                break;
            case R.id.btu_huifu:
                img_scre.setImageResource(R.mipmap.meinv);
                break;
        }
    }
}
