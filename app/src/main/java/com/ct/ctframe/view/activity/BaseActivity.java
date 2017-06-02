package com.ct.ctframe.view.activity;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Process;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

import com.ct.ctframe.R;
import com.ct.ctframe.inter.IBase;
import com.ct.ctframe.presenter.BasePresenter;
import com.ct.ctframe.utils.AppManager;
import com.ct.ctframe.utils.ContextUtils;
import com.ct.ctframe.utils.SystemBarTintManager;
import com.ct.ctframe.view.impl.IBaseView;

/**
 * 项目名称：CTFrame.
 * 创建人： CT.
 * 创建时间: 2017/5/15.
 * GitHub:https://github.com/CNHTT
 */

public abstract class BaseActivity<T extends BasePresenter> extends AppCompatActivity implements IBase {

    /**
     * 主线程
     */
    private long mUIThreadId;

    private void setActionBar(){};

    private void getIntentValue(){};


    /**
     * 是否设置沉浸式
     *
     * @return
     */
    protected boolean isSetStatusBar() {
        return false;
    }


    protected BasePresenter mPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mUIThreadId = android.os.Process.myTid();
        AppManager.getAppManager().addActivity(this);
        mPresenter = getPresenter();
        if (mPresenter != null && this instanceof IBaseView) {
            mPresenter.attach((IBaseView) this);
        }
        initWindow();
        getIntentValue();
        mRootView = createView(null, null, savedInstanceState);
        setContentView(mRootView);

        if (getToolBarId()!=0){
            mToolbar = (Toolbar) findViewById(getToolBarId());
            setSupportActionBar(mToolbar);
        }
        setActionBar();
        initViewFindByid();
        init();
        bindView(savedInstanceState);
    }

    protected abstract void init();

    protected abstract void initViewFindByid();

    @TargetApi(19)
    private void initWindow() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT && isSetStatusBar()) {
            getWindow().addFlags(
                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            tintManager = new SystemBarTintManager(this);
            tintManager.setStatusBarTintEnabled(true);
            tintManager.setStatusBarTintResource(R.color.outside_color_trans);
        }
    }
    private SystemBarTintManager tintManager;

    /**
     * add new color to window
     * @param color
     */
    protected void setStatusBarTintRes(int color) {
        if (tintManager != null) {
            tintManager.setStatusBarTintResource(color);
        }
    }

    public abstract int getToolBarId();

    public Toolbar getToolbar() {
        return mToolbar;
    }

    private Toolbar mToolbar;
    protected View mRootView;

  

    @Override
    public View createView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = ContextUtils.inflate(this, getContentLayout());
        return view;
    }
    /**
     * 获取UI线程ID
     *
     * @return UI线程ID
     */
    public long getUIThreadId() {
        return mUIThreadId;
    }
    @Override
    protected void onNewIntent(Intent intent) {
        mUIThreadId = Process.myTid();
        super.onNewIntent(intent);
    }


    @Override
    public View getView() {
        return mRootView;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onDestroy() {
        AppManager.getAppManager().finishActivity(this);
        if (mPresenter != null && this instanceof IBaseView) {
            mPresenter.detachView();
            mPresenter = null;
        }
        super.onDestroy();
    }

}
