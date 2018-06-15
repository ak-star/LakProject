package com.lak.scanner.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.util.AttributeSet;

import com.lak.scanner.R;
import com.lak.utils.ReflectUtils;

import me.dm7.barcodescanner.core.IViewFinder;
import me.dm7.barcodescanner.zxing.ZXingScannerView;

/**
 * Created by lawrence on 2018/6/14.
 * <p>
 * 二维码扫描控件
 *
 * @link https://github.com/dm77/barcodescanner
 */

public class ZxingScannerCtrl extends ZXingScannerView {
    private Context mCtx = null;

    private int mBorderColor = Color.GREEN;     // 扫描框颜色
    private int mLaserColor = Color.GREEN;      // 扫描线颜色

    private IViewFinder mFinderView = null;


    public ZxingScannerCtrl(Context context) {
        this(context, null);
    }

    public ZxingScannerCtrl(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        mCtx = context;
        initialAttrs(attributeSet);
    }

    private void initialAttrs(AttributeSet attributeSet) {
        TypedArray typedArray = mCtx.obtainStyledAttributes(attributeSet, R.styleable.ZbarScannerCtrl);
        mBorderColor = typedArray.getColor(R.styleable.ZbarScannerCtrl_scanBorderColor, Color.GREEN);
        mLaserColor = typedArray.getColor(R.styleable.ZbarScannerCtrl_scanLaserColor, Color.GREEN);
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
    public void resumeCameraPreview(ResultHandler resultHandler) {
        startLaser();
        super.resumeCameraPreview(resultHandler);
    }

    @Override
    public void stopCameraPreview() {
        stopLaser();
        super.stopCameraPreview();
    }

    private IViewFinder getViewFinder() {
        if (mFinderView == null) {
            synchronized (ZxingScannerCtrl.class) {
                if (mFinderView == null)
                    mFinderView = ReflectUtils.getDeclaredField(this, "mViewFinderView");
            }
        }
        return mFinderView;
    }

    // 停止扫描线移动
    private void stopLaser() {
        if (getViewFinder() != null
                && getViewFinder() instanceof LakViewFinderView) {
            ((LakViewFinderView) getViewFinder()).stopLaser(true);
        }
    }

    // 开始扫描线移动
    private void startLaser() {
        if (getViewFinder() != null
                && getViewFinder() instanceof LakViewFinderView) {
            ((LakViewFinderView) getViewFinder()).stopLaser(false);
        }
    }

}
