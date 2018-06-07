package com.lak.autolayout.attrs;

import android.view.View;

/**
 * Created by lawrence on 2018/6/7.
 * <p>
 * paddingTop
 */

public class CAttrPaddingTop extends AutoAttr {

    public CAttrPaddingTop(int pxVal) {
        super(pxVal);
    }

    @Override
    protected void execute(View view, int val) {
        int l = view.getPaddingLeft();
        int t = val;
        int r = view.getPaddingRight();
        int b = view.getPaddingBottom();
        view.setPadding(l, t, r, b);
    }

    public static CAttrPaddingTop generate(int val) {
        return new CAttrPaddingTop(val);
    }

}
