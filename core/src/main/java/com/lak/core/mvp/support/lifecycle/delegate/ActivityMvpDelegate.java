package com.lak.core.mvp.support.lifecycle.delegate;

import android.os.Bundle;

import com.lak.core.mvp.base.MvpView;
import com.lak.core.mvp.impl.MvpPresenterImpl;

/**
 * Created by lawrence on 2018/4/9.
 * <p>
 * Activity的生命周期，代理
 */

public interface ActivityMvpDelegate<V extends MvpView, P extends MvpPresenterImpl<V>> {

    public void onCreate(Bundle savedInstanceState);

    public void onResume();

    public void onPause();

    public void onSaveInstanceState(Bundle outState);

    public void onRestoreInstanceState(Bundle savedInstanceState);

    public void onDestroy();


}
