package com.ct.ctframe.view.holder;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * 项目名称：CTFrame.
 * 创建人： CT.
 * 创建时间: 2017/5/15.
 * GitHub:https://github.com/CNHTT
 */

public abstract class BaseHolder<T> extends RecyclerView.ViewHolder {
    protected View mView;
    protected T mData;
    protected Context mContext;

    public View getView() {
        return mView;
    }

    public BaseHolder(View view) {
        super(view);
        this.mView = view;
        mContext = this.mView.getContext();
        initView();

    }

    protected abstract void initView();

    public void setData(T mData) {
        this.mData = mData;
    }
}
