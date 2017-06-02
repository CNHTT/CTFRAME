package com.ct.ctframe.view.impl;

import android.content.Context;

import com.ct.ctframe.bean.User;
import com.ct.ctframe.view.activity.LoginActivity;

/**
 * 项目名称：CTFrame.
 * 创建人： CT.
 * 创建时间: 2017/5/21.
 * GitHub:https://github.com/CNHTT
 */

public interface ILoginView extends IBaseView {


    String getNumber();
    String getPass();
    Context getContext();
    LoginActivity getActivity();

    /**
     * 用户登录成功跳转失败
     * @param user
     */
    void setLoginToMain(User user);

    /**
     * 微信登录成功
     * @param str
     */
    void setLoginWx(String str);

    /**
     * 登录失败
     * @param str
     */
    void setFailedError(String str);

}
