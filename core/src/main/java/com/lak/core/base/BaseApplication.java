package com.lak.core.base;

import android.app.Application;
import android.content.Context;

import com.lak.core.dagger2.components.AppComponent;
import com.lak.core.dagger2.components.DaggerAppComponent;
import com.lak.core.dagger2.modules.AppModule;
import com.squareup.leakcanary.LeakCanary;

/**
 * Created by lawrence on 2018/4/13.
 * <p>
 * 全局application
 */

public class BaseApplication extends Application {
    private static Context mCtx = null; // 全局Context上下文 文法

    @Override
    public void onCreate() {
        super.onCreate();
        mCtx = getApplicationContext();
        setupLeakCanary(mCtx, this);
    }

    // ------------------- dagger2 ----------------------------------------------------------
    private static AppComponent mAppComponent = null;

    public static synchronized AppComponent getAppComponent() {
        if (mAppComponent == null) {
            mAppComponent = DaggerAppComponent.builder()
                    .appModule(new AppModule(appContext()))
                    .build();
        }
        return mAppComponent;
    }


    // ------------------- get方法 ----------------------------------------------------------

    /**
     * 得到项目的Context
     *
     * @return
     */
    public static Context appContext() {
        return mCtx;
    }


    // ------------------- 内存泄漏检查者 ----------------------------------------------------

    /**
     * 内存泄漏检测
     */
    private void setupLeakCanary(Context ctx, Application application) {
        if (LeakCanary.isInAnalyzerProcess(ctx)) {
            // This process is dedicated to LeakCanary for heap analysis.
            // You should not init your app in this process.
            return;
        }
        LeakCanary.install(application);
    }


}
