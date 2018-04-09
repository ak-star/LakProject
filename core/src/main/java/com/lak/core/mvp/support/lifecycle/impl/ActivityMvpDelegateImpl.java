package com.lak.core.mvp.support.lifecycle.impl;

import android.os.Bundle;
import android.support.annotation.NonNull;

import com.lak.core.mvp.base.MvpView;
import com.lak.core.mvp.impl.MvpPresenterImpl;
import com.lak.core.mvp.support.contract.callback.ActivityMvpDelegateCallback;
import com.lak.core.mvp.support.contract.proxy.MvpDelegateCallbackProxy;
import com.lak.core.mvp.support.lifecycle.delegate.ActivityMvpDelegate;
import com.lak.core.tools.Preconditions;

/**
 * Created by lawrence on 2018/4/9.
 * <p>
 * 代理模式—静态代理：具体的目标接口实现类
 * 该实现类对应的代理类是Activity
 *
 * @param <V> MvpView实现类
 * @param <P> MvpPresenterImpl<V>实现类
 */

public class ActivityMvpDelegateImpl<V extends MvpView, P extends MvpPresenterImpl<V>>
        implements ActivityMvpDelegate<V, P> {
    /**
     * 具体目标接口实现类，需要持有创建mvp的实例
     * ActivityMvpDelegateCallback继承接口MvpDelegateCallback
     */
    private ActivityMvpDelegateCallback<V, P> mDelegateCallback = null;

    /**
     * MvpDelegateCallback的代理对象
     */
    private MvpDelegateCallbackProxy<V, P> mProxy = null;

    public ActivityMvpDelegateImpl(
            @NonNull ActivityMvpDelegateCallback<V, P> delegateCallback) {
        Preconditions.checkNotNull(delegateCallback);
        this.mDelegateCallback = delegateCallback;
    }

    /**
     * 得到MvpDelegateCallback的代理对象
     *
     * @return
     */
    private MvpDelegateCallbackProxy<V, P> delegateProxy() {
        if (this.mProxy == null)
            this.mProxy = new MvpDelegateCallbackProxy<V, P>(mDelegateCallback);
        return this.mProxy;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        delegateProxy().newPresenter();
        delegateProxy().attachView().clazzName();
    }

    @Override
    public void onResume() {

    }

    @Override
    public void onPause() {

    }

    @Override
    public void onDestroy() {
        delegateProxy().detachView();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {

    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {

    }
}