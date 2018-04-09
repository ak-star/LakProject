package com.lak.core.base.fragments;

import android.app.Activity;
import android.content.Context;

import com.lak.core.mvp.MvpView;
import com.lak.core.mvp.impl.MvpPresenterImpl;
import com.lak.core.mvp.support.delegate.MvpDelegate;

/**
 * Created by lawrence on 2018/4/9.
 */

public abstract class MvpFragment<V extends MvpView, P extends MvpPresenterImpl<V>>
        extends BaseFragment implements MvpDelegate<V, P>, MvpView {
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
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        initialMvpFramework();  // 初始化Mvp框架信息
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        initialMvpFramework();  // 初始化Mvp框架信息
    }

    /**
     * 初始化Mvp框架信息
     */
    @SuppressWarnings("unchecked")
    private void initialMvpFramework() {
        if (mPresenter == null)
            mPresenter = newPresenter();
        mPresenter.clazzName(sClassName).attachView(mvpView());
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mPresenter != null)
            mPresenter.detachView();
        mPresenter = null;
    }
}
