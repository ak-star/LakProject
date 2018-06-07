package com.lak.autolayout.attrs;

import android.view.View;

import java.lang.reflect.Method;

/**
 * Created by lawrence on 2018/6/7.
 * <p>
 * maxWidth
 */

public class CAttrMaxWidth extends AutoAttr {

    public CAttrMaxWidth(int pxVal) {
        super(pxVal);
    }

    @Override
    protected void execute(View view, int val) {
        try {
            Method setMaxWidthMethod = view.getClass().getMethod("setMaxWidth", int.class);
            setMaxWidthMethod.invoke(view, val);
        } catch (Exception ignore) {
        }
    }

    public static CAttrMaxWidth generate(int val) {
        return new CAttrMaxWidth(val);
    }

    public static int getMaxWidth(View view) {
        try {
            Method setMaxWidthMethod = view.getClass().getMethod("getMaxWidth");
            return (int) setMaxWidthMethod.invoke(view);
        } catch (Exception ignore) {
        }
        return 0;
    }
}
