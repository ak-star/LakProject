package com.lak.autolayout.attrs;

import android.view.View;

/**
 * Created by lawrence on 2018/6/7.
 * <p>
 * padding
 */

public class CAttrPadding extends AutoAttr {

    public CAttrPadding(int pxVal) {
        super(pxVal);
    }

    @Override
    protected void execute(View view, int val) {
        view.setPadding(val, val, val, val);
    }

}
