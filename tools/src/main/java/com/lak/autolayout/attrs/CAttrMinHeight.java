package com.lak.autolayout.attrs;

import android.os.Build;
import android.view.View;

import java.lang.reflect.Field;

/**
 * Created by lawrence on 2018/6/7.
 * <p>
 * minHeight
 */

public class CAttrMinHeight extends AutoAttr {

    public CAttrMinHeight(int pxVal) {
        super(pxVal);
    }

    @Override
    protected void execute(View view, int val) {
        view.setMinimumHeight(val);
    }

    public static CAttrMinHeight generate(int val) {
        return new CAttrMinHeight(val);
    }

    public static int getMinHeight(View view) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            return view.getMinimumHeight();
        } else {
            try {
                Field minHeight = view.getClass().getField("mMinHeight");
                minHeight.setAccessible(true);
                return (int) minHeight.get(view);
            } catch (Exception e) {
            }
        }
        return 0;
    }
}
