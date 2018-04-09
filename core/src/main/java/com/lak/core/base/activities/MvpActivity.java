package com.lak.core.base.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.lak.core.mvp.MvpView;
import com.lak.core.mvp.impl.MvpPresenterImpl;
import com.lak.core.mvp.support.delegate.MvpDelegate;

/**
 * Created by lawrence on 2018/4/3.
 */

public abstract class MvpActivity<V extends MvpView, P extends MvpPresenterImpl<V>>
        extends BaseActivity implements MvpDelegate<V, P>, MvpView {
    // ---------------------------------------------------
    private P mPresenter;       // mvp中 presenter


    // ---------------------------------------------------
    @Override
    public P mvpPresenter() {
        return mPresenter;
    }

    @SuppressWarnings("unchecked")
    @Override
    public V mvpView() {
        return (V) this;
    }


    // ---------------------------------------------------
    @SuppressWarnings("unchecked")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (mPresenter == null)
            mPresenter = newPresenter();
        mPresenter.clazzName(sClassName).attachView(mvpView());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mPresenter != null)
            mPresenter.detachView();
        mPresenter = null;
    }
}
