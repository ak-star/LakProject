package com.lak.autolayout.attrs;

import android.view.View;
import android.view.ViewGroup;

/**
 * Created by lawrence on 2018/6/7.
 * <p>
 * layout_height
 */

public class CAttrHeight extends AutoAttr {

    public CAttrHeight(int pxVal) {
        super(pxVal);
    }

    @Override
    protected void execute(View view, int val) {
        ViewGroup.LayoutParams lp = view.getLayoutParams();
        lp.height = val;
    }

    public static CAttrHeight generate(int val) {
        return new CAttrHeight(val);
    }

}
