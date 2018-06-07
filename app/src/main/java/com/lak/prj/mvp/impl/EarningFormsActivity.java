package com.lak.prj.mvp.impl;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.view.View;

import com.lak.core.mvp.MvpActivity;
import com.lak.prj.R;
import com.lak.prj.mvp.presenter.LoginPresenter;
import com.lak.prj.mvp.view.LoginView;

/**
 * Created by lawrence on 2018/6/7.
 * <p>
 * 收入报表
 */

public class EarningFormsActivity extends MvpActivity<LoginView, LoginPresenter>
        implements LoginView, View.OnClickListener {

    @Override
    protected LoginPresenter bindPresenter() {
        return new LoginPresenter(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_earning_forms;
    }
    @Override
    protected View getRootView() {
        return null;
    }

    @Override
    protected void initialDatas(@Nullable Intent intent) { }

    @Override
    protected void initialViews() { }

    @Override
    protected void initialListeners() { }

    @Override
    public void onClick(View view) { }

}
