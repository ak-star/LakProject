package com.lak.prj;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.FrameLayout;

import com.lak.core.tools.ToastUtils;
import com.lak.scanner.ScanActivity;

import me.dm7.barcodescanner.zbar.Result;

/**
 * Created by lawrence on 2018/6/11.
 */

public class TestScanActivity extends ScanActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_scan);
        mProxy.setLaserColor(Color.parseColor("#CCFFFFFF"));
        mProxy.setBorderColor(Color.parseColor("#FFFFFF"));
        FrameLayout panel = findViewById(R.id.scan_panel);
        panel.addView(mScanView);
    }

    @Override
    protected void handleResult(Result result) {
        finish();
        ToastUtils.instance().show(result.getContents());
    }
}
