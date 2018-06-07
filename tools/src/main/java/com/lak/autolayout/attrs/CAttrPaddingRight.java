package com.lak.autolayout.attrs;

import android.view.View;

/**
 * Created by lawrence on 2018/6/7.
 * <p>
 * paddingRight
 */

public class CAttrPaddingRight extends AutoAttr {

    public CAttrPaddingRight(int pxVal) {
        super(pxVal);
    }

    @Override
    protected void execute(View view, int val) {
        int l = view.getPaddingLeft();
        int t = view.getPaddingTop();
        int r = val;
        int b = view.getPaddingBottom();
        view.setPadding(l, t, r, b);
    }

    public static CAttrPaddingRight generate(int val) {
        return new CAttrPaddingRight(val);
    }

}
