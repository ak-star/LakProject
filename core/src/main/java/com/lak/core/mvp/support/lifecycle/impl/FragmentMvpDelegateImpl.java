package com.lak.core.mvp.support.lifecycle.impl;

import com.lak.core.mvp.base.MvpView;
import com.lak.core.mvp.impl.MvpPresenterImpl;
import com.lak.core.mvp.support.lifecycle.delegate.FragmentMvpDelegate;

/**
 * Created by lawrence on 2018/4/9.
 */

public class FragmentMvpDelegateImpl<V extends MvpView, P extends MvpPresenterImpl<V>>
        implements FragmentMvpDelegate<V, P> {
}

