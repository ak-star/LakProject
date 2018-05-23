package com.lak.core.tools;

import android.content.Context;
import android.support.annotation.StringRes;
import android.widget.Toast;

import com.lak.core.base.BaseApplication;

/**
 * Created by lawrence on 2018/4/16.
 * <p>
 * 吐司提示
 */

public class ToastUtils {
    private final Context mCtx;

    private ToastUtils() {
        mCtx = BaseApplication.appCtx();
        Preconditions.checkNotNull(mCtx, "请先设置app的application！");
    }

    public static ToastUtils instance() {
        return ToastHolder.INSTANCE;
    }

    private static class ToastHolder {
        private static ToastUtils INSTANCE = new ToastUtils();
    }

    /**
     * 短吐司提示
     *
     * @param toastStr 提示内容
     */
    public void show(CharSequence toastStr) {
        if (!nullOrEmpty(toastStr)) {
            Toast.makeText(mCtx, toastStr, Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * 短吐司提示
     *
     * @param resId 提示内容的资源
     */
    public void show(@StringRes int resId) {
        Toast.makeText(mCtx, resId, Toast.LENGTH_SHORT).show();
    }

    /**
     * 短吐司提示
     *
     * @param toastStr 提示内容
     */
    public void showLong(CharSequence toastStr) {
        if (!nullOrEmpty(toastStr)) {
            Toast.makeText(mCtx, toastStr, Toast.LENGTH_LONG).show();
        }
    }

    /**
     * 短吐司提示
     *
     * @param resId 提示内容的资源
     */
    public void showLong(@StringRes int resId) {
        Toast.makeText(mCtx, resId, Toast.LENGTH_LONG).show();
    }

    /**
     * 是否为空or空串
     *
     * @param sequence
     * @return
     */
    private boolean nullOrEmpty(CharSequence sequence) {
        return sequence == null || "".equals(sequence.toString());
    }

}
