package com.lak.scanner.impl;

import android.content.Context;

import com.lak.scanner.widget.ZxingScannerCtrl;

import me.dm7.barcodescanner.core.BarcodeScannerView;
import me.dm7.barcodescanner.core.IViewFinder;
import me.dm7.barcodescanner.zxing.ZXingScannerView;

/**
 * Created by lawrence on 2018/6/11.
 * <p>
 * Zxing二维码扫描代理实现
 */

public abstract class ZxingScanDelegateImpl extends ScanDelegateImpl
        implements ZXingScannerView.ResultHandler {

    public ZxingScanDelegateImpl(Context ctx) {
        super(ctx);
    }

    @Override
    protected BarcodeScannerView setScannerCtrl(Context ctx) {
        return new ZxingScannerCtrl(mCtx) {
            @Override
            protected IViewFinder newViewFinder(Context ctx) {
                return ZxingScanDelegateImpl.this.newViewFinder(ctx);
            }
        };
    }

    @Override
    public void onResume() {
        super.onResume();
        if (mScannerView != null
                && mScannerView instanceof ZXingScannerView) {
            ((ZXingScannerView) mScannerView).setResultHandler(this);
        }
    }

}
