package com.lak.autolayout.main;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

import com.lak.autolayout.AutoLayoutConfig;
import com.lak.autolayout.attrs.CAttrHeight;
import com.lak.autolayout.attrs.CAttrMargin;
import com.lak.autolayout.attrs.CAttrMarginBottom;
import com.lak.autolayout.attrs.CAttrMarginLeft;
import com.lak.autolayout.attrs.CAttrMarginRight;
import com.lak.autolayout.attrs.CAttrMarginTop;
import com.lak.autolayout.attrs.CAttrMaxHeight;
import com.lak.autolayout.attrs.CAttrMaxWidth;
import com.lak.autolayout.attrs.CAttrMinHeight;
import com.lak.autolayout.attrs.CAttrMinWidth;
import com.lak.autolayout.attrs.CAttrPadding;
import com.lak.autolayout.attrs.CAttrPaddingBottom;
import com.lak.autolayout.attrs.CAttrPaddingLeft;
import com.lak.autolayout.attrs.CAttrPaddingRight;
import com.lak.autolayout.attrs.CAttrPaddingTop;
import com.lak.autolayout.attrs.CAttrTextSize;
import com.lak.autolayout.attrs.CAttrWidth;
import com.lak.autolayout.attrs.SupportAttrs;

/**
 * Created by lawrence on 2018/5/23.
 * <p>
 * 自动布局辅助
 */

public class AutoLayoutHelper {
    private static AutoLayoutConfig mAutoLayoutConfig;
    private final ViewGroup mHost;

    public AutoLayoutHelper(ViewGroup host) {
        mHost = host;
        if (mAutoLayoutConfig == null) {
            mAutoLayoutConfig = AutoLayoutConfig.getInstance();
            mAutoLayoutConfig.init(host.getContext());
        }
    }

    public void adjustChildren() {
        mAutoLayoutConfig.checkParams();  // 确保已经得到设计宽

        for (int i = 0, n = mHost.getChildCount(); i < n; i++) {
            View view = mHost.getChildAt(i);
            ViewGroup.LayoutParams params = view.getLayoutParams();
            if (params instanceof AutoLayoutParams) {
                AutoLayoutInfo info = ((AutoLayoutParams) params).getAutoLayoutInfo();
                if (info != null) {
                    info.fillAttrs(view);
                }
            }
        }
    }

    public static AutoLayoutInfo getAutoLayoutInfo(Context ctx, AttributeSet attrs) {
        AutoLayoutInfo info = new AutoLayoutInfo();

        // int[] systemAttrs = R.styleable.AutoLayoutSystemAttrs; // 通过xml定义的是自动排序
        // 系统的属性，注意SupportAttrs.ATTRS：顺序！顺序！顺序！
        TypedArray array = ctx.obtainStyledAttributes(attrs, SupportAttrs.ATTRS);
        int n = array.getIndexCount();
        for (int i = 0; i < n; i++) {
            int index = array.getIndex(i);

            // 不是px，跳过
            if (!DiUtils.isPxVal(array.peekValue(index))) continue;

            int pxVal = 0;
            try {
                pxVal = array.getDimensionPixelOffset(index, 0);
            } catch (Exception ignore) {
                continue; //not dimension  不是dimension类型，跳过
            }

            switch (index) {
                case SupportAttrs.INDEX_WIDTH:
                    info.addAttr(new CAttrWidth(pxVal));
                    break;
                case SupportAttrs.INDEX_HEIGHT:
                    info.addAttr(new CAttrHeight(pxVal));
                    break;

                case SupportAttrs.INDEX_MAX_WIDTH:
                    info.addAttr(new CAttrMaxWidth(pxVal));
                    break;
                case SupportAttrs.INDEX_MAX_HEIGHT:
                    info.addAttr(new CAttrMaxHeight(pxVal));
                    break;
                case SupportAttrs.INDEX_MIN_WIDTH:
                    info.addAttr(new CAttrMinWidth(pxVal));
                    break;
                case SupportAttrs.INDEX_MIN_HEIGHT:
                    info.addAttr(new CAttrMinHeight(pxVal));
                    break;

                case SupportAttrs.INDEX_MARGIN:
                    info.addAttr(new CAttrMargin(pxVal));
                    break;
                case SupportAttrs.INDEX_MARGIN_LEFT:
                    info.addAttr(new CAttrMarginLeft(pxVal));
                    break;
                case SupportAttrs.INDEX_MARGIN_TOP:
                    info.addAttr(new CAttrMarginTop(pxVal));
                    break;
                case SupportAttrs.INDEX_MARGIN_RIGHT:
                    info.addAttr(new CAttrMarginRight(pxVal));
                    break;
                case SupportAttrs.INDEX_MARGIN_BOTTOM:
                    info.addAttr(new CAttrMarginBottom(pxVal));
                    break;

                case SupportAttrs.INDEX_PADDING:
                    info.addAttr(new CAttrPadding(pxVal));
                    break;
                case SupportAttrs.INDEX_PADDING_LEFT:
                    info.addAttr(new CAttrPaddingLeft(pxVal));
                    break;
                case SupportAttrs.INDEX_PADDING_TOP:
                    info.addAttr(new CAttrPaddingTop(pxVal));
                    break;
                case SupportAttrs.INDEX_PADDING_RIGHT:
                    info.addAttr(new CAttrPaddingRight(pxVal));
                    break;
                case SupportAttrs.INDEX_PADDING_BOTTOM:
                    info.addAttr(new CAttrPaddingBottom(pxVal));
                    break;

                case SupportAttrs.INDEX_TEXT_SIZE:
                    info.addAttr(new CAttrTextSize(pxVal));
                    break;
            }
        }
        array.recycle(); // 释放资源
        return info;
    }


    public interface AutoLayoutParams {
        AutoLayoutInfo getAutoLayoutInfo();
    }

}
