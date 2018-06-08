package com.lak.autolayout.layout;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by lawrence on 2018/6/8.
 * <p>
 * Ui适配，AppCompatActivity的布局
 */

public class AutoLayoutActivity extends AppCompatActivity {
    private static final String LAYOUT_LINEAR_LAYOUT = "LinearLayout";
    private static final String LAYOUT_LINEAR_LAYOUT_COMPAT = "LinearLayoutCompat";
    private static final String LAYOUT_FRAME_LAYOUT = "FrameLayout";
    private static final String LAYOUT_RELATIVE_LAYOUT = "RelativeLayout";

    // 说明为什么重载onCreateView此方法
    // https://www.jianshu.com/p/7013999db2c6
    @Override
    public View onCreateView(String name, Context context, AttributeSet attrs) {
        View view = null;

        if (name.equals(LAYOUT_LINEAR_LAYOUT)) {
            view = new AutoLinearLayout(context, attrs);
        } else if (name.equals(LAYOUT_LINEAR_LAYOUT_COMPAT)) {
            view = new AutoLinearLayoutCompat(context, attrs);
        } else if (name.equals(LAYOUT_FRAME_LAYOUT)) {
            view = new AutoFrameLayout(context, attrs);
        } else if (name.equals(LAYOUT_RELATIVE_LAYOUT)) {
            view = new AutoRelativeLayout(context, attrs);
        }

        if (view != null) return view;

        return super.onCreateView(name, context, attrs);
    }


}