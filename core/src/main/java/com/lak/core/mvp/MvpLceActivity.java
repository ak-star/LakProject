package com.lak.core.mvp;

import com.lak.core.mvp.base.MvpLceView;
import com.lak.core.mvp.impl.MvpPresenterImpl;

/**
 * Created by lawrence on 2018/4/10.
 */

public abstract class MvpLceActivity<M, V extends MvpLceView<M>, P extends MvpPresenterImpl<V>>
        extends MvpActivity<V, P> implements MvpLceView<M> {
}
