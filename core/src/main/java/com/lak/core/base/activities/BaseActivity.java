package com.lak.core.base.activities;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.lak.core.manager.ActivitiesManager;
import com.lak.tools.keyboard.KeyboardUtils;

/**
 * Created by lawrence on 2018/4/3.
 * <p>
 * 基础Activity，基础Mvp
 */

public abstract class BaseActivity extends AppCompatActivity {
    // ---------------------------------------------------
    protected abstract @LayoutRes int getLayoutId();

    // ---------------------------------------------------
    protected final String sClassName = this.getClass().getSimpleName();
    protected Context mCtx;     // 上下文  文法

    // ---------------------------------------------------
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.mCtx = this;
        setContentView(getLayoutId());
        ActivitiesManager.instance().addActivity(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        ActivitiesManager.instance().setRunningActivity(this);
    }

    @Override
    protected void onPause() {
        ActivitiesManager.instance().setRunningActivity(null);
        super.onPause();
        KeyboardUtils.hideInput(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivitiesManager.instance().removeActivity(this);
    }

}
