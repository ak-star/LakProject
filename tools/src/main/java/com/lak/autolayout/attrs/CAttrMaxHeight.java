package com.lak.autolayout.attrs;

import android.view.View;

import java.lang.reflect.Method;

/**
 * Created by lawrence on 2018/6/7.
 * <p>
 * maxHeight
 */

public class CAttrMaxHeight extends AutoAttr {

    public CAttrMaxHeight(int pxVal) {
        super(pxVal);
    }

    @Override
    protected void execute(View view, int val) {
        try {
            Method setMaxWidthMethod = view.getClass().getMethod("setMaxHeight", int.class);
            setMaxWidthMethod.invoke(view, val);
        } catch (Exception ignore) {
        }
    }

    public static CAttrMaxHeight generate(int val) {
        return new CAttrMaxHeight(val);
    }

    public static int getMaxHeight(View view) {
        try {
            Method setMaxWidthMethod = view.getClass().getMethod("getMaxHeight");
            return (int) setMaxWidthMethod.invoke(view);
        } catch (Exception ignore) {
        }
        return 0;
    }
}
