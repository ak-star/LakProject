package com.lak.scanner.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.util.AttributeSet;

import com.lak.scanner.R;

import me.dm7.barcodescanner.core.IViewFinder;
import me.dm7.barcodescanner.zbar.ZBarScannerView;

/**
 * Created by lawrence on 2018/6/11.
 * <p>
 * 二维码扫描控件
 *
 * @link https://github.com/dm77/barcodescanner
 */

public class ScanView extends ZBarScannerView {
    private Context mCtx = null;

    private int mBorderColor = Color.GREEN;     // 扫描框颜色
    private int mLaserColor = Color.GREEN;      // 扫描线颜色

    private IViewFinder mFinderView = null;

    public ScanView(Context context) {
        this(context, null);
    }

    public ScanView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        mCtx = context;
        initialAttrs(attributeSet);
    }

    private void initialAttrs(AttributeSet attributeSet) {
        TypedArray typedArray = mCtx.obtainStyledAttributes(attributeSet, R.styleable.ScanView);
        mBorderColor = typedArray.getColor(R.styleable.ScanView_scanBorderColor, Color.GREEN);
        mLaserColor = typedArray.getColor(R.styleable.ScanView_scanLaserColor, Color.GREEN);
        typedArray.recycle();
    }

    // 扫描框
    @Override
    protected IViewFinder createViewFinderView(Context context) {
        return mFinderView = newViewFinder(context);
    }

    @Override
    public void setBorderColor(int borderColor) {
        super.setBorderColor(mBorderColor = borderColor);
    }

    @Override
    public void setLaserColor(int laserColor) {
        super.setLaserColor(mLaserColor = laserColor);
    }

    protected IViewFinder newViewFinder(Context ctx) {
        IViewFinder viewFinder = new LakViewFinderView(ctx);
        viewFinder.setBorderColor(mBorderColor);    // 扫描框边框颜色
        viewFinder.setLaserColor(mLaserColor);      // 扫描框内，扫描线颜色
        return viewFinder;
    }

    @Override
    public void startCamera() {
        startLaser();
        super.startCamera();
    }

    @Override
    public void startCamera(int cameraId) {
        startLaser();
        super.startCamera(cameraId);
    }

    @Override
    public void stopCameraPreview() {
        stopLaser();
        super.stopCameraPreview();
    }

    @Override
    public void stopCamera() {
        stopLaser();
        super.stopCamera();
    }

    // 停止扫描线移动
    private void stopLaser() {
        if (mFinderView != null
                && mFinderView instanceof LakViewFinderView) {
            ((LakViewFinderView) mFinderView).stopLaser(true);
        }
    }

    // 开始扫描线移动
    private void startLaser() {
        if (mFinderView != null
                && mFinderView instanceof LakViewFinderView) {
            ((LakViewFinderView) mFinderView).stopLaser(false);
        }
    }

}
