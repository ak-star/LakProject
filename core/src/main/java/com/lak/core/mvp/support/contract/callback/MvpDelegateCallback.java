package com.lak.core.mvp.support.contract.callback;

import com.lak.core.mvp.base.MvpView;
import com.lak.core.mvp.impl.MvpPresenterImpl;

/**
 * Created by lawrence on 2018/4/9.
 * <p>
 * mvp代理接口类
 */

public interface MvpDelegateCallback<V extends MvpView, P extends MvpPresenterImpl<V>> {

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
