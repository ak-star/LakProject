package com.lak.tools.display;

import android.content.Context;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;

/**
 * Created by lawrence on 2018/5/7.
 * <p>
 * 辅助控件设置
 */

public final class CtrlTools {

    /**
     * 通过id查找view
     */
    public static <T extends View> T findViewById(View root, int resId) {
        T result = null;
        if (root != null)
            result = (T) root.findViewById(resId);
        return result;
    }

    // ====================================================================
    private static volatile CtrlTools instance = null;
    private Context mAppCtx = null;

    public static CtrlTools instance(Context ctx) {
        if (instance == null) {
            synchronized (CtrlTools.class) {
                if (instance == null)
                    instance = new CtrlTools(ctx);
            }
        }
        return instance;
    }

    // ====================================================================
    public static final int DEFAULT_DESIGN_W = 720; // 默认设计比例
    private int DISPLAY_W = 0;                  // 屏幕宽度
    private int DISPLAY_H = 0;                  // 屏幕高度
    private int DESIGN_W = DEFAULT_DESIGN_W;    // 设计比例
    private float RATIO = 0;                    // 比例， 屏幕宽度/设计宽度
    private final int TOUCH_SLOP;               // 最小移动单位

    private CtrlTools(Context appCtx) {
        mAppCtx = appCtx.getApplicationContext();
        DISPLAY_W = mAppCtx.getResources().getDisplayMetrics().widthPixels;
        DISPLAY_H = mAppCtx.getResources().getDisplayMetrics().heightPixels;
        setDesign(DEFAULT_DESIGN_W); // 设置默认设计宽度
        TOUCH_SLOP = ViewConfiguration.get(appCtx).getScaledTouchSlop();
    }

    /**
     * 设置 UI设计图，使用宽度。
     *   通过设置此值，可以按比例对控件大小，间隙，字号，进行对应缩放
     *
     * @param width 设计图宽度 【必须width>0有效】
     */
    public CtrlTools setDesign(int width) {
        if (width > 0) {
            DESIGN_W = width;
            RATIO = DISPLAY_W * 1.0f / DESIGN_W;
        }
        return this;
    }

    // 屏幕宽度
    public int displayW() {
        return DISPLAY_W;
    }

    // 屏幕宽度
    public int displayH() {
        return DISPLAY_H;
    }

    // 缩放比例， 屏宽/设计宽
    public float ratio() {
        return RATIO;
    }

    // 当前设计宽度
    public int signWidth() {
        return DESIGN_W;
    }

    /**
     * 根据设计 对控件大小，外间距，进行调整
     *
     * @param w            设计宽度
     * @param h            设计高度
     * @param leftMargin   设计外左间距
     * @param topMargin    设计外上间距
     * @param rightMargin  设计外右间距
     * @param bottomMargin 设计外下间距
     */
    public void setRatioUi(View v, int w, int h, int leftMargin,
                           int topMargin, int rightMargin, int bottomMargin) {
        if (v.getLayoutParams() instanceof ViewGroup.MarginLayoutParams) {
            ViewGroup.MarginLayoutParams layoutParams
                    = (ViewGroup.MarginLayoutParams) v.getLayoutParams();
            layoutParams.width = (int) (w * RATIO);
            layoutParams.height = (int) (h * RATIO);
            layoutParams.leftMargin = (int) (leftMargin * RATIO);
            layoutParams.topMargin = (int) (topMargin * RATIO);
            layoutParams.rightMargin = (int) (rightMargin * RATIO);
            layoutParams.bottomMargin = (int) (bottomMargin * RATIO);
        } else {
            throw new IllegalArgumentException("v 的外层布局，不是MarginLayoutParams类型");
        }
    }

    /**
     * 根据设计 对控件外间距，进行调整
     *
     * @param leftMargin   设计外左间距
     * @param topMargin    设计外上间距
     * @param rightMargin  设计外右间距
     * @param bottomMargin 设计外下间距
     */
    public void setRatioMargin(View v, int leftMargin,
                               int topMargin, int rightMargin, int bottomMargin) {
        if (v.getLayoutParams() instanceof ViewGroup.MarginLayoutParams) {
            ViewGroup.MarginLayoutParams layoutParams
                    = (ViewGroup.MarginLayoutParams) v.getLayoutParams();
            layoutParams.leftMargin = (int) (leftMargin * RATIO);
            layoutParams.topMargin = (int) (topMargin * RATIO);
            layoutParams.rightMargin = (int) (rightMargin * RATIO);
            layoutParams.bottomMargin = (int) (bottomMargin * RATIO);
        } else {
            throw new IllegalArgumentException("v 的外层布局，不是MarginLayoutParams类型");
        }
    }

