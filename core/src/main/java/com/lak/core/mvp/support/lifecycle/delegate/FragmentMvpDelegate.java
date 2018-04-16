package com.lak.core.mvp.support.lifecycle.delegate;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.StringRes;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.lak.core.mvp.base.MvpView;
import com.lak.core.mvp.impl.MvpPresenterImpl;

/**
 * Created by lawrence on 2018/4/9.
 * <p>
 * Fragment的生命周期，代理
 */

public interface FragmentMvpDelegate<V extends MvpView, P extends MvpPresenterImpl<V>> {
    // --------------------------------------------------------------------------------------
    public void toast(CharSequence toastStr);

    public void toast(@StringRes int resId);

    public void toastLong(CharSequence toastStr);

    public void toastLong(@StringRes int resId);


    // --------------------------------------------------------------------------------------
    public void onAttach(Context context);

    public void onAttach(Activity activity);

    public void onCreateView(LayoutInflater inflater,
                 ViewGroup container, Bundle savedInstanceState);

    public void onActivityCreated(Bundle savedInstanceState);

    public void onResume();

    public void onPause();

    public void onSaveInstanceState(Bundle outState);

    public void onViewStateRestored(Bundle savedInstanceState);

    public void onDestroy();


}
