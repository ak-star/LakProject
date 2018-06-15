package com.lak.scanner.delegate;

import android.os.Bundle;
import android.view.View;

/**
 * Created by lawrence on 2018/6/11.
 * <p>
 * 扫描代理
 */

public interface ScanDelegate {
    // 初始化二维码扫描控件，并恢复相机相关设置
    View onCreateView(Bundle savedInstanceState);

    // 启动二维码扫描
    void onResume();

    // 暂停二维码扫描
    void onPause();

    // 存储相机相关设置
    void onSaveInstanceState(Bundle outState);

    // 恢复预览
    void resumeCameraPreview();

    // 停止预览
    void stopCameraPreview();

}
