package com.lak.prj.mvp.presenter;

import android.content.Context;

import com.lak.core.mvp.impl.MvpPresenterImpl;
import com.lak.prj.mvp.view.LoginView;

/**
 * Created by lawrence on 2018/4/10.
 * <p>
 * 收入报表
 */

public class LoginPresenter extends MvpPresenterImpl<LoginView> {

    public LoginPresenter(Context context) {
        super(context);
    }

}