    /**
     * 根据设计 对控件宽度，外间距，进行调整
     *
     * @param w            设计宽度
     * @param leftMargin   设计外左间距
     * @param topMargin    设计外上间距
     * @param rightMargin  设计外右间距
     * @param bottomMargin 设计外下间距
     */
    public void setRatioW(View v, int w, int leftMargin,
                          int topMargin, int rightMargin, int bottomMargin) {
        if (v.getLayoutParams() instanceof ViewGroup.MarginLayoutParams) {
            ViewGroup.MarginLayoutParams layoutParams
                    = (ViewGroup.MarginLayoutParams) v.getLayoutParams();
            layoutParams.width = (int) (w * RATIO);
            layoutParams.leftMargin = (int) (leftMargin * RATIO);
            layoutParams.topMargin = (int) (topMargin * RATIO);
            layoutParams.rightMargin = (int) (rightMargin * RATIO);
            layoutParams.bottomMargin = (int) (bottomMargin * RATIO);
        } else {
            throw new IllegalArgumentException("v 的外层布局，不是MarginLayoutParams类型");
        }
    }

    /**
     * 根据设计 对控件宽度，外间距，进行调整
     *
     * @param w            设计宽度
     */
    public void setRatioW(View v, int w) {
        if (v.getLayoutParams() instanceof ViewGroup.MarginLayoutParams) {
            ViewGroup.MarginLayoutParams layoutParams
                    = (ViewGroup.MarginLayoutParams) v.getLayoutParams();
            layoutParams.width = (int) (w * RATIO);
        } else {
            throw new IllegalArgumentException("v 的外层布局，不是MarginLayoutParams类型");
        }
    }

    /**
     * 根据设计 对控件高度，外间距，进行调整
     *
     * @param h            设计高度
     * @param leftMargin   设计外左间距
     * @param topMargin    设计外上间距
     * @param rightMargin  设计外右间距
     * @param bottomMargin 设计外下间距
     */
    public void setRatioH(View v, int h, int leftMargin,
                          int topMargin, int rightMargin, int bottomMargin) {
        if (v.getLayoutParams() instanceof ViewGroup.MarginLayoutParams) {
            ViewGroup.MarginLayoutParams layoutParams
                    = (ViewGroup.MarginLayoutParams) v.getLayoutParams();
            layoutParams.height = (int) (h * RATIO);
            layoutParams.leftMargin = (int) (leftMargin * RATIO);
            layoutParams.topMargin = (int) (topMargin * RATIO);
            layoutParams.rightMargin = (int) (rightMargin * RATIO);
            layoutParams.bottomMargin = (int) (bottomMargin * RATIO);
        } else {
            throw new IllegalArgumentException("v 的外层布局，不是MarginLayoutParams类型");
        }
    }

    /**
     * 根据设计 对控件高度，外间距，进行调整
     *
     * @param h            设计高度
     */
    public void setRatioH(View v, int h) {
        if (v.getLayoutParams() instanceof ViewGroup.MarginLayoutParams) {
            ViewGroup.MarginLayoutParams layoutParams
                    = (ViewGroup.MarginLayoutParams) v.getLayoutParams();
            layoutParams.height = (int) (h * RATIO);
        } else {
            throw new IllegalArgumentException("v 的外层布局，不是MarginLayoutParams类型");
        }
    }

    /**
     * 根据设计 对控件大小，外间距，进行调整
     *
     * @param w            设计宽度
     * @param h            设计高度
     */
    public void setRatioUi(View v, int w, int h) {
        if (v.getLayoutParams() instanceof ViewGroup.MarginLayoutParams) {
            ViewGroup.MarginLayoutParams layoutParams
                    = (ViewGroup.MarginLayoutParams) v.getLayoutParams();
            layoutParams.width = (int) (w * RATIO);
            layoutParams.height = (int) (h * RATIO);
        } else {
            throw new IllegalArgumentException("v 的外层布局，不是MarginLayoutParams类型");
        }
    }

    /**
     * 根据设计 内间距，进行调整
     *
     * @param left      设计内左间距
     * @param top       设计内上间距
     * @param right     设计内右间距
     * @param bottom    设计内下间距
     */
    public void setRatioPadding(View v, int left, int top, int right, int bottom) {
        if (v == null) { return; }
        v.setPadding((int) (left * RATIO), (int) (top * RATIO),
                (int) (right * RATIO), (int) (bottom * RATIO));
    }

    /**
     * 将px值转换为dip或dp值，保证尺寸大小不变
     */
    public int px2dp(float pxValue) {
        final float scale = mAppCtx.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    /**
     * 将dip或dp值转换为px值，保证尺寸大小不变
     */
    public int dp2px(float dipValue) {
        final float scale = mAppCtx.getResources().getDisplayMetrics().density;
        return (int) (dipValue * scale + 0.5f);
    }

    /**
     * 将px值转换为sp值，保证文字大小不变
     */
    public int px2sp(float pxValue) {
        final float fontScale = mAppCtx.getResources().getDisplayMetrics().scaledDensity;
        return (int) (pxValue / fontScale + 0.5f);
    }

    /**
     * 将sp值转换为px值，保证文字大小不变
     */
    public int sp2px(float spValue) {
        final float fontScale = mAppCtx.getResources().getDisplayMetrics().scaledDensity;
        return (int) (spValue * fontScale + 0.5f);
    }

    /**
     * 根比例缩放后字体px，计算出对应sp
     */
    public int ratioSp(float fontPx) {
        float px = fontPx * RATIO; // 对应设计的 字体px
        return px2sp(px);
    }

    /**
     * 系统移动距离最小单位
     */
    public int touchSlop() {
        return TOUCH_SLOP;
    }

}
