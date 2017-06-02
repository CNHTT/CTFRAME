package com.ct.ctframe.model;

import android.content.Context;

import com.ct.ctframe.inter.OnLoginListener;
import com.ct.ctframe.model.impl.ILoginModel;

/**
 * 项目名称：CTFrame.
 * 创建人： CT.
 * 创建时间: 2017/5/21.
 * GitHub:https://github.com/CNHTT
 */

public class LoginModel implements ILoginModel {


    /**
     * 用户登录
     * @param context
     * @param number
     * @param pass
     * @param listener
     */
    @Override
    public void loginNet(Context context, String number, String pass, OnLoginListener listener) {

    }

    /**
     * 微信登录
     * @param context
     * @param listener
     */
    @Override
    public void loginWx(Context context, OnLoginListener listener) {

    }
}
