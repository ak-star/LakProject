package com.lak.core.base;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.lak.core.mvp.MvpView;
import com.lak.core.mvp.impl.MvpPresenterImpl;

/**
 * Created by lawrence on 2018/4/3.
 */

public abstract class MvpActivity<V extends MvpView, P extends MvpPresenterImpl<V>> extends BaseActivity {
    // ---------------------------------------------------
    protected abstract V newView();           // 创建mvp的view
    protected abstract P newPresenter();      // 创建mvp的presenter

    // ---------------------------------------------------
    private V mView;            // mvp中 view
    private P mPresenter;       // mvp中 presenter

    // ---------------------------------------------------
    protected V mvpView() {
        return mView;
    }
    protected P mvpPresenter() {
        return mPresenter;
    }

    // ---------------------------------------------------
    @SuppressWarnings("unchecked")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (mPresenter == null)
            mPresenter = newPresenter();
        if (mView == null)
            mView = newView();
        mPresenter.clazzName(sClassName).attachView(mView);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mView = null;
        if (mPresenter != null)
            mPresenter.detachView();
        mPresenter = null;
    }
}
