package com.lak.core.mvp;

import android.content.Context;
import android.support.annotation.NonNull;

import com.lak.core.tools.Preconditions;

/**
 * Created by lawrence on 2018/4/3.
 * <p>
 * MVP模式 基础Presenter
 */

public abstract class MvpPresenter<T extends MvpView> {
    protected Context mCtx = null;
    protected String sClassName = this.getClass().getSimpleName();
    private T mMvpView = null;      // mvp中view对象

    /**
     * 设置上下文，文法
     *
     * @param context 上下文，文法
     * @return 链式
     */
    public MvpPresenter ctx(Context context) {
        this.mCtx = context;
        return this;
    }

    /**
     * 设置className
     *
     * @param clazzName
     * @return
     */
    public MvpPresenter clazzName(String clazzName) {
        this.sClassName = clazzName;
        return this;
    }

    /**
     * 绑定view
     *
     * @param mvpView
     */
    public MvpPresenter attach(@NonNull T mvpView) {
        Preconditions.checkNotNull(mvpView);
        this.mMvpView = mvpView;
        return this;
    }

    /**
     * 释放资源，防止内存泄漏
     */
    public void detach() {
        mCtx = null;
        this.mMvpView = null;
    }

}
