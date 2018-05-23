package com.lak.autolayout.main;

import android.util.TypedValue;

/**
 * Created by lawrence on 2018/5/23.
 * <p>
 * 用于适配时辅助，填写的单位，判断是否为px
 */

public class DiUtils {

    // 得到混合单位
    private static int getComplexUnit(int data) {
        return TypedValue.COMPLEX_UNIT_MASK & (data >> TypedValue.COMPLEX_UNIT_SHIFT);
    }

    // 是否为px值
    public static boolean isPxVal(TypedValue val) {
        if (val != null && val.type == TypedValue.TYPE_DIMENSION &&
                getComplexUnit(val.data) == TypedValue.COMPLEX_UNIT_PX) {
            return true;
        }
        return false;
    }
}
