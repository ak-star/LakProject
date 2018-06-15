package com.lak.scanner.impl;

import android.content.Context;
import android.content.res.Resources;

import com.lak.scanner.widget.ZxingScannerCtrl;

import me.dm7.barcodescanner.core.IViewFinder;
import me.dm7.barcodescanner.core.R;
import me.dm7.barcodescanner.core.ViewFinderView;
import me.dm7.barcodescanner.zxing.ZXingScannerView;

/**
 * Created by lawrence on 2018/6/11.
 * <p>
 * Zxing二维码扫描代理实现
 */

public abstract class ZxingScanDelegateImpl extends ScanDelegateImpl<ZxingScannerCtrl>
        implements ZXingScannerView.ResultHandler {

    // 设置预览器样式
    protected abstract void setFinderView(final IViewFinder finderView);

    public ZxingScanDelegateImpl(Context ctx) {
        super(ctx);
    }

    @Override
    protected ZxingScannerCtrl setScannerCtrl(Context ctx) {
        return new ZxingScannerCtrl(mCtx) {
            @Override
            protected IViewFinder newViewFinder(Context ctx) {
                ViewFinderView viewFinderView = new ViewFinderView(ctx);
                viewFinderView.setLaserEnabled(true);
                viewFinderView.setBorderCornerRounded(false);
                viewFinderView.setBorderCornerRadius(0);
                viewFinderView.setSquareViewFinder(false);
                viewFinderView.setViewFinderOffset(0);

                Resources resources = getResources();
                viewFinderView.setBorderColor(resources.getColor(R.color.viewfinder_border));
                viewFinderView.setLaserColor(resources.getColor(R.color.viewfinder_laser));
                viewFinderView.setMaskColor(resources.getColor(R.color.viewfinder_mask));
                viewFinderView.setBorderStrokeWidth(resources.getInteger(R.integer.viewfinder_border_width));
                viewFinderView.setBorderLineLength(resources.getInteger(R.integer.viewfinder_border_length));

                return viewFinderView;
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
