package com.lak.autolayout.main;

import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.lak.autolayout.attrs.Attrs;
import com.lak.autolayout.attrs.AutoAttr;
import com.lak.autolayout.attrs.CAttrHeight;
import com.lak.autolayout.attrs.CAttrMarginBottom;
import com.lak.autolayout.attrs.CAttrMarginLeft;
import com.lak.autolayout.attrs.CAttrMarginRight;
import com.lak.autolayout.attrs.CAttrMarginTop;
import com.lak.autolayout.attrs.CAttrMaxHeight;
import com.lak.autolayout.attrs.CAttrMaxWidth;
import com.lak.autolayout.attrs.CAttrMinHeight;
import com.lak.autolayout.attrs.CAttrMinWidth;
import com.lak.autolayout.attrs.CAttrPaddingBottom;
import com.lak.autolayout.attrs.CAttrPaddingLeft;
import com.lak.autolayout.attrs.CAttrPaddingRight;
import com.lak.autolayout.attrs.CAttrPaddingTop;
import com.lak.autolayout.attrs.CAttrTextSize;
import com.lak.autolayout.attrs.CAttrWidth;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lawrence on 2018/5/23.
 */

public class AutoLayoutInfo {

    private List<AutoAttr> autoAttrs = new ArrayList<>();

    public void addAttr(AutoAttr autoAttr) {
        autoAttrs.add(autoAttr);
    }

    public void fillAttrs(View view) {
        for (AutoAttr autoAttr : autoAttrs) {
            autoAttr.apply(view);
        }
    }

    public static AutoLayoutInfo getAttrFromView(View view, int attrs) {
        ViewGroup.LayoutParams params = view.getLayoutParams();
        if (params == null) return null;
        AutoLayoutInfo autoLayoutInfo = new AutoLayoutInfo();

        // width & height
        if ((attrs & Attrs.WIDTH) != 0 && params.width > 0) {
            autoLayoutInfo.addAttr(CAttrWidth.generate(params.width));
        }
        if ((attrs & Attrs.HEIGHT) != 0 && params.height > 0) {
            autoLayoutInfo.addAttr(CAttrHeight.generate(params.height));
        }

        //margin
        if (params instanceof ViewGroup.MarginLayoutParams) {
            if ((attrs & Attrs.MARGIN) != 0) {
                autoLayoutInfo.addAttr(CAttrMarginLeft.generate(((ViewGroup.MarginLayoutParams) params).leftMargin));
                autoLayoutInfo.addAttr(CAttrMarginTop.generate(((ViewGroup.MarginLayoutParams) params).topMargin));
                autoLayoutInfo.addAttr(CAttrMarginRight.generate(((ViewGroup.MarginLayoutParams) params).rightMargin));
                autoLayoutInfo.addAttr(CAttrMarginBottom.generate(((ViewGroup.MarginLayoutParams) params).bottomMargin));
            }
            if ((attrs & Attrs.MARGIN_LEFT) != 0) {
                autoLayoutInfo.addAttr(CAttrMarginLeft.generate(((ViewGroup.MarginLayoutParams) params).leftMargin));
            }
            if ((attrs & Attrs.MARGIN_TOP) != 0) {
                autoLayoutInfo.addAttr(CAttrMarginTop.generate(((ViewGroup.MarginLayoutParams) params).topMargin));
            }
            if ((attrs & Attrs.MARGIN_RIGHT) != 0) {
                autoLayoutInfo.addAttr(CAttrMarginRight.generate(((ViewGroup.MarginLayoutParams) params).rightMargin));
            }
            if ((attrs & Attrs.MARGIN_BOTTOM) != 0) {
                autoLayoutInfo.addAttr(CAttrMarginBottom.generate(((ViewGroup.MarginLayoutParams) params).bottomMargin));
            }
        }

        //padding
        if ((attrs & Attrs.PADDING) != 0) {
            autoLayoutInfo.addAttr(CAttrPaddingLeft.generate(view.getPaddingLeft()));
            autoLayoutInfo.addAttr(CAttrPaddingTop.generate(view.getPaddingTop()));
            autoLayoutInfo.addAttr(CAttrPaddingRight.generate(view.getPaddingRight()));
            autoLayoutInfo.addAttr(CAttrPaddingBottom.generate(view.getPaddingBottom()));
        }
        if ((attrs & Attrs.PADDING_LEFT) != 0) {
            autoLayoutInfo.addAttr(CAttrPaddingLeft.generate(view.getPaddingLeft()));
        }
        if ((attrs & Attrs.PADDING_TOP) != 0) {
            autoLayoutInfo.addAttr(CAttrPaddingTop.generate(view.getPaddingTop()));
        }
        if ((attrs & Attrs.PADDING_RIGHT) != 0) {
            autoLayoutInfo.addAttr(CAttrPaddingRight.generate(view.getPaddingRight()));
        }
        if ((attrs & Attrs.PADDING_BOTTOM) != 0) {
            autoLayoutInfo.addAttr(CAttrPaddingBottom.generate(view.getPaddingBottom()));
        }

        //maxWidth, maxHeight, minWidth, minHeight
        if ((attrs & Attrs.MAX_WIDTH) != 0) {
            autoLayoutInfo.addAttr(CAttrMaxWidth.generate(CAttrMaxWidth.getMaxWidth(view)));
        }
        if ((attrs & Attrs.MAX_HEIGHT) != 0) {
            autoLayoutInfo.addAttr(CAttrMaxHeight.generate(CAttrMaxHeight.getMaxHeight(view)));
        }
        if ((attrs & Attrs.MIN_WIDTH) != 0) {
            autoLayoutInfo.addAttr(CAttrMinWidth.generate(CAttrMinWidth.getMinWidth(view)));
        }
        if ((attrs & Attrs.MIN_HEIGHT) != 0) {
            autoLayoutInfo.addAttr(CAttrMinHeight.generate(CAttrMinHeight.getMinHeight(view)));
        }

        //textsizegetAutoLayoutInfo
        if (view instanceof TextView) {
            if ((attrs & Attrs.TEXT_SIZE) != 0) {
                autoLayoutInfo.addAttr(CAttrTextSize.generate((int) ((TextView) view).getTextSize()));
            }
        }
        return autoLayoutInfo;
    }

}
