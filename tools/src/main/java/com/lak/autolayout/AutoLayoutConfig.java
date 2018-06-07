package com.lak.autolayout;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;

import com.lak.logger.CLog;
import com.lak.logger.CLogConfig;
import com.lak.tools.display.DisplayUtils;

/**
 * Created by lawrence on 2018/5/23.
 *
 * 设计UI配置
 * 方案，只考虑设计的宽度适配，根据设计宽度/屏幕宽度的比例缩放
 */

public class AutoLayoutConfig {
    private static final String KEY_DESIGN_WIDTH = "design_width";      // 设计宽度
    private static final String KEY_OPEN_DEBUG = "open_debug";          // 是否开启日志

    // >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
    private static class AutoLayoutHolder {
        private static final AutoLayoutConfig sInstance = new AutoLayoutConfig();
    }
    public static AutoLayoutConfig getInstance() {
        return AutoLayoutHolder.sInstance;
    }
    private AutoLayoutConfig() { }

    // <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
    private CLogConfig mCLogConfig;
    private static final String TAG = "AutoLayoutConfig";

    private int mScreenWidth;    // 屏幕宽高
    private int mDesignWidth;    // 设计图宽高

    private boolean isInit = false;             // 是否初始化完成

    public void checkParams() {
        if (mDesignWidth <= 0)
            throw new RuntimeException("you must set " + KEY_DESIGN_WIDTH);
    }

    // 设置是否开启日志模式
    public AutoLayoutConfig setDebug(boolean debug) {
        if (debug) {
            if (mCLogConfig == null) {
                mCLogConfig = CLog.init();
            }
            mCLogConfig.setDebug(debug).setShowThreadInfo(false).setTag(TAG);
        } else if (mCLogConfig != null) {
            mCLogConfig.setDebug(debug);
        }
        CLog.d("AutoLayout日志模式已" + (debug ? "开启" : "关闭"));
        return this;
    }

    // 是否开启日志模式
    public boolean isDebug() {
        if (mCLogConfig != null)
            return mCLogConfig.isDebug();
        return false;
    }

    // 设置UI尺寸的宽度
    public AutoLayoutConfig designWidth(int width) {
        if (!isInit)
            this.mDesignWidth = width;
        return this;
    }

    // 屏幕宽度
    public int getScreenWidth() {
        return mScreenWidth;
    }
    // 设计宽度
    public int getDesignWidth() {
        return mDesignWidth;
    }

    // 获取manifest中设置的参数
    private void getMetaData(Context ctx) {
        PackageManager packageManager = ctx.getPackageManager();
        ApplicationInfo applicationInfo;
        try {
            applicationInfo = packageManager.getApplicationInfo(
                    ctx.getPackageName(), PackageManager.GET_META_DATA);
            if (applicationInfo != null && applicationInfo.metaData != null) {
                if (applicationInfo.metaData.containsKey(KEY_DESIGN_WIDTH))
                    mDesignWidth = (int) applicationInfo.metaData.get(KEY_DESIGN_WIDTH);
                if (applicationInfo.metaData.containsKey(KEY_OPEN_DEBUG)) {
                    boolean debug = (boolean) applicationInfo.metaData.get(KEY_OPEN_DEBUG);
                    setDebug(debug);
                }
            }
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        CLog.d("设计宽 Width=" + mDesignWidth);
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
                    DisplayUtils instance = DisplayUtils.instance(appCtx);
                    mScreenWidth = instance.screenW();
                    isInit = true;  // 初始化完成
                }
            }
        }
        CLog.d("屏幕宽 Width=" + mScreenWidth);
    }

}
