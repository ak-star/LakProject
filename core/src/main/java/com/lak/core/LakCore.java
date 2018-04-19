package com.lak.core;

import com.lak.logger.CLog;
import com.lak.logger.CLogConfig;

/**
 * Created by lawrence on 2018/4/2.
 */

public final class LakCore {

    /**
     * 初始化日志系统
     * 并可以根据CLogConfig，设置相关日志参数
     *
     * @return
     */
    public static CLogConfig initCLog() {
        return CLog.init();
    }

}
