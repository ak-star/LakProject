package com.lak.autolayout.attrs;

/**
 * Created by lawrence on 2018/5/23.
 * <p>
 * 支持的系统属性
 */

public final class SupportAttrs {
    private SupportAttrs() {
    }

    // 支持的系统属性
    public static final int[] ATTRS = new int[]{
            android.R.attr.layout_width,                    // 宽度
            android.R.attr.layout_height,                   // 高度

            android.R.attr.maxWidth,                        // 最大宽度
            android.R.attr.maxHeight,                       // 最大高度
            android.R.attr.minWidth,                        // 最小宽度
            android.R.attr.minHeight,                       // 最小高度

            android.R.attr.layout_margin,                   // 外间距
            android.R.attr.layout_marginLeft,               // 外左间距
            android.R.attr.layout_marginTop,                // 外上间距
            android.R.attr.layout_marginRight,              // 外右间距
            android.R.attr.layout_marginBottom,             // 外下间距

            android.R.attr.padding,                         // 内间距
            android.R.attr.paddingLeft,                     // 内左间距
            android.R.attr.paddingTop,                      // 内上间距
            android.R.attr.paddingRight,                    // 内右间距
            android.R.attr.paddingBottom,                   // 内下间距

            android.R.attr.textSize,                        // 字体大小
    };

    // ========================================================================
    public static final int INDEX_WIDTH = 0;               // 宽度
    public static final int INDEX_HEIGHT = 1;              // 高度

    public static final int INDEX_MAX_WIDTH = 2;           // 最大宽度
    public static final int INDEX_MAX_HEIGHT = 3;          // 最大高度
    public static final int INDEX_MIN_WIDTH = 4;           // 最小宽度
    public static final int INDEX_MIN_HEIGHT = 5;          // 最小高度

    public static final int INDEX_MARGIN = 6;              // 外间距
    public static final int INDEX_MARGIN_LEFT = 7;         // 外左间距
    public static final int INDEX_MARGIN_TOP = 8;          // 外上间距
    public static final int INDEX_MARGIN_RIGHT = 9;        // 外右间距
    public static final int INDEX_MARGIN_BOTTOM = 10;      // 外下间距

    public static final int INDEX_PADDING = 11;            // 内间距
    public static final int INDEX_PADDING_LEFT = 12;       // 内左间距
    public static final int INDEX_PADDING_TOP = 13;        // 内上间距
    public static final int INDEX_PADDING_RIGHT = 14;      // 内右间距
    public static final int INDEX_PADDING_BOTTOM = 15;     // 内下间距

    public static final int INDEX_TEXT_SIZE = 16;          // 字体大小

}
