package com.lak.core.mvp.support.lifecycle.impl;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.lak.core.mvp.base.MvpView;
import com.lak.core.mvp.impl.MvpPresenterImpl;
import com.lak.core.mvp.support.contract.callback.FragmentMvpDelegateCallback;
import com.lak.core.mvp.support.contract.proxy.MvpDelegateCallbackProxy;
import com.lak.core.mvp.support.lifecycle.delegate.FragmentMvpDelegate;
import com.lak.core.tools.Preconditions;

/**
 * Created by lawrence on 2018/4/9.
 */

public class FragmentMvpDelegateImpl<V extends MvpView, P extends MvpPresenterImpl<V>>
        implements FragmentMvpDelegate<V, P> {
    /**
     * 具体目标接口实现类，需要持有创建mvp的实例
     * FragmentMvpDelegateCallback继承接口MvpDelegateCallback
     */
    private FragmentMvpDelegateCallback<V, P> mDelegateCallback = null;

    /**
     * MvpDelegateCallback的代理对象
     */
    private MvpDelegateCallbackProxy<V, P> mProxy = null;

    public FragmentMvpDelegateImpl(
            @NonNull FragmentMvpDelegateCallback<V, P> delegateCallback) {
        Preconditions.checkNotNull(delegateCallback);
        this.mDelegateCallback = delegateCallback;
    }

    private MvpDelegateCallbackProxy<V, P> delegateProxy() {
        if (this.mProxy == null)
            this.mProxy = new MvpDelegateCallbackProxy<V, P>(mDelegateCallback);
        return this.mProxy;
    }

    @Override
    public void onAttach(Context context) { }

    @Override
    public void onAttach(Activity activity) { }

    @Override
    public void onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        delegateProxy().newPresenter();
        delegateProxy().attachView().clazzName();
    }

    @Override
    public void onResume() { }

    @Override
    public void onPause() { }

    @Override
    public void onDestroy() {
        delegateProxy().detachView();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) { }

    @Override
    public void onViewStateRestored(Bundle savedInstanceState) { }

}

