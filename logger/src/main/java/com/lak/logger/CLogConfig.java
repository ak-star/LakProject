package com.lak.logger;


import android.text.TextUtils;


public class CLogConfig {

    // 线程信息输出，true-输出，false-不输出
    private boolean showThreadInfo = true;
    // 调试输出，true-调试状态输出日志，false-非调试状态，不输出日志
    private boolean debug = false;
    // 调试标签
    private String tag = "CLogger";


    public CLogConfig setTag(String tag) {
        if (!TextUtils.isEmpty(tag)) {
            this.tag = tag;
        }
        return this;
    }

    public CLogConfig setShowThreadInfo(boolean showThreadInfo) {
        this.showThreadInfo = showThreadInfo;
        return this;
    }


    public CLogConfig setDebug(boolean debug) {
        this.debug = debug;
        return this;
    }

    public String getTag() {
        return tag;
    }

    public boolean isDebug() {
        return debug;
    }

    public boolean isShowThreadInfo() {
        return showThreadInfo;
    }
}
