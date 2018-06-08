package com.lak.autolayout.widget;

import android.content.Context;
import android.support.design.widget.CollapsingToolbarLayout;
import android.util.AttributeSet;
import android.view.ViewGroup;

import com.lak.autolayout.main.AutoLayoutHelper;
import com.lak.autolayout.main.AutoLayoutInfo;

/**
 * Created by lawrence on 2018/6/8.
 * <p>
 * Ui适配，CollapsingToolbarLayout的布局
 */

public class AutoCollapsingToolbarLayout extends CollapsingToolbarLayout {
    private AutoLayoutHelper mHelper = new AutoLayoutHelper(this);

    public AutoCollapsingToolbarLayout(Context context) {
        super(context);
    }

    public AutoCollapsingToolbarLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public AutoCollapsingToolbarLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        if (!isInEditMode())
            mHelper.adjustChildren();
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }


    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
    }

    @Override
    public LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new AutoCollapsingToolbarLayout.LayoutParams(getContext(), attrs);
    }


    public static class LayoutParams extends CollapsingToolbarLayout.LayoutParams
            implements AutoLayoutHelper.AutoLayoutParams {
        private AutoLayoutInfo mAutoLayoutInfo;

        public LayoutParams(Context c, AttributeSet attrs) {
            super(c, attrs);
            mAutoLayoutInfo = AutoLayoutHelper.getAutoLayoutInfo(c, attrs);
        }

        @Override
        public AutoLayoutInfo getAutoLayoutInfo() {
            return mAutoLayoutInfo;
        }


        public LayoutParams(int width, int height) {
            super(width, height);
        }


        public LayoutParams(ViewGroup.LayoutParams source) {
            super(source);
        }

        public LayoutParams(MarginLayoutParams source) {
            super(source);
        }

    }

}