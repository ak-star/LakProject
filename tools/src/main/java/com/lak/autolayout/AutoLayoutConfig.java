package com.lak.autolayout;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.util.Log;

import com.lak.autolayout.main.ScUtils;
import com.lak.tools.display.DisplayUtils;

/**
 * Created by lawrence on 2018/5/23.
 *
 * 设计UI配置，有两种方案提供
 * 方案一，只考虑设计的宽度适配，根据设计宽度/屏幕宽度的比例缩放
 * 方案二，按设计的宽度和高度适配，即：
 *          宽度缩放比例为 设计宽度/屏幕宽度
 *          高度缩放比例为 设计高度/屏幕高度
 */

public class AutoLayoutConfig {
    // 适配方案 0-方案一，宽度适配，  1-方案二，宽度和高度一起适配
    private static final String KEY_DESIGN_TYPE = "fit_type";           // 适配方案
    private static final String KEY_DESIGN_WIDTH = "design_width";      // 设计宽度
    private static final String KEY_DESIGN_HEIGHT = "design_height";    // 设计高度

    // >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
    private static class AutoLayoutHolder {
        private static final AutoLayoutConfig sInstance = new AutoLayoutConfig();
    }
    public static AutoLayoutConfig getInstance() {
        return AutoLayoutHolder.sInstance;
    }
    private AutoLayoutConfig() { }

    // <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
    private boolean debug = false; // 是否输出日志
    private static final String TAG = "AutoLayoutConfig";
    // 适配方案 0-方案一，宽度适配，  1-方案二，宽度和高度一起适配
    private int mFitType = 0;   // 适配方案
    private int mScreenWidth, mScreenHeight;    // 屏幕宽高
    private int mDesignWidth, mDesignHeight;    // 设计图宽高

    private boolean fitStatusBar = false;       // 显示区域，是否包含状态栏
    private boolean isInit = false;             // 是否初始化完成

    private void checkParams() {
        switch (mFitType) {
            case 0: // 方案一，宽度适配
                if (mDesignWidth <= 0)
                    throw new RuntimeException("you must set " + KEY_DESIGN_WIDTH);
                break;
            case 1: // 方案二，宽度和高度一起适配
                if (mDesignWidth <= 0 || mDesignHeight <= 0)
                    throw new RuntimeException("you must set "
                            + KEY_DESIGN_TYPE + " and " + KEY_DESIGN_HEIGHT );
                break;
            default:
                throw new RuntimeException("you must set " + KEY_DESIGN_TYPE
                            + ". The value only be used 0 or 1.");
        }
    }

    public AutoLayoutConfig setDebug(boolean debug) {
        this.debug = debug;
        return this;
    }
    public boolean debug() {
        return this.debug;
    }

    // 显示区域包含状态栏
    public AutoLayoutConfig fitStatusBar() {
        if (!isInit)
            this.fitStatusBar = true;
        return this;
    }

    // 设置UI适配方案, 0-方案一，1-方案二
    public AutoLayoutConfig fitType(int type) {
        if (!isInit)
            this.mFitType = type;
        return this;
    }
    // 设置UI尺寸的宽度
    public AutoLayoutConfig designWidth(int width) {
        if (!isInit)
            this.mDesignWidth = width;
        return this;
    }
    // 设置UI尺寸的高度
    public AutoLayoutConfig designHeight(int height) {
        if (!isInit)
            this.mDesignHeight = height;
        return this;
    }

    // 屏幕宽度
    public int getScreenWidth() {
        return mScreenWidth;
    }
    // 屏幕高度
    public int getScreenHeight() {
        return mScreenHeight;
    }
    // 设计宽度
    public int getDesignWidth() {
        return mDesignWidth;
    }
    // 设计高度
    public int getDesignHeight() {
        return mDesignHeight;
    }

    // 获取manifest中设置的参数
    private void getMetaData(Context ctx) {
        PackageManager packageManager = ctx.getPackageManager();
        ApplicationInfo applicationInfo;
        try {
            applicationInfo = packageManager.getApplicationInfo(
                    ctx.getPackageName(), PackageManager.GET_META_DATA);
            if (applicationInfo != null && applicationInfo.metaData != null) {
                if (applicationInfo.metaData.containsKey(KEY_DESIGN_TYPE))
                    mFitType = (int) applicationInfo.metaData.get(KEY_DESIGN_TYPE);
                if (applicationInfo.metaData.containsKey(KEY_DESIGN_WIDTH))
                    mDesignWidth = (int) applicationInfo.metaData.get(KEY_DESIGN_WIDTH);
                if (applicationInfo.metaData.containsKey(KEY_DESIGN_HEIGHT))
                    mDesignHeight = (int) applicationInfo.metaData.get(KEY_DESIGN_HEIGHT);
            }
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        d("fit_type =" + mFitType + " designWidth =" + mDesignWidth + " , designHeight = " + mDesignHeight);
    }

    // >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
    // 只能初始化一次，多次调用无效
    public void init(Context ctx) {
        if (!isInit) {
            synchronized (AutoLayoutConfig.class) {
                if (!isInit) {
                    Context appCtx = ctx.getApplicationContext();
                    getMetaData(appCtx);
                    checkParams();  // 验证参数是否完整
                    switch (mFitType) {
                        case 0: // 方案一
                            DisplayUtils instance = DisplayUtils.instance(appCtx);
                            mScreenWidth = instance.screenW();
                            mScreenHeight = instance.screenH();
                            if (!fitStatusBar) // 不包含状态栏
                                mScreenHeight = instance.displaySize().y;
                            break;

                        case 1: // 方案二
                            int[] screenSize = ScUtils.getScreenSize(appCtx, fitStatusBar);
                            mScreenWidth = screenSize[0];
                            mScreenHeight = screenSize[1];
                            break;
                    }
                    isInit = true;  // 初始化完成
                }
            }
        }
        d("fit_type =" + mFitType + " screenWidth =" + mScreenWidth + " , screenHeight = " + mScreenHeight);
    }

    // <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
    public static void d(String msg) {
        d(TAG, msg);
    }
    public static void d(String tag, String msg) {
        if (getInstance().debug()) { Log.d(tag, msg); }
    }

}
