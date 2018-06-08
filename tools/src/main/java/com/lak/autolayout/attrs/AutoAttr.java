package com.lak.autolayout.attrs;

import android.view.View;

import com.lak.autolayout.main.AutoUtils;

/**
 * Created by lawrence on 2018/6/7.
 * <p>
 * 属性基础
 */

public abstract class AutoAttr {

    protected int pxVal;

    // 设置view大小
    protected abstract void execute(View view, int val);

    public AutoAttr(int pxVal) {
        this.pxVal = pxVal;
    }

    public void apply(View view) {
        // 设计px按比例转换后的大小【单位还是px】
        int percentVal = AutoUtils.getPercentSize(pxVal);
        if (percentVal > 0) {
            percentVal = Math.max(percentVal, 1);
        }
        // 使用转换后的大小，重置view尺寸
        execute(view, percentVal);
    }

}
