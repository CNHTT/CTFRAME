package com.ct.ctframe.inter;

import com.ct.ctframe.bean.User;

/**
 * 项目名称：CTFrame.
 * 创建人： CT.
 * 创建时间: 2017/5/21.
 * GitHub:https://github.com/CNHTT
 */

public interface OnLoginListener {
    void loginSuccess(User user);

    void loginSuccessWx(String str);

    void loginFailed(String str);
}
