package com.lak.autolayout.attrs;

import android.util.TypedValue;
import android.view.View;
import android.widget.TextView;

/**
 * Created by lawrence on 2018/6/7.
 * <p>
 * textSize
 */

public class CAttrTextSize extends AutoAttr {

    public CAttrTextSize(int pxVal) {
        super(pxVal);
    }

    @Override
    protected void execute(View view, int val) {
        if (view instanceof TextView) {
            ((TextView) view).setIncludeFontPadding(false);
            ((TextView) view).setTextSize(TypedValue.COMPLEX_UNIT_PX, val);
        }
    }

    public static CAttrTextSize generate(int val) {
        return new CAttrTextSize(val);
    }

}
