package com.lak.core.mvp.support.lifecycle.delegate;

import com.lak.core.mvp.base.MvpView;
import com.lak.core.mvp.impl.MvpPresenterImpl;

/**
 * Created by lawrence on 2018/4/9.
 * <p>
 * Fragment的生命周期，代理
 */

public interface FragmentMvpDelegate<V extends MvpView, P extends MvpPresenterImpl<V>> {
}
