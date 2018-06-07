package com.lak.autolayout.attrs;

import android.view.View;
import android.view.ViewGroup.MarginLayoutParams;

/**
 * Created by lawrence on 2018/6/7.
 * <p>
 * layout_marginRight
 */

public class CAttrMarginRight extends AutoAttr {

    public CAttrMarginRight(int pxVal) {
        super(pxVal);
    }

    @Override
    protected void execute(View view, int val) {
        if (view.getLayoutParams() instanceof MarginLayoutParams) {
            MarginLayoutParams lp = (MarginLayoutParams) view.getLayoutParams();
            lp.rightMargin = val;
        }
    }

    public static CAttrMarginRight generate(int val) {
        return new CAttrMarginRight(val);
    }

}
