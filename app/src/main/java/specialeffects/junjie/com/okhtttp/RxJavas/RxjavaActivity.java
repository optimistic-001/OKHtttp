package specialeffects.junjie.com.okhtttp.RxJavas;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;
import specialeffects.junjie.com.okhtttp.BasActivity;
import specialeffects.junjie.com.okhtttp.R;
import specialeffects.junjie.com.okhtttp.Sideslip.util.L;

/**
 * Created by JIE on 2016/9/9.
 */
public class RxjavaActivity extends BasActivity implements View.OnClickListener {
    private ImageView img_rx;
    private Button but_delete, but_txt, but_img;
    private TextView txt_String;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activiyt_rxjava);
        initFind();
        initListener();

    }

    @Override
    protected void initFind() {
        super.initFind();
        img_rx = (ImageView) findViewById(R.id.img_rx);
        but_delete = (Button) findViewById(R.id.but_delete);
        but_txt = (Button) findViewById(R.id.but_txt);
        but_img = (Button) findViewById(R.id.but_img);
        txt_String = (TextView) findViewById(R.id.txt_String);
    }

    @Override
    protected void initListener() {
        super.initListener();
        but_delete.setOnClickListener(this);
        but_txt.setOnClickListener(this);
        but_img.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.but_delete:
                img_rx.setImageResource(R.mipmap.ic_launcher);
                txt_String.setText("");
                break;
            case R.id.but_txt:
                control(true);
                rxjavaString();
                break;
            case R.id.but_img:
                txt_String.setText("");
                control(false);
                rxjavaImg();
                break;
        }
    }

    private void control(boolean b) {
        if (b) {
            img_rx.setVisibility(View.GONE);
        } else {
            img_rx.setVisibility(View.VISIBLE);
        }
    }

    private void rxjavaString() {
        String[] names = {"Rxjava", "输出"};
        Observable.from(names)
                .subscribe(new Action1<String>() {
                    @Override
                    public void call(String name) {
                        txt_String.setText(name);
                        L.d(name);
                    }
                });
    }

    //    .just(R.mipmap.ic_launcher, R.mipmap.meinv)
//    .subscribe(new Action1<Integer>() {
//        @Override
//        public void call(Integer integer) {
//
//        }
//    });
    private void rxjavaImg() {
        int[] drawableRes = {R.mipmap.ic_launcher, R.mipmap.meinv};
        Observable.just(drawableRes).subscribeOn(Schedulers.io()) // 指定 subscribe() 发生在 IO 线程
                .observeOn(AndroidSchedulers.mainThread()) // 指定 Subscriber 的回调发生在主线程
                .subscribe(new Action1<int[]>() {
                               @Override
                               public void call(int[] ints) {
                                   img_rx.setImageResource(ints[1]);
                               }
                           }
                );


//        Observable.create(new Observable.OnSubscribe<Drawable>() {
//            @Override
//            public void call(Subscriber<? super Drawable> subscriber) {
//                Drawable drawable = null;
//                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
//                    drawable = getTheme().getDrawable(R.mipmap.meinv);
//                }
//                subscriber.onNext(drawable);
//                subscriber.onCompleted();
//            }
//        }).subscribe(new Observer<Drawable>() {
//            @Override
//            public void onCompleted() {
//
//            }
//
//            @Override
//            public void onError(Throwable e) {
//                L.d(e.toString());
//                txt_String.setText(e.toString());
//
//            }
//
//            @Override
//            public void onNext(Drawable drawable) {
//                img_rx.setImageDrawable(drawable);
//            }
//        });
    }
}
