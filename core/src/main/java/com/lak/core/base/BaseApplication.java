package com.lak.core.base;

import android.app.Application;
import android.content.Context;

import com.squareup.leakcanary.LeakCanary;

/**
 * Created by lawrence on 2018/4/13.
 */

public class BaseApplication extends Application {
    private Context mCtx = null; // 全局Context上下文 文法

    @Override
    public void onCreate() {
        super.onCreate();
        mCtx = getApplicationContext();
        setupLeakCanary(mCtx, this);
    }

    // ------------------- 内存泄漏检查者 ----------------------------------------------------
    /**
     * 内存泄漏检测
     */
    private void setupLeakCanary(Context ctx, Application application) {
        if (LeakCanary.isInAnalyzerProcess(ctx)) {
            // This process is dedicated to LeakCanary for heap analysis.
            // You should not init your app in this process.
            return ;
        }
        LeakCanary.install(application);
    }


}
