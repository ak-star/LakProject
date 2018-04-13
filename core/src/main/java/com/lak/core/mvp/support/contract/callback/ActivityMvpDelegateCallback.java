package com.lak.core.mvp.support.contract.callback;

import com.lak.core.mvp.base.MvpView;
import com.lak.core.mvp.impl.MvpPresenterImpl;

/**
 * Created by lawrence on 2018/4/9.
 * <p>
 * 扩展目标接口 针对不同的模块，目标接口有差异
 */

public interface ActivityMvpDelegateCallback<V extends MvpView, P extends MvpPresenterImpl<V>>
        extends MvpDelegateCallback<V, P> {

//    /**
//     * 获取实例
//     *
//     * @return
//     */
//    public Object getLastCustomNonConfigurationInstance();

}
