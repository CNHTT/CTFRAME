package com.ct.ctframe.model.impl;

import android.content.Context;

import com.ct.ctframe.inter.OnLoginListener;

/**
 * 项目名称：CTFrame.
 * 创建人： CT.
 * 创建时间: 2017/5/21.
 * GitHub:https://github.com/CNHTT
 */

public interface ILoginModel extends BaseModel {
    public void loginNet(Context context, String number , String pass, OnLoginListener listener);
    public void loginWx(Context context,OnLoginListener listener);
}
