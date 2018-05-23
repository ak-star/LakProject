package com.lak.autolayout.main;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

import com.lak.autolayout.AutoLayoutConfig;
import com.lak.autolayout.attrs.SupportAttrs;
import com.lak.tools.R;

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

    public static AutoLayoutInfo getAutoLayoutInfo(Context ctx, AttributeSet attrs) {
        AutoLayoutInfo info = new AutoLayoutInfo();

        // 自定义的属性
        TypedArray a = ctx.obtainStyledAttributes(attrs, R.styleable.AutoLayout_Layout);
        int baseWidth = a.getInt(R.styleable.AutoLayout_Layout_layout_auto_basewidth, 0);
        int baseHeight = a.getInt(R.styleable.AutoLayout_Layout_layout_auto_baseheight, 0);
        a.recycle();

        // 系统的属性
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
                    info.addAttr(new WidthAttr(pxVal, baseWidth, baseHeight));
                    break;
                case SupportAttrs.INDEX_HEIGHT:
                    info.addAttr(new HeightAttr(pxVal, baseWidth, baseHeight));
                    break;

                case SupportAttrs.INDEX_MAX_WIDTH:
                    info.addAttr(new MaxWidthAttr(pxVal, baseWidth, baseHeight));
                    break;
                case SupportAttrs.INDEX_MAX_HEIGHT:
                    info.addAttr(new MaxHeightAttr(pxVal, baseWidth, baseHeight));
                    break;
                case SupportAttrs.INDEX_MIN_WIDTH:
                    info.addAttr(new MinWidthAttr(pxVal, baseWidth, baseHeight));
                    break;
                case SupportAttrs.INDEX_MIN_HEIGHT:
                    info.addAttr(new MinHeightAttr(pxVal, baseWidth, baseHeight));
                    break;

                case SupportAttrs.INDEX_MARGIN:
                    info.addAttr(new MarginAttr(pxVal, baseWidth, baseHeight));
                    break;
                case SupportAttrs.INDEX_MARGIN_LEFT:
                    info.addAttr(new MarginLeftAttr(pxVal, baseWidth, baseHeight));
                    break;
                case SupportAttrs.INDEX_MARGIN_TOP:
                    info.addAttr(new MarginTopAttr(pxVal, baseWidth, baseHeight));
                    break;
                case SupportAttrs.INDEX_MARGIN_RIGHT:
                    info.addAttr(new MarginRightAttr(pxVal, baseWidth, baseHeight));
                    break;
                case SupportAttrs.INDEX_MARGIN_BOTTOM:
                    info.addAttr(new MarginBottomAttr(pxVal, baseWidth, baseHeight));
                    break;

                case SupportAttrs.INDEX_PADDING:
                    info.addAttr(new PaddingAttr(pxVal, baseWidth, baseHeight));
                    break;
                case SupportAttrs.INDEX_PADDING_LEFT:
                    info.addAttr(new PaddingLeftAttr(pxVal, baseWidth, baseHeight));
                    break;
                case SupportAttrs.INDEX_PADDING_TOP:
                    info.addAttr(new PaddingTopAttr(pxVal, baseWidth, baseHeight));
                    break;
                case SupportAttrs.INDEX_PADDING_RIGHT:
                    info.addAttr(new PaddingRightAttr(pxVal, baseWidth, baseHeight));
                    break;
                case SupportAttrs.INDEX_PADDING_BOTTOM:
                    info.addAttr(new PaddingBottomAttr(pxVal, baseWidth, baseHeight));
                    break;

                case SupportAttrs.INDEX_TEXT_SIZE:
                    info.addAttr(new TextSizeAttr(pxVal, baseWidth, baseHeight));
                    break;
            }
        }
        array.recycle(); // 释放资源
        AutoLayoutConfig.d("getAutoLayoutInfo " + info.toString());
        return info;
    }

    public void adjustChildren() {
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


    public interface AutoLayoutParams {
        AutoLayoutInfo getAutoLayoutInfo();
    }

}
