package com.ct.ctframe.presenter;

import com.ct.ctframe.view.impl.IBaseView;

/**
 * 项目名称：CTFrame.
 * 创建人： CT.
 * 创建时间: 2017/5/16.
 * GitHub:https://github.com/CNHTT
 */

public abstract class BasePresenter<T extends IBaseView> {

    public T mView;

    public void attach(T mView) {
        this.mView = mView;
    }

    public void detachView() {
        if (mView != null) {
            mView = null;
        }
    }
}
