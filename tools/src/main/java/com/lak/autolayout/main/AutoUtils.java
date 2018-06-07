package com.lak.autolayout.main;

import android.view.View;

import com.lak.autolayout.AutoLayoutConfig;
import com.lak.autolayout.attrs.Attrs;

/**
 * Created by lawrence on 2018/6/7.
 * <p>
 * 比例转换器
 */

public final class AutoUtils {

    private AutoUtils() {
    }

    /**
     * 得到按比例转换后的px大小
     *
     * @param pxVal 设计px大小
     * @return 转换后的px大小
     */
    public static int getPercentSize(int pxVal) {
        final int screenWidth = AutoLayoutConfig.getInstance().getScreenWidth();
        final int designWidth = AutoLayoutConfig.getInstance().getDesignWidth();

        int result = pxVal * screenWidth;
        if (result % designWidth == 0)
            return result / designWidth;
        else
            return result / designWidth + 1;
    }

    /**
     * 会直接将view的LayoutParams上设置的width，height直接进行百分比处理
     *
     * @param view
     */
    public static void auto(View view) {
        autoSize(view);
        autoPadding(view);
        autoMargin(view);
        autoTextSize(view);
    }

    /**
     * @param view
     * @param attrs #Attrs.WIDTH|Attrs.HEIGHT
     */
    public static void auto(View view, int attrs) {
        AutoLayoutInfo autoLayoutInfo = AutoLayoutInfo.getAttrFromView(view, attrs);
        if (autoLayoutInfo != null)
            autoLayoutInfo.fillAttrs(view);
    }

    public static void autoTextSize(View view) {
        auto(view, Attrs.TEXT_SIZE);
    }

    public static void autoMargin(View view) {
        auto(view, Attrs.MARGIN);
    }

    public static void autoPadding(View view) {
        auto(view, Attrs.PADDING);
    }

    public static void autoSize(View view) {
        auto(view, Attrs.WIDTH | Attrs.HEIGHT);
    }

}
