package specialeffects.junjie.com.okhtttp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;

/**
 * Created by JIE on 2016/9/6.
 * BaseActivity
 */
public class BasActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
    }

    protected  void onpeActivity(Class<?> pClass){
        Intent intent = new Intent(this,pClass);
        this.startActivity(intent);
    }

    protected void initFind(){

    }

    protected void initListener(){

    }

    protected void initData(){

    }


}
