package com.lak.autolayout.widget;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.util.AttributeSet;

import com.lak.autolayout.layout.AutoFrameLayout;
import com.lak.autolayout.main.AutoLayoutHelper;

/**
 * Created by lawrence on 2018/6/8.
 * <p>
 * Ui适配，CardView的布局
 */

public class AutoCardView extends CardView {
    private final AutoLayoutHelper mHelper = new AutoLayoutHelper(this);

    public AutoCardView(Context context) {
        super(context);
    }

    public AutoCardView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public AutoCardView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public AutoFrameLayout.LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new AutoFrameLayout.LayoutParams(getContext(), attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        if (!isInEditMode()) {
            mHelper.adjustChildren();
        }
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

}
