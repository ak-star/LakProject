package com.lak.autolayout.attrs;

import android.view.View;
import android.view.ViewGroup.MarginLayoutParams;

/**
 * Created by lawrence on 2018/6/7.
 * <p>
 * layout_marginTop
 */

public class CAttrMarginTop extends AutoAttr {

    public CAttrMarginTop(int pxVal) {
        super(pxVal);
    }

    @Override
    protected void execute(View view, int val) {
        if (view.getLayoutParams() instanceof MarginLayoutParams) {
            MarginLayoutParams lp = (MarginLayoutParams) view.getLayoutParams();
            lp.topMargin = val;
        }
    }

    public static CAttrMarginTop generate(int val) {
        return new CAttrMarginTop(val);
    }

}
