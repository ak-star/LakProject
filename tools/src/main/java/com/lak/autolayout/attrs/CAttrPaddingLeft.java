package com.lak.autolayout.attrs;

import android.view.View;

/**
 * Created by lawrence on 2018/6/7.
 * <p>
 * paddingLeft
 */

public class CAttrPaddingLeft extends AutoAttr {

    public CAttrPaddingLeft(int pxVal) {
        super(pxVal);
    }

    @Override
    protected void execute(View view, int val) {
        int l = val;
        int t = view.getPaddingTop();
        int r = view.getPaddingRight();
        int b = view.getPaddingBottom();
        view.setPadding(l, t, r, b);
    }

    public static CAttrPaddingLeft generate(int val) {
        return new CAttrPaddingLeft(val);
    }

}
