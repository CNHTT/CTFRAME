package com.ct.ctframe.presenter;

import com.ct.ctframe.bean.User;
import com.ct.ctframe.inter.OnLoginListener;
import com.ct.ctframe.model.LoginModel;
import com.ct.ctframe.model.impl.ILoginModel;
import com.ct.ctframe.view.impl.ILoginView;

/**
 * 项目名称：CTFrame.
 * 创建人： CT.
 * 创建时间: 2017/5/21.
 * GitHub:https://github.com/CNHTT
 */

public class LoginPresenter extends BasePresenter<ILoginView> implements OnLoginListener {
    private ILoginModel mLoginModel;

    public LoginPresenter() {
        this.mLoginModel = new LoginModel();
    }

    public void loginNet(){
        mLoginModel.loginNet(mView.getContext(),mView.getNumber(),mView.getPass(),this);
    }

    @Override
    public void loginSuccess(User user) {
        mView.setLoginToMain(user);
    }

    @Override
    public void loginSuccessWx(String str) {

    }

    @Override
    public void loginFailed(String str) {
        mView.setFailedError(str);
    }
}
