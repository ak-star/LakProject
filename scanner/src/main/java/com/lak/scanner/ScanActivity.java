package com.lak.scanner;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.lak.scanner.impl.ScanDelegateImpl;

import me.dm7.barcodescanner.zbar.Result;

/**
 * Created by lawrence on 2018/6/11.
 * <p>
 * 扫码activity，需要自己申请相机权限
 */

public abstract class ScanActivity extends AppCompatActivity {

    protected ScanDelegateImpl mProxy = null;
    protected View mScanView = null; // 二维码扫描控件

    protected abstract void handleResult(Result result);

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mProxy = new ScanDelegateImpl(this) {
            @Override
            public void handleResult(Result result) {
                ScanActivity.this.handleResult(result);
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
