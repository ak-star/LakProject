package com.lak.scanner;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.lak.scanner.impl.ScanDelegateImpl;
import com.lak.scanner.widget.LakViewFinderView;

import me.dm7.barcodescanner.zbar.Result;

/**
 * Created by lawrence on 2018/6/11.
 * <p>
 * 扫码activity，需要自己申请相机权限
 */

public abstract class ScanActivity extends AppCompatActivity {
    protected Context mCtx = null;
    protected ScanDelegateImpl mProxy = null;
    protected View mScanView = null; // 二维码扫描控件

    protected abstract void handleResult(Result result);
    protected abstract void setViewFinderView(LakViewFinderView finderView);

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mProxy = new ScanDelegateImpl(mCtx = this) {
            @Override
            public void handleResult(Result result) {
                ScanActivity.this.handleResult(result);
            }
            @Override
            protected void setViewFinderView(LakViewFinderView finderView) {
                ScanActivity.this.setViewFinderView(finderView);
            }
        };
        mScanView = mProxy.onCreateView(savedInstanceState);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mProxy.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mProxy.onPause();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mProxy.onSaveInstanceState(outState);
    }

}
