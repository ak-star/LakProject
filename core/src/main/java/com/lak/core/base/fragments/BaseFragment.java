package com.lak.core.base.fragments;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by lawrence on 2018/4/9.
 */

public abstract class BaseFragment extends Fragment {
    // ---------------------------------------------------
    protected abstract @LayoutRes int getLayoutId();

    // ---------------------------------------------------
    protected final String sClassName = this.getClass().getSimpleName();
    protected Context mCtx;     // 上下文  文法
    protected View mRootView = null;   // 根View

    // ---------------------------------------------------
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.mCtx = context;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) { this.mCtx = activity; }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mRootView = inflater.inflate(getLayoutId(), container, false);
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

}
