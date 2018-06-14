package com.lak.autolayout.layout;

import android.content.Context;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;
import android.view.ViewGroup;

import com.lak.autolayout.main.AutoLayoutHelper;
import com.lak.autolayout.main.AutoLayoutInfo;

/**
 * Created by lawrence on 2018/5/23.
 * <p>
 * Ui适配，AutoToolbar 的布局
 */

public class AutoToolbar extends Toolbar {
    private final AutoLayoutHelper mHelper = new AutoLayoutHelper(this);

    public AutoToolbar(Context context) {
        super(context);
    }

    public AutoToolbar(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public AutoToolbar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new LayoutParams(getContext(), attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        if (!isInEditMode())
            mHelper.adjustChildren();
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
    }

    public static class LayoutParams extends Toolbar.LayoutParams
            implements AutoLayoutHelper.AutoLayoutParams {
        private AutoLayoutInfo mAutoLayoutInfo;

        public LayoutParams(Context c, AttributeSet attrs) {
            super(c, attrs);
            mAutoLayoutInfo = AutoLayoutHelper.getAutoLayoutInfo(c, attrs);
        }

        public LayoutParams(int width, int height) {
            super(width, height);
        }
        public LayoutParams(int width, int height, int gravity) {
            super(width, height, gravity);
        }
        public LayoutParams(int gravity) {
            super(gravity);
        }
        public LayoutParams(ActionBar.LayoutParams source) {
            super(source);
        }
        public LayoutParams(ViewGroup.LayoutParams source) {
            super(source);
        }
        public LayoutParams(MarginLayoutParams source) {
            super(source);
        }

        public LayoutParams(LayoutParams source) {
            super(source);
            mAutoLayoutInfo = source.mAutoLayoutInfo;
        }

        @Override
        public AutoLayoutInfo getAutoLayoutInfo() {
            return mAutoLayoutInfo;
        }

    }
}
