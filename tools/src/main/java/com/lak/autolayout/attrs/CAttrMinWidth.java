package com.lak.autolayout.attrs;

import android.os.Build;
import android.view.View;

import java.lang.reflect.Field;

/**
 * Created by lawrence on 2018/6/7.
 * <p>
 * minWidth
 */

public class CAttrMinWidth extends AutoAttr {

    public CAttrMinWidth(int pxVal) {
        super(pxVal);
    }

    @Override
    protected void execute(View view, int val) {
        view.setMinimumWidth(val);
    }

    public static CAttrMinWidth generate(int val) {
        return new CAttrMinWidth(val);
    }

    public static int getMinWidth(View view) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN)
            return view.getMinimumWidth();
        try {
            Field minWidth = view.getClass().getField("mMinWidth");
            minWidth.setAccessible(true);
            return (int) minWidth.get(view);
        } catch (Exception ignore) {
        }
        return 0;
    }
}
