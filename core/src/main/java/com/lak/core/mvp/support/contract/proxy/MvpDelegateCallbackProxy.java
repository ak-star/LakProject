package com.lak.core.mvp.support.contract.proxy;

import android.support.annotation.NonNull;

import com.lak.core.mvp.base.MvpView;
import com.lak.core.mvp.impl.MvpPresenterImpl;
import com.lak.core.mvp.support.contract.callback.MvpDelegateCallback;
import com.lak.core.tools.Preconditions;

/**
 * Created by lawrence on 2018/4/9.
 * <p>
 * 实现MvpDelegateCallback接口类的代理对象
 */

public class MvpDelegateCallbackProxy<V extends MvpView, P extends MvpPresenterImpl<V>>
        implements MvpDelegateCallback<V, P> {

    /**
     * MvpDelegateCallback的具体实现类
     */
    private MvpDelegateCallback<V, P> mCallback = null;

    public MvpDelegateCallbackProxy(@NonNull MvpDelegateCallback<V, P> mvpDelegateCallback) {
        Preconditions.checkNotNull(mvpDelegateCallback);
        this.mCallback = mvpDelegateCallback;
    }

    @Override
    public P newPresenter() {
        P presenter = mCallback.mvpPresenter();
        if (presenter == null)
            presenter = mCallback.newPresenter();
        Preconditions.checkNotNull(presenter, "Presenter is not null.");
        return presenter;
    }

    @Override
    public P mvpPresenter() {
        P presenter = mCallback.mvpPresenter();
        Preconditions.checkNotNull(presenter, "Presenter is not null.");
        return presenter;
    }

    @Override
    public V mvpView() {
        return mCallback.mvpView();
    }

    protected String mvpClassName() {
        return mCallback.getClass().getSimpleName();
    }

    /**
     * 绑定view
     */
    public MvpDelegateCallbackProxy<V, P> attachView() {
        mvpPresenter().attachView(mvpView());
        return this;
    }

    /**
     * 解绑view
     */
    public void detachView() {
        mvpPresenter().detachView();
    }

    /**
     * 设置className
     */
    public MvpDelegateCallbackProxy<V, P> clazzName() {
        mvpPresenter().clazzName(mvpClassName());
        return this;
    }

}
