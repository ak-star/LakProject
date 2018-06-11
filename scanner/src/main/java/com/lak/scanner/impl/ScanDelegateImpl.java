package com.lak.scanner.impl;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.ColorRes;
import android.view.View;

import com.lak.scanner.delegate.ScanDelegate;
import com.lak.scanner.widget.ScanView;

/**
 * Created by lawrence on 2018/6/11.
 * <p>
 * 二维码扫描代理实现
 */

public abstract class ScanDelegateImpl implements ScanDelegate {
    private static final String FLASH_STATE = "FLASH_STATE";
    private static final String AUTO_FOCUS_STATE = "AUTO_FOCUS_STATE";
    private static final String BORDER_COLOR = "BORDER_COLOR";
    private static final String LASER_COLOR = "LASER_COLOR";
    private static final String LASER_ENABLED = "LASER_ENABLED";
    private static final String SQUARE_FINDER = "SQUARE_FINDER";

    private Context mCtx = null;
    private ScanView mScannerView;

    private boolean mFlash;         // 闪光灯
    private boolean mAutoFocus;     // 自动化对焦
    private @ColorRes int mBorderColor;       // 扫描框颜色
    private @ColorRes int mLaserColor;        // 扫描框中激光线颜色
    private boolean mLaserEnabled = true;     // 扫描框中激光线是否显示
    private boolean mSquareViewFinder = true;   // 扫描框是正方形

    public ScanDelegateImpl(Context ctx) {
        mCtx = ctx;
    }

    @Override
    public View onCreateView(Bundle state) {
        mScannerView = new ScanView(mCtx);
        if (state != null) {
            mFlash = state.getBoolean(FLASH_STATE, false);
            mAutoFocus = state.getBoolean(AUTO_FOCUS_STATE, true);
            mBorderColor = state.getInt(BORDER_COLOR, Color.GREEN);
            mLaserColor = state.getInt(LASER_COLOR, Color.GREEN);
            mLaserEnabled = state.getBoolean(LASER_ENABLED, true);
            mSquareViewFinder = state.getBoolean(SQUARE_FINDER, true);
        } else {
            mFlash = false;
            mAutoFocus = true;
            mBorderColor = Color.GREEN;
            mLaserColor = Color.GREEN;
            mLaserEnabled = true;
            mSquareViewFinder = true;
        }
        return mScannerView;
    }

    @Override
    public void onResume() {
        if (mScannerView != null) {
            mScannerView.setResultHandler(this);
            mScannerView.setSquareViewFinder(mSquareViewFinder);
            mScannerView.startCamera();
            mScannerView.setFlash(mFlash);
            mScannerView.setAutoFocus(mAutoFocus);
            mScannerView.setBorderColor(mBorderColor);
            mScannerView.setLaserColor(mLaserColor);
            mScannerView.setLaserEnabled(mLaserEnabled);
        }
    }

    @Override
    public void onPause() {
        if (mScannerView != null) {
            mScannerView.stopCameraPreview();
            mScannerView.stopCamera();
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putBoolean(FLASH_STATE, mFlash);
        outState.putBoolean(AUTO_FOCUS_STATE, mAutoFocus);
        outState.putInt(BORDER_COLOR, mBorderColor);
        outState.putInt(LASER_COLOR, mLaserColor);
        outState.putBoolean(LASER_ENABLED, mLaserEnabled);
        outState.putBoolean(SQUARE_FINDER, mSquareViewFinder);
    }

    public ScanDelegateImpl setFlash(boolean flag) {
        mScannerView.setFlash(mFlash = flag);
        return this;
    }

    public ScanDelegateImpl setAutoFocus(boolean state) {
        mScannerView.setAutoFocus(mAutoFocus = state);
        return this;
    }

    public ScanDelegateImpl setBorderColor(int borderColor) {
        mScannerView.setBorderColor(mBorderColor = borderColor);
        return this;
    }

    public ScanDelegateImpl setLaserColor(int laserColor) {
        mScannerView.setLaserColor(mLaserColor = laserColor);
        return this;
    }

    public ScanDelegateImpl setLaserEnabled(boolean isLaserEnabled) {
        mScannerView.setLaserEnabled(mLaserEnabled = isLaserEnabled);
        return this;
    }

    public ScanDelegateImpl setSquareViewFinder(boolean isSquareViewFinder) {
        mScannerView.setSquareViewFinder(mSquareViewFinder = isSquareViewFinder);
        return this;
    }

}
