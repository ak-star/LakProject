package com.lak.scanner;

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
import com.lak.scanner.widget.LakViewFinderView;

import me.dm7.barcodescanner.zbar.Result;

/**
 * Created by lawrence on 2018/6/11.
 * <p>
 * 扫码fragment，需要自己申请相机权限
 */

public abstract class ScanFragment extends Fragment {
    private Context mCtx;
    private ScanDelegateImpl mProxy = null;
    protected View mScanView = null; // 二维码扫描控件

    protected abstract void handleResult(Result result);
    protected abstract void setViewFinderView(LakViewFinderView finderView);

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
        mProxy = new ScanDelegateImpl(mCtx) {
            @Override
            public void handleResult(Result result) {
                ScanFragment.this.handleResult(result);
            }
            @Override
            protected void setViewFinderView(LakViewFinderView finderView) {
                ScanFragment.this.setViewFinderView(finderView);
            }
        };
        mScanView = mProxy.onCreateView(savedInstanceState);
        return mScanView;
    }

    @Override
    public void onResume() {
        super.onResume();
        mProxy.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        mProxy.onPause();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mProxy.onSaveInstanceState(outState);
    }

}
