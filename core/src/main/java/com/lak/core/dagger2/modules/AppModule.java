package com.lak.core.dagger2.modules;

import android.content.Context;

import com.lak.core.LakCore;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by lawrence on 2018/4/17.
 * <p>
 * 全局 module
 */

@Module
public class AppModule {

    private Context mCtx;

    public AppModule(Context ctx) {
        this.mCtx = ctx;
    }

    @Singleton
    @Provides
    public Context provideAppCtx() {
        return mCtx;
    }

    @Provides
    public LakCore provideCore() {
        return new LakCore();
    }

}
