package com.lak.prj;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Point;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.FrameLayout;

import com.lak.ScanActivity;
import com.lak.core.tools.ToastUtils;
import com.lak.scanner.impl.ScanDelegateImpl;
import com.lak.scanner.impl.ZbarScanDelegateImpl;
import com.lak.scanner.widget.LakViewFinderView;
import com.lak.tools.display.CtrlTools;

import me.dm7.barcodescanner.core.IViewFinder;
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
        panel.addView(mProxy.getScannerView());
    }

    @Override
    protected ScanDelegateImpl newInstanceScanDelegate(Context ctx) {
        return new ZbarScanDelegateImpl(mCtx = this) {
            @Override
            public void handleResult(Result result) {
//                finish();
                ToastUtils.instance().show(result.getContents());
                mProxy.getScannerView().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        resumeCameraPreview();
                    }
                }, 3000);
            }

            @Override
            protected void setFinderView(IViewFinder finderView) {
                if (finderView instanceof LakViewFinderView) {
                    LakViewFinderView lakFinderView = (LakViewFinderView) finderView;

                    CtrlTools instance = CtrlTools.instance(mCtx);
                    lakFinderView.setBitmapButton(R.mipmap.i_scan_close,
                            new Point(instance.dp2px(30), instance.dp2px(30)));
                    lakFinderView.setMessageText("将要扫的二维码放入取景框中，自动识别");
                    lakFinderView.setMessageFontSize(14);
                    lakFinderView.setMessageMarginDp(30);
                    lakFinderView.setOnBitmapButtonClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            finish();
                        }
                    });
                }
            }
        };
    }

}
