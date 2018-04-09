package com.lak.core.mvp.support.delegate;

import com.lak.core.mvp.MvpPresenter;
import com.lak.core.mvp.MvpView;

/**
 * Created by lawrence on 2018/4/9.
 */

public interface MvpDelegate<V extends MvpView, P extends MvpPresenter<V>> {

    /**
     * 创建Presenter方法
     *
     * @return
     */
    public P newPresenter();

    /**
     * 得到Presenter实例
     *
     * @return
     */
    public P mvpPresenter();

    /**
     * 得到具体的mvp的view对象
     *
     * @return
     */
    public V mvpView();
}
