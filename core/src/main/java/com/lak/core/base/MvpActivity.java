package com.lak.core.base;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.lak.core.mvp.MvpPresenter;
import com.lak.core.mvp.MvpView;

/**
 * Created by lawrence on 2018/4/3.
 */

public abstract class MvpActivity<T extends MvpPresenter> extends BaseActivity implements MvpView {
    // ---------------------------------------------------
    protected abstract @NonNull T newPresenter();

    // ---------------------------------------------------
    private T mPresenter;       // mvp中 presenter

    // ---------------------------------------------------
    protected T presenter() {
        return mPresenter;
    }

    // ---------------------------------------------------
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter = newPresenter();
        mPresenter.ctx(this).clazzName(sClassName).attach(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mPresenter != null)
            mPresenter.detach();
        mPresenter = null;
    }
}
