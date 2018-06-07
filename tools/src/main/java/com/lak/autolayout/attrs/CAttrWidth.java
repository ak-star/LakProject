package com.lak.autolayout.attrs;

import android.view.View;
import android.view.ViewGroup;

/**
 * Created by lawrence on 2018/6/7.
 * <p>
 * layout_width
 */

public class CAttrWidth extends AutoAttr {

    public CAttrWidth(int pxVal) {
        super(pxVal);
    }

    @Override
    protected void execute(View view, int val) {
        ViewGroup.LayoutParams lp = view.getLayoutParams();
        lp.width = val;
    }

    public static CAttrWidth generate(int val) {
        return new CAttrWidth(val);
    }

}
