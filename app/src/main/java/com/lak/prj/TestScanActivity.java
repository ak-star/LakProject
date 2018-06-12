package com.lak.prj;

import android.graphics.Color;
import android.graphics.Point;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.FrameLayout;

import com.lak.core.tools.ToastUtils;
import com.lak.scanner.ScanActivity;
import com.lak.scanner.widget.LakViewFinderView;
import com.lak.tools.display.CtrlTools;

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

    @Override
    protected void setViewFinderView(LakViewFinderView finderView) {
        CtrlTools instance = CtrlTools.instance(mCtx);
        finderView.setBitmapButton(R.mipmap.i_scan_close,
                new Point(instance.dp2px(30), instance.dp2px(30)));
        finderView.setMessageText("将要扫的二维码放入取景框中，自动识别");
        finderView.setMessageFontSize(14);
        finderView.setMessageMarginDp(30);
        finderView.setOnBitmapButtonClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }



}
