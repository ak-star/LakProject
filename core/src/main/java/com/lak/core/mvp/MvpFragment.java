package com.lak.core.mvp;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lak.core.base.BaseFragment;
import com.lak.core.mvp.base.MvpView;
import com.lak.core.mvp.impl.MvpPresenterImpl;
import com.lak.core.mvp.support.contract.callback.FragmentMvpDelegateCallback;
import com.lak.core.mvp.support.lifecycle.delegate.FragmentMvpDelegate;
import com.lak.core.mvp.support.lifecycle.impl.FragmentMvpDelegateImpl;

/**
 * Created by lawrence on 2018/4/9.
 */

public abstract class MvpFragment<V extends MvpView, P extends MvpPresenterImpl<V>>
        extends BaseFragment implements FragmentMvpDelegateCallback<V, P>, MvpView {
    // ---------------------------------------------------
    protected abstract P bindPresenter();   // 绑定Presenter实例对象

    // ---------------------------------------------------
    private P mPresenter;       // mvp中 presenter
    private FragmentMvpDelegate<V, P> mMvpDelegate = null;

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

    protected FragmentMvpDelegate<V, P> mvpDelegate() {
        if (this.mMvpDelegate == null)
            this.mMvpDelegate = new FragmentMvpDelegateImpl<V, P>(this);
        return this.mMvpDelegate;
    }

    // ---------------------------------------------------
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mvpDelegate().onAttach(context);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mvpDelegate().onAttach(activity);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mRootView = inflater.inflate(getLayoutId(), container, false);
        mvpDelegate().onCreateView(inflater, container, savedInstanceState);
        return mRootView;
    }

    @SuppressWarnings("unchecked")
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mvpDelegate().onActivityCreated(savedInstanceState);
    }

    @Override
    public void onResume() {
        super.onResume();
        mvpDelegate().onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        mvpDelegate().onPause();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mvpDelegate().onSaveInstanceState(outState);
    }

    @Override
    public void onViewStateRestored(Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
        mvpDelegate().onViewStateRestored(savedInstanceState);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mvpDelegate().onDestroy();
    }

}
