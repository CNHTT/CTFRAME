package com.ct.ctframe.view.activity;

import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Context;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.LinearInterpolator;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.VideoView;

import com.ct.ctframe.R;
import com.ct.ctframe.bean.User;
import com.ct.ctframe.presenter.BasePresenter;
import com.ct.ctframe.presenter.LoginPresenter;
import com.ct.ctframe.view.impl.ILoginView;
import com.ct.util.AndroidBug5497Workaround;
import com.ct.util.AnimationUtils;

import java.util.Random;

public class LoginActivity extends BaseActivity implements ILoginView, View.OnClickListener {

    private String number;
    private String pass;

    private VideoView videoView;
    private Button loginWx;

    private ImageView loginLImg;
    private int [] videos = {R.raw.a,R.raw.b};

    private ScrollView  mScrollView;
    private LinearLayout mContent;
    private LinearLayout mService;
    private int screenHeight = 0;//屏幕亮度
    private int keyHeight = 0;   //软件盘弹起的高度
    private float scale = 0.6f ; //logoImg的缩放的比例
    private int height = 0 ;

    private EditText mEtMobile;
    private EditText mEtPass;
    @Override
    protected void initViewFindByid() {
        videoView = (VideoView) findViewById(R.id.login_bg_vd);
        loginWx = (Button) findViewById(R.id.login_wx);
        loginLImg = (ImageView) findViewById(R.id.login);
        mScrollView = (ScrollView) findViewById(R.id.scrollView);
        mContent = (LinearLayout) findViewById(R.id.content);
        mService = (LinearLayout) findViewById(R.id.service);
    }

    @Override
    protected void init() {

        Random random = new Random(SystemClock.elapsedRealtime());
        final String videoPath = Uri.parse("android.resource://"+getPackageName() +"/"+ videos[random.nextInt(videos.length)]).toString();

        videoView.setVideoPath(videoPath);
        videoView.start();
        videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                mp.setVolume(0F,0F);
                mp.start();
                mp.setLooping(true);
            }
        });

        videoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                videoView.setVideoPath(videoPath);
                videoView.start();
            }
        });

        loginWx.setOnClickListener(this);
        mScrollView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return true;
            }
        });
        mScrollView.addOnLayoutChangeListener(new View.OnLayoutChangeListener() {
            @Override
            public void onLayoutChange(View v, int left, int top, int right, int bottom, int oldLeft, int oldTop, int oldRight, int oldBottom) {
                 /* old是改变前的左上右下坐标点值，没有old的是改变后的左上右下坐标点值
              现在认为只要控件将Activity向上推的高度超过了1/3屏幕高，就认为软键盘弹起*/
                if (oldBottom != 0 && bottom != 0 && (oldBottom - bottom > keyHeight)) {
                    Log.e("wenzhihao", "up------>" + (oldBottom - bottom));
                    int dist = mContent.getBottom() - bottom;
                    if (dist > 0) {
                        ObjectAnimator mAnimatorTranslateY = ObjectAnimator.ofFloat(mContent, "translationY", 0.0f, -dist);
                        mAnimatorTranslateY.setDuration(300);
                        mAnimatorTranslateY.setInterpolator(new LinearInterpolator());
                        mAnimatorTranslateY.start();
                        AnimationUtils.zoomIn(loginLImg, 0.6f, dist);
                    }
                    mService.setVisibility(View.INVISIBLE);

                } else if (oldBottom != 0 && bottom != 0 && (bottom - oldBottom > keyHeight)) {
                    Log.e("wenzhihao", "down------>" + (bottom - oldBottom));
                    if ((mContent.getBottom() - oldBottom) > 0) {
                        ObjectAnimator mAnimatorTranslateY = ObjectAnimator.ofFloat(mContent, "translationY", mContent.getTranslationY(), 0);
                        mAnimatorTranslateY.setDuration(300);
                        mAnimatorTranslateY.setInterpolator(new LinearInterpolator());
                        mAnimatorTranslateY.start();
                        //键盘收回后，logo恢复原来大小，位置同样回到初始位置
                        AnimationUtils.zoomOut(loginLImg, 0.6f);
                    }
                    mService.setVisibility(View.VISIBLE);
                }
            }
        });

    }


    @Override
    public int getToolBarId() {
        return 0;
    }

    @Override
    public BasePresenter getPresenter() {
        return new LoginPresenter();
    }

    @Override
    public void bindView(Bundle savedInstanceState) {
        if (isFullScreen(this)) {
            AndroidBug5497Workaround.assistActivity(this);
        }
        screenHeight = this.getResources().getDisplayMetrics().heightPixels;//屏幕的高度
        keyHeight = screenHeight /3;
    }

    @Override
    public int getContentLayout() {
        return R.layout.activity_login;
    }


    @Override
    public void setLoginToMain(User user) {

    }

    @Override
    public void setLoginWx(String str) {

    }

    @Override
    public String getNumber() {
        return number;
    }

    @Override
    public String getPass() {
        return pass;
    }

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public LoginActivity getActivity() {
        return this;
    }

    @Override
    public void setFailedError(String str) {

    }

    /**
     *
     * @param activity
     * @return
     */
    public boolean isFullScreen(Activity activity) {
        return (activity.getWindow().getAttributes().flags &
                WindowManager.LayoutParams.FLAG_FULLSCREEN) == WindowManager.LayoutParams.FLAG_FULLSCREEN;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.login_wx:

                break;
            case  R.id.btn_login:
                ((LoginPresenter)getPresenter()).loginNet();
                break;

            default:
        }
    }
}
