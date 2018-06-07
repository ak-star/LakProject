package com.lak.autolayout.attrs;

import android.view.View;

/**
 * Created by lawrence on 2018/6/7.
 * <p>
 * paddingBottom
 */

public class CAttrPaddingBottom extends AutoAttr {

    public CAttrPaddingBottom(int pxVal) {
        super(pxVal);
    }

    @Override
    protected void execute(View view, int val) {
        int l = view.getPaddingLeft();
        int t = view.getPaddingTop();
        int r = view.getPaddingRight();
        int b = val;
        view.setPadding(l, t, r, b);
    }

    public static CAttrPaddingBottom generate(int val) {
        return new CAttrPaddingBottom(val);
    }

}
