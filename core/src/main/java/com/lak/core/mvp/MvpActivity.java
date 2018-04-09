package com.lak.core.mvp;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.lak.core.base.BaseActivity;
import com.lak.core.mvp.base.MvpView;
import com.lak.core.mvp.impl.MvpPresenterImpl;
import com.lak.core.mvp.support.contract.callback.ActivityMvpDelegateCallback;
import com.lak.core.mvp.support.lifecycle.delegate.ActivityMvpDelegate;
import com.lak.core.mvp.support.lifecycle.impl.ActivityMvpDelegateImpl;

/**
 * Created by lawrence on 2018/4/3.
 */

public abstract class MvpActivity<V extends MvpView, P extends MvpPresenterImpl<V>>
        extends BaseActivity implements ActivityMvpDelegateCallback<V, P>, MvpView {
    // ---------------------------------------------------
    protected abstract P bindPresenter();   // 绑定Presenter实例对象

    // ---------------------------------------------------
    private P mPresenter;       // mvp中 presenter
    private ActivityMvpDelegate<V, P> mMvpDelegate = null;

    // ---------------------------------------------------
    @Override
    public P newPresenter() {
        return mPresenter = bindPresenter();
    }

    @Override
    public P mvpPresenter() {
        return mPresenter;
    }

    @SuppressWarnings("unchecked")
    @Override
    public V mvpView() {
        return (V) this;
    }

    protected ActivityMvpDelegate<V, P> mvpDelegate() {
        if (this.mMvpDelegate == null)
            this.mMvpDelegate = new ActivityMvpDelegateImpl<V, P>(this);
        return this.mMvpDelegate;
    }

    // ---------------------------------------------------
    @SuppressWarnings("unchecked")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mvpDelegate().onCreate(savedInstanceState);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mvpDelegate().onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mvpDelegate().onPause();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mvpDelegate().onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        mvpDelegate().onRestoreInstanceState(savedInstanceState);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mvpDelegate().onDestroy();
    }
}
