package com.lak.scanner.impl;

import android.content.Context;

import com.lak.scanner.widget.ZbarScannerCtrl;

import me.dm7.barcodescanner.core.BarcodeScannerView;
import me.dm7.barcodescanner.core.IViewFinder;
import me.dm7.barcodescanner.zbar.ZBarScannerView;

/**
 * Created by lawrence on 2018/6/11.
 * <p>
 * 二维码扫描代理实现
 */

public abstract class ZbarScanDelegateImpl extends ScanDelegateImpl
        implements ZBarScannerView.ResultHandler {

    public ZbarScanDelegateImpl(Context ctx) {
        super(ctx);
    }

    @Override
    protected BarcodeScannerView setScannerCtrl(Context ctx) {
        return new ZbarScannerCtrl(mCtx) {
            @Override
            protected IViewFinder newViewFinder(Context ctx) {
                return ZbarScanDelegateImpl.this.newViewFinder(ctx);
            }
        };
    }

    @Override
    public void onResume() {
        super.onResume();
        if (mScannerView != null
                && mScannerView instanceof ZBarScannerView) {
            ((ZBarScannerView) mScannerView).setResultHandler(this);
        }
    }

}
