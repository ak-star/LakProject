package com.lak.autolayout.attrs;

/**
 * Created by lawrence on 2018/5/23.
 * <p>
 * 支持的系统属性，注意：顺序！顺序！顺序！
 */

public final class SupportAttrs {
    private SupportAttrs() {
    }

    // 支持的系统属性，注意：顺序！顺序！顺序！
    public static final int[] ATTRS = new int[]{
            android.R.attr.textSize,                        // 16842901 字体大小

            android.R.attr.padding,                         // 16842965 内间距
            android.R.attr.paddingLeft,                     // 16842966 内左间距
            android.R.attr.paddingTop,                      // 16842967 内上间距
            android.R.attr.paddingRight,                    // 16842968 内右间距
            android.R.attr.paddingBottom,                   // 16842969 内下间距

            android.R.attr.layout_width,                    // 16842996 宽度
            android.R.attr.layout_height,                   // 16842997 高度

            android.R.attr.layout_margin,                   // 16842998 外间距
            android.R.attr.layout_marginLeft,               // 16842999 外左间距
            android.R.attr.layout_marginTop,                // 16843000 外上间距
            android.R.attr.layout_marginRight,              // 16843001 外右间距
            android.R.attr.layout_marginBottom,             // 16843002 外下间距

            android.R.attr.maxWidth,                        // 16843039 最大宽度
            android.R.attr.maxHeight,                       // 16843040 最大高度
            android.R.attr.minWidth,                        // 16843071 最小宽度
            android.R.attr.minHeight,                       // 16843072 最小高度
    };

    // ========================================================================
    public static final int INDEX_TEXT_SIZE = 0;          // 字体大小

    public static final int INDEX_PADDING = 1;            // 内间距
    public static final int INDEX_PADDING_LEFT = 2;       // 内左间距
    public static final int INDEX_PADDING_TOP = 3;        // 内上间距
    public static final int INDEX_PADDING_RIGHT = 4;      // 内右间距
    public static final int INDEX_PADDING_BOTTOM = 5;     // 内下间距

    public static final int INDEX_WIDTH = 6;               // 宽度
    public static final int INDEX_HEIGHT = 7;              // 高度

    public static final int INDEX_MARGIN = 8;              // 外间距
    public static final int INDEX_MARGIN_LEFT = 9;         // 外左间距
    public static final int INDEX_MARGIN_TOP = 10;          // 外上间距
    public static final int INDEX_MARGIN_RIGHT = 11;        // 外右间距
    public static final int INDEX_MARGIN_BOTTOM = 12;      // 外下间距

    public static final int INDEX_MAX_WIDTH = 13;           // 最大宽度
    public static final int INDEX_MAX_HEIGHT = 14;          // 最大高度
    public static final int INDEX_MIN_WIDTH = 15;           // 最小宽度
    public static final int INDEX_MIN_HEIGHT = 16;          // 最小高度

}
