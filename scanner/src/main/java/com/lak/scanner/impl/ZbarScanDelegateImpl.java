package com.lak.scanner.impl;

import android.content.Context;

import com.lak.scanner.widget.LakViewFinderView;
import com.lak.scanner.widget.ZbarScannerCtrl;

import me.dm7.barcodescanner.core.IViewFinder;
import me.dm7.barcodescanner.zbar.ZBarScannerView;

/**
 * Created by lawrence on 2018/6/11.
 * <p>
 * 二维码扫描代理实现
 */

public abstract class ZbarScanDelegateImpl extends ScanDelegateImpl<ZbarScannerCtrl>
        implements ZBarScannerView.ResultHandler {

    // 设置预览器样式
    protected abstract void setFinderView(final IViewFinder finderView);

    public ZbarScanDelegateImpl(Context ctx) {
        super(ctx);
    }

    @Override
    protected ZbarScannerCtrl setScannerCtrl(Context ctx) {
        return new ZbarScannerCtrl(mCtx) {
            @Override
            protected IViewFinder newViewFinder(Context ctx) {
                LakViewFinderView finderView = new LakViewFinderView(ctx);
                finderView.setBorderColor(mBorderColor);    // 扫描框边框颜色
                finderView.setLaserColor(mLaserColor);      // 扫描框内，扫描线颜色
                setFinderView(finderView);
                return finderView;
            }
        };
    }

    @Override
    public void onResume() {
        if (mScannerView != null)
            mScannerView.setResultHandler(this);
        super.onResume();
    }

    @Override
    public void resumeCameraPreview() {
        if (mScannerView != null)
            mScannerView.resumeCameraPreview(this);
    }
}
