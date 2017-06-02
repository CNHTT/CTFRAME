package com.ct.ctframe.view.activity;

import android.content.Context;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.Button;
import android.widget.VideoView;

import com.ct.ctframe.R;
import com.ct.ctframe.bean.User;
import com.ct.ctframe.presenter.BasePresenter;
import com.ct.ctframe.presenter.LoginPresenter;
import com.ct.ctframe.view.impl.ILoginView;

import java.util.Random;

public class LoginActivity extends BaseActivity implements ILoginView, View.OnClickListener {

    private String number;
    private String pass;

    private VideoView videoView;
    private Button loginWx;

    private int [] videos = {R.raw.a,R.raw.b};
    @Override
    protected void initViewFindByid() {
        videoView = (VideoView) findViewById(R.id.login_bg_vd);
        loginWx = (Button) findViewById(R.id.login_wx);
    }

    @Override
    protected void init() {

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
        Random random = new Random(SystemClock.elapsedRealtime());
        final String videoPath = Uri.parse("android.resource://"+getPackageName() +"/"+ videos[random.nextInt(videos.length)]).toString();

        videoView.setVideoPath(videoPath);
        videoView.start();
        videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                mp.setVolume(1.0F,1.0F);
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
