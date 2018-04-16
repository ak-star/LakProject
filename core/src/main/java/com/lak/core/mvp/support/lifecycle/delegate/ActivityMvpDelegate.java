package com.lak.core.mvp.support.lifecycle.delegate;

import android.os.Bundle;
import android.support.annotation.StringRes;

import com.lak.core.mvp.base.MvpView;
import com.lak.core.mvp.impl.MvpPresenterImpl;

/**
 * Created by lawrence on 2018/4/9.
 * <p>
 * Activity的生命周期，代理
 */

public interface ActivityMvpDelegate<V extends MvpView, P extends MvpPresenterImpl<V>> {
    // --------------------------------------------------------------------------------------
    public void toast(CharSequence toastStr);

    public void toast(@StringRes int resId);

    public void toastLong(CharSequence toastStr);

    public void toastLong(@StringRes int resId);


    // --------------------------------------------------------------------------------------
    public void onCreate(Bundle savedInstanceState);

    public void onResume();

    public void onPause();

    public void onSaveInstanceState(Bundle outState);

    public void onRestoreInstanceState(Bundle savedInstanceState);

    public void onDestroy();

//    /**
//     * 保存一个对象的实例
//     *      当Activity意外关闭，意外销毁，横竖屏切换导致onDestory方法没有回调，
//     *      或者我们需要使用的数据被意外释放掉时，自动调用
//     * @return
//     */
//    public Object onRetainCustomNonConfigurationInstance();

}
