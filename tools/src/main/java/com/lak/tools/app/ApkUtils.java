package com.lak.tools.app;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.text.TextUtils;

/**
 * Created by lawrence on 2018/4/24.
 */

public final class ApkUtils {
    private Context mCtx;

    private static volatile ApkUtils INSTANCE = null;

    private ApkUtils(Context ctx) {
        this.mCtx = ctx.getApplicationContext();
        init(mCtx);
    }

    public static ApkUtils instance(Context ctx) {
        if (INSTANCE == null) {
            if (ctx == null)
                throw new NullPointerException("ApkUtils.instance(ctx)’s ctx of argument can not be null.");
            synchronized (ApkUtils.class) {
                if (INSTANCE == null) {
                    INSTANCE = new ApkUtils(ctx);
                }
            }
        }
        return INSTANCE;
    }

    // ---------------------------------------------------------------------------------------------
    private PackageInfo packageInfo = null;

    /**
     * 初始化 设置
     *
     * @param ctx
     */
    private void init(Context ctx) {
    }

    /**
     * 得到PackageInfo
     */
    public PackageInfo getPackageInfo() {
        if (packageInfo == null) {
            PackageManager packageManager = mCtx.getPackageManager();
            try {
                packageInfo = packageManager.getPackageInfo(mCtx.getPackageName(), 0);
            } catch (PackageManager.NameNotFoundException e) {
                e.printStackTrace();
            }
        }
        return packageInfo;
    }

    /**
     * 得到版本号 str
     */
    public String getVersionName() {
        return getPackageInfo().versionName;
    }

    /**
     * 得到版本号 int
     */
    public int getVersionCode() {
        return getPackageInfo().versionCode;
    }



    // --------------- 静态方法 ----------------------------------------------------------------------

    /**
     * 比较版本号的大小,前者大则返回一个正数,后者大返回一个负数,相等则返回0
     * 支持4.1.2, 4.1.23, 4.1.23.rc111这种形式
     *
     * @param version1
     * @param version2
     * @return
     */
    public static int compareVersion(String version1, String version2) throws Exception {
        if (TextUtils.isEmpty(version1) || TextUtils.isEmpty(version2))
            throw new IllegalArgumentException("compareVersion’s arguments can not be null/\"\".");

        int diff = 0; // 返回结果
        String[] vArray1 = version1.split("\\."); //注意此处为正则匹配，不能用"."；
        String[] vArray2 = version2.split("\\."); //注意此处为正则匹配，不能用"."；
        int minLen = Math.min(vArray1.length, vArray2.length);
        for (int i = 0; i < minLen; i++) {
            diff = vArray1[i].length() - vArray2[i].length();
            if (diff != 0) {
                break;
            } // 比较每一节中，字符长度

            diff = vArray1[i].compareTo(vArray2[i]);
            if (diff != 0) {
                break;
            } // 比较每一节中，字符大小
        }

        // 如果已经分出大小，则直接返回，如果未分出大小，则再比较位数，有子版本的为大；
        if (diff == 0) {
            diff = vArray1.length - vArray2.length;
        }

        return diff;
    }

}
