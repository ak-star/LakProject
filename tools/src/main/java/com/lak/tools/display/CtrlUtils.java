package com.lak.tools.display;

import android.graphics.Point;
import android.view.View;

/**
 * Created by lawrence on 2018/4/2.
 * <p>
 * 控件显示信息
 */

public class CtrlUtils {

    /**
     * 获得控件大小
     *
     * @param view 控件view，view的设置要是wrap_content 才有效
     * @return 控件大小
     */
    public static Point getCtrlSize(View view) {
        int w = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        int h = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        view.measure(w, h);
        return new Point(view.getMeasuredWidth(), view.getMeasuredHeight());
    }

    /**
     * 获得控件高度
     *
     * @param view 控件view，view的设置要是wrap_content 才有效
     * @return 控件高度
     */
    public static int getCtrlH(View view) {
        int w = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        int h = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        view.measure(w, h);
        return view.getMeasuredHeight();
    }

    /**
     * 获得控件宽度
     *
     * @param view 控件view，view的设置要是wrap_content 才有效
     * @return 控件宽度
     */
    public static int getCtrlW(View view) {
        int w = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        int h = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        view.measure(w, h);
        return view.getMeasuredWidth();
    }

}
