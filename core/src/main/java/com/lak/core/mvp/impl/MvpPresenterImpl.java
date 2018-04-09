package com.lak.core.mvp.impl;

import android.content.Context;

import com.lak.core.mvp.base.MvpPresenter;
import com.lak.core.mvp.base.MvpView;
import com.lak.core.tools.Preconditions;

import java.lang.ref.WeakReference;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Created by lawrence on 2018/4/3.
 * <p>
 * MVP模式 Presenter抽象类
 * 统一管理View层绑定和解除绑定
 * <p>
 * 通过动态代理，优化 null 判断
 */

public abstract class MvpPresenterImpl<V extends MvpView> implements MvpPresenter<V> {
    protected Context mCtx = null;
    protected String sClassName = this.getClass().getSimpleName();

    private WeakReference<V> mWeakView; // mvp中view对象的若引用
    private V mProxyMvpView;            // mvp中view对象的动态代理

    public MvpPresenterImpl(Context context) {
        this.mCtx = context;
    }

    /**
     * 绑定view
     *
     * @param mvpView
     */
    @SuppressWarnings("unchecked")
    @Override
    public void attachView(V mvpView) {
        Preconditions.checkNotNull(mvpView);
        this.mWeakView = new WeakReference<V>(mvpView);
        MvpViewInvocationHandler invocationHandler
                = new MvpViewInvocationHandler(this.mWeakView.get());
        mProxyMvpView = (V) Proxy.newProxyInstance(
                mvpView.getClass().getClassLoader(),
                mvpView.getClass().getInterfaces(), invocationHandler);
    }

    /**
     * 释放资源，防止内存泄漏
     */
    @Override
    public void detachView() {
        mCtx = null;
        if (mWeakView != null)
            mWeakView.clear();
        mWeakView = null;
    }

    /**
     * 设置className
     *
     * @param clazzName
     * @return
     */
    public MvpPresenterImpl clazzName(String clazzName) {
        this.sClassName = clazzName;
        return this;
    }

    /**
     * 获得Mvp中的View对象
     *
     * @return
     */
    protected V mvpView() {
        return mProxyMvpView;
    }

    /**
     * 用于检查View是否为null
     *
     * @return true-不为null，false-是null
     */
    protected boolean isAttachView() {
        return mWeakView != null && mWeakView.get() != null;
    }

    /**
     * 代理具体实现
     *
     * @param <V>
     */
    private class MvpViewInvocationHandler<V extends MvpView> implements InvocationHandler {
        private V mvpView;

        public MvpViewInvocationHandler(V mvpView) {
            this.mvpView = mvpView;
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            if (isAttachView())
                return method.invoke(this.mvpView, args);
            return null;
        }
    }

}
