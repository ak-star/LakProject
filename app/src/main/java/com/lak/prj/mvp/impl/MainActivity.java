package com.lak.prj.mvp.impl;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import com.lak.core.mvp.MvpActivity;
import com.lak.prj.R;
import com.lak.prj.mvp.model.MainModel;
import com.lak.prj.mvp.presenter.MainPresenter;
import com.lak.prj.mvp.view.MainView;

public class MainActivity extends MvpActivity<MainView, MainPresenter>
        implements MainView, View.OnClickListener {
    private TextView mTextView;

    @Override
    protected MainPresenter bindPresenter() {
        return new MainPresenter(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected View getRootView() {
        return null;
    }

    @Override
    protected void initialDatas(@Nullable Intent intent) { }

    @Override
    protected void initialViews() {
        mTextView = findViewById(R.id.text);
    }

    @Override
    protected void initialListeners() {
        findViewById(R.id.button).setOnClickListener(this);
    }

    @Override
    public void add(MainModel data) {
        mTextView.setText(Integer.toString(data.test));
        toast("toast do add " + data.test);
    }

    @Override
    public void onClick(View v) {
        mvpPresenter().doAdd();
    }

}
