package com.lak.core.base;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lak.core.tools.ToastUtils;

/**
 * Created by lawrence on 2018/4/9.
 * <p>
 * BaseFragment
 */

public abstract class BaseFragment extends Fragment {
    // ---------------------------------------------------
    protected abstract @LayoutRes int getLayoutId();
    protected abstract View getRootView();

    // ---------------------------------------------------
    protected Context mCtx;     // 上下文  文法
    protected View mRootView = null;   // 根View

    // ---------------------------------------------------
    @Nullable
    protected <T extends View> T findViewById(@IdRes int resId) {
        T result = null;
        if (mRootView != null)
            result = mRootView.findViewById(resId);
        return result;
    }

    // ---------------------------------------------------
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.mCtx = context;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.mCtx = activity;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mRootView = getRootView();
        if (mRootView == null) {
            mRootView = inflater.inflate(getLayoutId(), container, false);
        }
        return mRootView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        // 初始化操作
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    // ---------------------------------------------------
    protected void toast(CharSequence toastStr) {
        ToastUtils.instance().show(toastStr);
    }
    protected void toast(@StringRes int resId) {
        ToastUtils.instance().show(resId);
    }
    protected void toastLong(CharSequence toastStr) {
        ToastUtils.instance().showLong(toastStr);
    }
    protected void toastLong(@StringRes int resId) {
        ToastUtils.instance().showLong(resId);
    }

}
