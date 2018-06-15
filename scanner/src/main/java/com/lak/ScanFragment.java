package com.lak;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lak.scanner.impl.ScanDelegateImpl;

/**
 * Created by lawrence on 2018/6/11.
 * <p>
 * 扫码fragment，需要自己申请相机权限
 */

public abstract class ScanFragment extends Fragment {
    protected Context mCtx = null;
    protected ScanDelegateImpl mProxy = null;

    protected abstract ScanDelegateImpl newInstanceScanDelegate(Context ctx);

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mCtx = context;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mCtx = activity;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        scanDelegateImpl(savedInstanceState);
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    protected void scanDelegateImpl(@Nullable Bundle savedInstanceState) {
        mProxy = newInstanceScanDelegate(mCtx);
        mProxy.onCreateView(savedInstanceState);
    }

    @Override
    public void onResume() {
        super.onResume();
        mProxy.onResume();
    }

    // 恢复预览
    public void resumeCameraPreview() {
        mProxy.resumeCameraPreview();
    }

    @Override
    public void onPause() {
        super.onPause();
        mProxy.onPause();
    }

    // 停止预览
    public void stopCameraPreview() {
        mProxy.stopCameraPreview();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mProxy.onSaveInstanceState(outState);
    }

}
