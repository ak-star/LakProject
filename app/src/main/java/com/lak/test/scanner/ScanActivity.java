package com.lak.test.scanner;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.lxh.scanz.zxing.scanner.impl.ScanDelegateImpl;

/**
 * Created by lawrence on 2018/6/11.
 * <p>
 * 扫码activity，需要自己申请相机权限
 */

public abstract class ScanActivity extends AppCompatActivity {
    protected Context mCtx = null;
    protected ScanDelegateImpl mProxy = null;

    protected abstract ScanDelegateImpl newInstanceScanDelegate(Context ctx);

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mProxy = newInstanceScanDelegate(mCtx = this);
        mProxy.onCreateView(savedInstanceState);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mProxy.onResume();
    }

    // 恢复预览
    public void resumeCameraPreview() {
        mProxy.resumeCameraPreview();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mProxy.onPause();
    }

    // 停止预览
    public void stopCameraPreview() {
        mProxy.stopCameraPreview();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mProxy.onSaveInstanceState(outState);
    }

}
