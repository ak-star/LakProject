package com.lak.core.mvp;

import android.os.Bundle;

import com.lak.core.base.BaseFragment;
import com.lak.core.mvp.base.MvpView;
import com.lak.core.mvp.impl.MvpPresenterImpl;
import com.lak.core.mvp.support.contract.callback.MvpDelegateCallback;

/**
 * Created by lawrence on 2018/4/9.
 */

public abstract class MvpFragment<V extends MvpView, P extends MvpPresenterImpl<V>>
        extends BaseFragment implements MvpDelegateCallback<V, P>, MvpView {
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
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
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
