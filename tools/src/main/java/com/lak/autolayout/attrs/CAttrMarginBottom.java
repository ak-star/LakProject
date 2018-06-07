package com.lak.autolayout.attrs;

import android.view.View;
import android.view.ViewGroup.MarginLayoutParams;

/**
 * Created by lawrence on 2018/6/7.
 * <p>
 * layout_marginBottom
 */

public class CAttrMarginBottom extends AutoAttr {

    public CAttrMarginBottom(int pxVal) {
        super(pxVal);
    }

    @Override
    protected void execute(View view, int val) {
        if (view.getLayoutParams() instanceof MarginLayoutParams) {
            MarginLayoutParams lp = (MarginLayoutParams) view.getLayoutParams();
            lp.bottomMargin = val;
        }
    }

    public static CAttrMarginBottom generate(int val) {
        return new CAttrMarginBottom(val);
    }

}
