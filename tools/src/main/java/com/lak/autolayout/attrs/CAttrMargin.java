package com.lak.autolayout.attrs;

import android.view.View;
import android.view.ViewGroup.MarginLayoutParams;

/**
 * Created by lawrence on 2018/6/7.
 * <p>
 * layout_margin
 */

public class CAttrMargin extends AutoAttr {

    public CAttrMargin(int pxVal) {
        super(pxVal);
    }

    @Override
    protected void execute(View view, int val) {
        if (view.getLayoutParams() instanceof MarginLayoutParams) {
            MarginLayoutParams lp = (MarginLayoutParams) view.getLayoutParams();
            lp.leftMargin = lp.rightMargin = lp.topMargin = lp.bottomMargin = val;
        }
    }

}
