package com.ct.ctframe.view.activity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.Window;
import android.widget.ImageView;

import com.ct.ctframe.ConstantValue;
import com.ct.ctframe.MainActivity;
import com.ct.ctframe.R;
import com.ct.ctframe.utils.PreferenceUtils;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;

public class SplashActivity extends Activity{

    private ImageView mIVEntry;

    private  static  final  int ANIM_TIME = 2000;
    private  static  final  float SCALE_END = 1.15F;


    private static final int[] imgs={
            R.drawable.welcomimg1,R.drawable.welcomimg2,
            R.drawable.welcomimg3,R.drawable.welcomimg4,
            R.drawable.welcomimg5, R.drawable.welcomimg6,
            R.drawable.welcomimg7,R.drawable.welcomimg8,
            R.drawable.welcomimg9,R.drawable.welcomimg10,
            R.drawable.welcomimg11,R.drawable.welcomimg12};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        //判断应用是否第一次加载
        boolean isFirstOpen = PreferenceUtils.getPrefBoolean(this, ConstantValue.FIRST_OPEN,true);
        //如果是第一次启动则进入功能引导界面
        if (isFirstOpen)
        {
            Intent intent = new Intent(this,SplashGuideActivity.class);
            startActivity(intent);
            finish();
            return;
        }
        setContentView(R.layout.activity_splash);
        initFindById();
        startMainActivity();

    }

    private void startMainActivity() {
        Random random = new Random(SystemClock.elapsedRealtime());
        mIVEntry.setImageResource(imgs[random.nextInt(imgs.length)]);

        Observable.timer(1000,TimeUnit.MILLISECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Long>() {
                    @Override
                    public void accept(Long aLong) throws Exception {
                        startAnim();
                    }
                });

//         Observer observer=   new Observer<Long>() {
//            @Override
//            public void onSubscribe(Disposable d) {
//                Log.d(getLocalClassName(),"onSubscribe");
//            }
//
//            @Override
//            public void onNext(Long value) {
//                Log.d(getLocalClassName(),"onNext");
//            }
//
//            @Override
//            public void onError(Throwable e) {
//                Log.d(getLocalClassName(),"onError");
//            }
//
//            @Override
//            public void onComplete() {
//                Log.d(getLocalClassName(),"onComplete");
//                startAnim();
//            }
//        };
    }

    private void startAnim() {
        ObjectAnimator animatorX = ObjectAnimator.ofFloat(mIVEntry,"scaleX",1F,SCALE_END);
        ObjectAnimator animatorY = ObjectAnimator.ofFloat(mIVEntry,"scaleY",1F,SCALE_END);

        AnimatorSet set = new AnimatorSet();
        set.setDuration(ANIM_TIME).play(animatorX).with(animatorY);
        set.start();

        set.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                //判断是否登录
                boolean isLogin = PreferenceUtils.getPrefBoolean(SplashActivity.this,ConstantValue.IS_LOGIN,true);
                //没有登录
                if (isLogin)
                {
                    Intent intent = new Intent(SplashActivity.this,LoginActivity.class);
                    startActivity(intent);
                    finish();
                    return;
                }
                startActivity(new Intent(SplashActivity.this, MainActivity.class));
                SplashActivity.this.finish();
            }
        });



    }

    private void initFindById() {
        mIVEntry = (ImageView) findViewById(R.id.iv_entry);
    }
}
