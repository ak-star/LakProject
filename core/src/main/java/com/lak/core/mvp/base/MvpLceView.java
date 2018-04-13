package com.lak.core.mvp.base;

/**
 * Created by lawrence on 2018/4/10.
 * <p>
 * MVP模式 view抽象类
 */

public interface MvpLceView<M> extends MvpView {

    /**
     * 显示loading界面
     *
     * @param pullToRefresh true-代表你用的是下拉刷新组件
     */
    public void showLoading(boolean pullToRefresh);

    /**
     * 显示ContentView
     */
    public void showContent();

    /**
     * 显示异常界面
     */
    public void showError();

    /**
     * 绑定数据
     *
     * @param data
     */
    public void bindData(M data);

    /**
     * 加载数据
     *
     * @param pullToRefresh
     */
    public void loadData(boolean pullToRefresh);

}
