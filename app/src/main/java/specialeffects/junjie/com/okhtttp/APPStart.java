package specialeffects.junjie.com.okhtttp;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import specialeffects.junjie.com.okhtttp.Carousel.CarouselActivity;
import specialeffects.junjie.com.okhtttp.OKHTTPS.MainActivity;
import specialeffects.junjie.com.okhtttp.RxJavas.RxjavaActivity;
import specialeffects.junjie.com.okhtttp.Screenshot.ScreenshotActivity;
import specialeffects.junjie.com.okhtttp.Share.ShareActivity;
import specialeffects.junjie.com.okhtttp.Sideslip.SideslipActivity;
import specialeffects.junjie.com.okhtttp.Signin.SigninActivity;
import specialeffects.junjie.com.okhtttp.Weather.WeatherActivity2;

/**
 * Created by JIE on 2016/9/6.
 * 主页面
 */
public class APPStart extends BasActivity implements View.OnClickListener {
    Button but_okhttp, but_Sideslip,but_Screenshot,btu_RxJava,but_Carousel,but_Weather,but_Share,but_Sign_in;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        init();
    }

    private void init() {
        but_okhttp = (Button) findViewById(R.id.but_okhttp);
        but_okhttp.setOnClickListener(this);
        but_Sideslip = (Button) findViewById(R.id.but_Sideslip);
        but_Sideslip.setOnClickListener(this);
        but_Screenshot = (Button) findViewById(R.id.but_Screenshot);
        but_Screenshot.setOnClickListener(this);
        btu_RxJava = (Button) findViewById(R.id.but_RxJava);
        btu_RxJava.setOnClickListener(this);
        but_Carousel = (Button) findViewById(R.id.but_Carousel);
        but_Carousel.setOnClickListener(this);
        but_Weather = (Button) findViewById(R.id.but_Weather);
        but_Weather.setOnClickListener(this);
        but_Share = (Button) findViewById(R.id.but_Share);
        but_Share.setOnClickListener(this);
        but_Sign_in = (Button) findViewById(R.id.but_Sign_in);
        but_Sign_in.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.but_okhttp:
                onpeActivity(MainActivity.class);
                break;
            case R.id.but_Sideslip:
                onpeActivity(SideslipActivity.class);
                break;
            case R.id.but_Screenshot:
                onpeActivity(ScreenshotActivity.class);
                break;
            case R.id.but_RxJava:
                onpeActivity(RxjavaActivity.class);
                break;
            case R.id.but_Carousel:
                onpeActivity(CarouselActivity.class);
                break;
            case R.id.but_Weather:
//                onpeActivity(WeatherActivity.class);
                onpeActivity(WeatherActivity2.class);
                break;
            case R.id.but_Share:
                onpeActivity(ShareActivity.class);
                break;
            case R.id.but_Sign_in:
                onpeActivity(SigninActivity.class);
                break;
        }
    }
}
