package com.lak.tools.display;

import android.content.Context;
import android.graphics.Point;
import android.os.Build;
import android.view.Display;
import android.view.WindowManager;

/**
 * Created by lawrence on 2018/4/2.
 * <p>
 * 屏幕显示信息
 */

public class DisplayUtils {
    private static Context mCtx = null;
    private static volatile DisplayUtils INSTANCE = null;

    private static void init(Context ctx) {
        if (INSTANCE == null) {
            synchronized (DisplayUtils.class) {
                if (INSTANCE == null)
                    INSTANCE = new DisplayUtils(ctx.getApplicationContext());
            }
        }
    }

    public static DisplayUtils instance(Context ctx) {
        init(ctx);
        return INSTANCE;
    }

    // ---------------------------------------------------------------------------------------------
    private final int mSW, mSH; // 屏幕宽度、高度
    private final int mSBarH;   // 状态栏高度
    private final float mDensityDpi;
    private final float mDensity;
    private final float mScaledDensity;

    /**
     * 构造方法
     *
     * @param ctx 设置全局 上下文文法
     */
    private DisplayUtils(Context ctx) {
        mCtx = ctx;
        WindowManager wm = (WindowManager) mCtx.getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.HONEYCOMB_MR1) {
            Point point = new Point();
            display.getSize(point);
            mSW = point.x;  // 屏幕宽度
            mSH = point.y;  // 屏幕高度
        } else {
            mSW = display.getWidth();  // 屏幕宽度
            mSH = display.getHeight(); // 屏幕高度
        }
        mSBarH = statusBarHeight();    // 状态栏高度

        // dpi 密度
        mDensityDpi = mCtx.getResources().getDisplayMetrics().densityDpi;
        // 密度
        mDensity = mCtx.getResources().getDisplayMetrics().density;
        // 字体转换 密度
        mScaledDensity = mCtx.getResources().getDisplayMetrics().scaledDensity;
    }

    /**
     * 获得状态栏高度
     *
     * @return 状态栏高度
     */
    private int statusBarHeight() {
        int statusBarHeight = 0;
        try {
            Class<?> clazz = Class.forName("com.android.internal.R$dimen");
            Object object = clazz.newInstance();
            int height = Integer.parseInt(clazz.getField("status_bar_height").get(object).toString());
            statusBarHeight = mCtx.getResources().getDimensionPixelSize(height);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return statusBarHeight;
    }

    /**
     * 屏幕宽度
     *
     * @return 屏幕宽度
     */
    public int screenW() {
        return mSW;
    }

    /**
     * 屏幕高度
     *
     * @return 屏幕高度
     */
    public int screenH() {
        return mSH;
    }

    /**
     * 状态栏高度
     *
     * @return 状态栏高度
     */
    public int statusBarH() {
        return mSBarH;
    }

    /**
     * 显示高度
     *
     * @return 显示高度
     */
    public int displayH() {
        return mSH - mSBarH;
    }

    /**
     * dp转化成px
     *
     * @return dp转化成px
     */
    public int dp2px(int dp) {
        return (int) (dp * (mDensityDpi / 160) + 0.5f);
    }

    /**
     * px转化成dp
     *
     * @return px转化成dp
     */
    public float px2dp(float px) {
        return px / mDensity;
    }

    /**
     * 将px值转换为sp值，保证文字大小不变
     *
     * @return 将px值转换为sp值，保证文字大小不变
     */
    public int px2sp(float pxValue) {
        return (int) (pxValue / mScaledDensity + 0.5f);
    }

    /**
     * 将sp值转换为px值，保证文字大小不变
     *
     * @return 将sp值转换为px值，保证文字大小不变
     */
    public int sp2px(float spValue) {
        return (int) (spValue * mScaledDensity + 0.5f);
    }

    /**
     * dip转化成px
     *
     * @return dip转化成px
     */
    public int dip2px(float dp) {
        return (int) (dp * mDensity + 0.5f);
    }

    /**
     * 获得屏幕大小
     *
     * @return 获得屏幕大小
     */
    public Point screenSize() {
        return new Point(mSW, mSH);
    }

    /**
     * 获得显示大小
     *
     * @return 获得显示大小
     */
    public Point displaySize() {
        return new Point(mSW, displayH());
    }

}
