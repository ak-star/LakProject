package com.lak.core.mvp;

/**
 * Created by lawrence on 2018/4/4.
 * <p>
 * Mvp中Presenter抽象接口
 */

public interface MvpPresenter<V extends MvpView> {

    /**
     * 绑定视图
     *
     * @param view
     */
    void attachView(V view);

    /**
     * 解除视图
     */
    void detachView();

}
