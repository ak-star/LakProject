package com.lak.core.base;

import android.content.Context;
import android.content.Intent;
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
    protected abstract void initialDatas(@Nullable Intent intent); // 接收数据
    protected abstract void initialViews(); // 初始化View
    protected abstract void initialListeners(); // 设置监听事件

    // ---------------------------------------------------
    protected Context mCtx;     // 上下文  文法

    // ---------------------------------------------------
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.mCtx = this;
        setContentView(getLayoutId());

        initialDatas(getIntent()); // 接收数据
        initialViews(); // 初始化View
        initialListeners(); // 设置监听事件
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
