package com.lak.autolayout.attrs;

import android.view.View;
import android.view.ViewGroup.MarginLayoutParams;

/**
 * Created by lawrence on 2018/6/7.
 * <p>
 * layout_marginLeft
 */

public class CAttrMarginLeft extends AutoAttr {

    public CAttrMarginLeft(int pxVal) {
        super(pxVal);
    }

    @Override
    protected void execute(View view, int val) {
        if (view.getLayoutParams() instanceof MarginLayoutParams) {
            MarginLayoutParams lp = (MarginLayoutParams) view.getLayoutParams();
            lp.leftMargin = val;
        }
    }

    public static CAttrMarginLeft generate(int val) {
        return new CAttrMarginLeft(val);
    }

}
