package com.lak.scanner.widget;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.RectF;
import android.os.Build;
import android.support.annotation.DrawableRes;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.ViewConfiguration;

import com.lak.scanner.R;

import java.lang.ref.WeakReference;

import me.dm7.barcodescanner.core.ViewFinderView;

/**
 * Created by lawrence on 2018/6/11.
 * <p>
 * 扫描框
 *
 * @link https://github.com/dm77/barcodescanner
 */

public class LakViewFinderView extends ViewFinderView {

    private static final int POINT_SIZE = 10;
    private static final long ANIMATION_DELAY = 15l; // 人眼，一秒大约33帧，显示没有卡顿感
    private float mLaserLocation = 0;  // 扫描线移动位置
    private final float mDensityDpi;
    private int mLaserStrokeWidth = 2;
    private final int mLaserStartLocation;
    private boolean isStop = false;     // 是否停止扫描

    private RectF rect_lt, rect_rt, rect_lb, rect_rb;

    private Paint mTextPaint;
    private String sMsg = "";   // 显示文本
    private int mMessageColor = Color.WHITE;  // 文字颜色
    private float mMessageFontSize = 14f;   // 文字大小 单位sp
    private int mTextMarginBottom = 20;     // 文字距离扫描框的间距

    private Paint mBitmapPaint;
    private @DrawableRes int mBitmapButton = -1;        // 显示在下面区域的按钮
    private WeakReference<Bitmap> mWrfBitmap = null;    // 显示在下面区域的按钮
    private Point mBitmapSize = null;       // 图片显示大小
    private Rect click_effect_rect = null;  // 按钮响应区域

    private OnClickListener mButtonClickListener = null;    // 按钮点击事件
    private final int TOUCH_SLOP;       // 移动最小单位
    private boolean isEffective = false;    // 点击是否有效

    public LakViewFinderView(Context context) {
        this(context, null);
    }

    public LakViewFinderView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        mDensityDpi = context.getResources().getDisplayMetrics().densityDpi;
        mLaserPaint.setStrokeWidth(mLaserStrokeWidth = dp2px(2));
        mLaserStartLocation = dp2px(20);

        mBorderPaint.setStrokeWidth(dp2px(3));
        mBorderPaint.setAntiAlias(true);

        rect_lt = new RectF();
        rect_rt = new RectF();
        rect_lb = new RectF();
        rect_rb = new RectF();

        TOUCH_SLOP = ViewConfiguration.get(context).getScaledTouchSlop();
        sMsg = context.getString(R.string.scanner_warning);

        initialTextPaint();     // 设置文本画笔
        mTextMarginBottom = dp2px(20); // 文字距离扫描框的间距
        initialBitmapPaint();   // 设置图片画笔
    }

    // 设置文本画笔
    private void initialTextPaint() {
        mTextPaint = new Paint();               // 设置画笔
        mTextPaint.setStrokeWidth(2);          // 设置画笔宽度
        mTextPaint.setAntiAlias(true);          // 指定是否使用抗锯齿功能，如果使用，会使绘图速度变慢
        mTextPaint.setStyle(Paint.Style.FILL);  // 绘图样式，对于设文字和几何图形都有效

        mTextPaint.setTextAlign(Paint.Align.LEFT);    //设置文字对齐方式，取值：align.CENTER、align.LEFT或align.RIGHT
        mTextPaint.setTextSize(sp2px(mMessageFontSize));         //设置文字大小
        mTextPaint.setColor(mMessageColor);   // 采用的颜色

//        mTextPaint.setFakeBoldText(true);           //设置是否为粗体文字
//        mTextPaint.setUnderlineText(true);          //设置下划线
//        mTextPaint.setTextSkewX((float) -0.25);     //设置字体水平倾斜度，普通斜体字是-0.25
//        mTextPaint.setStrikeThruText(true);         //设置带有删除线效果
    }

    // 设置图片画笔
    private void initialBitmapPaint() {
        mBitmapPaint = new Paint();               // 设置画笔
        mBitmapPaint.setFlags(Paint.ANTI_ALIAS_FLAG|Paint.FILTER_BITMAP_FLAG);
        mBitmapPaint.setAntiAlias(true);          // 指定是否使用抗锯齿功能，如果使用，会使绘图速度变慢
    }

    protected int dp2px(int dp) {
        return (int) (dp * (mDensityDpi / 160) + 0.5f);
    }

    protected int sp2px(float spVal) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP,
                spVal, getResources().getDisplayMetrics());
    }

    private void setRectF(RectF rectF, float left, float top, float right, float bottom) {
        rectF.left = left;
        rectF.top = top;
        rectF.right = right;
        rectF.bottom = bottom;
    }

    @Override
    public void drawViewFinderMask(Canvas canvas) {
        super.drawViewFinderMask(canvas);

        Rect framingRect = this.getFramingRect();
        int width = canvas.getWidth();
        int height = canvas.getHeight();
        drawOverMaskTop(canvas, new Rect(0, 0, width, framingRect.top));
        drawOverMaskBottom(canvas, new Rect(0, framingRect.bottom + 1, width, height));
    }

    // 在顶层遮罩上绘制
    public void drawOverMaskTop(Canvas canvas, Rect rect) {
        if (!TextUtils.isEmpty(sMsg)) {
            // 得到使用该paint写上text的时候,像素为多少
            float txtLength = mTextPaint.measureText(sMsg);
            int width = Math.abs(rect.right - rect.left);
            int txt_l = rect.left; //起始位置
            if (width > txtLength)
                txt_l += (int) ((width - txtLength) / 2);
            canvas.drawText(sMsg, txt_l, rect.bottom - mTextMarginBottom, mTextPaint);
        }
    }

    // 在底层遮罩上绘制
    public void drawOverMaskBottom(Canvas canvas, Rect rect) {
        final Bitmap bitmap = getBitmap();
        if (bitmap != null && mBitmapSize != null && mBitmapSize.x > 0 && mBitmapSize.y > 0) {
            // 最终绘制宽高
            int do_w = mBitmapSize.x, do_h = mBitmapSize.y;
            // 获得可绘制的宽度
            int w = Math.abs(rect.right - rect.left);
            do_w = Math.min(do_w, w);
            // 获得可绘制的高度
            int h = Math.abs(rect.bottom - rect.top);
            do_h = Math.min(do_h, h);
            // 计算绘制矩形位置
            int left = (w - do_w) / 2, top = (h - do_h) / 2 + rect.top;
            click_effect_rect = new Rect(left, top, left + do_w, top + do_h);
            canvas.drawBitmap(bitmap,
                    new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight()),
                    click_effect_rect, mBitmapPaint);
        }
    }

    // 得到要绘制的图片
    private Bitmap getBitmap() {
        if (mBitmapButton == -1) {
            recycleBitmap();
        } else if (mWrfBitmap == null
                || mWrfBitmap.get() == null) {
            recycleBitmap();
            Bitmap bmp = BitmapFactory.decodeResource(getResources(), mBitmapButton);
            if (bmp != null)
                mWrfBitmap = new WeakReference<>(bmp);
        }
        if (mWrfBitmap != null) {
            final Bitmap bitmap = mWrfBitmap.get();
            if (bitmap != null)
                return bitmap;
        }
        return null;
    }

    @Override
    public void drawViewFinderBorder(Canvas canvas) {
        Rect framingRect = this.getFramingRect();

        int newBorderLineLength = this.mBorderLineLength / 6;
        int diameter = newBorderLineLength * 2; // 直径

        Path path = new Path();

        path.moveTo((float) framingRect.left, (float) (framingRect.top + this.mBorderLineLength));
        path.lineTo((float) framingRect.left, (float) framingRect.top + newBorderLineLength);
        setRectF(rect_lt, (float) framingRect.left, (float) framingRect.top,
                (float) (framingRect.left + diameter), (float) (framingRect.top + diameter));
        path.arcTo(rect_lt, 180f, 90f, false);
        path.lineTo((float) (framingRect.left + newBorderLineLength), (float) framingRect.top);
        path.lineTo((float) (framingRect.left + this.mBorderLineLength), (float) framingRect.top);
        canvas.drawPath(path, this.mBorderPaint);

        path.moveTo((float) (framingRect.right - newBorderLineLength), (float) framingRect.top);
        path.lineTo((float) (framingRect.right - this.mBorderLineLength), (float) framingRect.top);
        setRectF(rect_rt, (float) (framingRect.right - diameter), (float) framingRect.top,
                (float) framingRect.right, (float) (framingRect.top + diameter));
        path.arcTo(rect_rt, -90f, 90f, false);
        path.lineTo((float) framingRect.right, (float) framingRect.top + newBorderLineLength);
        path.lineTo((float) framingRect.right, (float) (framingRect.top + this.mBorderLineLength));
        canvas.drawPath(path, this.mBorderPaint);


        path.moveTo((float) framingRect.right, (float) (framingRect.bottom - this.mBorderLineLength));
        path.lineTo((float) framingRect.right, (float) framingRect.bottom - newBorderLineLength);
        setRectF(rect_rb, (float) (framingRect.right - diameter), (float) (framingRect.bottom - diameter),
                (float) framingRect.right, (float) framingRect.bottom);
        path.arcTo(rect_rb, 0f, 90f, false);
        path.lineTo((float) (framingRect.right - newBorderLineLength), (float) framingRect.bottom);
        path.lineTo((float) (framingRect.right - this.mBorderLineLength), (float) framingRect.bottom);
        canvas.drawPath(path, this.mBorderPaint);

        path.moveTo((float) (framingRect.left + mBorderLineLength), (float) framingRect.bottom);
        path.lineTo((float) (framingRect.left + newBorderLineLength), (float) framingRect.bottom);
        setRectF(rect_lb, (float) framingRect.left, (float) (framingRect.bottom - diameter),
                (float) (framingRect.left + diameter), (float) framingRect.bottom);
        path.arcTo(rect_lb, 90f, 90f, false);
        path.lineTo((float) framingRect.left, (float) (framingRect.bottom - newBorderLineLength));
        path.lineTo((float) framingRect.left, (float) framingRect.bottom - this.mBorderLineLength);
        canvas.drawPath(path, this.mBorderPaint);
    }

    @Override
    public void drawLaser(Canvas canvas) {
        if (isStop) { return; }
        Rect framingRect = getFramingRect();

        if (mLaserLocation == 0 || mLaserLocation >= framingRect.bottom - mLaserStartLocation) {
            mLaserLocation = framingRect.top + mLaserStartLocation;
        } else {
            mLaserLocation += 2;
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            canvas.drawRoundRect(framingRect.left + 30 + 2,
                    mLaserLocation - (mLaserStrokeWidth / 2),
                    framingRect.right - 30 - 1,
                    mLaserLocation + mLaserStrokeWidth,
                    mLaserStrokeWidth,
                    mLaserStrokeWidth,
                    mLaserPaint);
        } else {
            RectF rectF = new RectF(framingRect.left + 30 + 2,
                    mLaserLocation - (mLaserStrokeWidth / 2),
                    framingRect.right - 30 - 1,
                    mLaserLocation + mLaserStrokeWidth);
            canvas.drawRoundRect(rectF, mLaserStrokeWidth, mLaserStrokeWidth, mLaserPaint);
        }

        postInvalidateDelayed(ANIMATION_DELAY,
                framingRect.left - POINT_SIZE,
                framingRect.top - POINT_SIZE,
                framingRect.right + POINT_SIZE,
                framingRect.bottom + POINT_SIZE);
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        recycleBitmap(); // 释放图片资源
    }

    // 释放图片资源
    private void recycleBitmap() {
        if (mWrfBitmap != null) {
            Bitmap bitmap = mWrfBitmap.get();
            mWrfBitmap.clear();
            if (bitmap != null)
                bitmap.recycle();
            mWrfBitmap = null;
        }
    }

    /**
     * 设置提示文本
     *
     * @param message
     */
    public void setMessageText(String message) {
        sMsg = message;
    }

    /**
     * 设置提示文本文字颜色
     *
     * @param color
     */
    public void setMessageColor(int color) {
        mMessageColor = color;
        if (mTextPaint != null)
            mTextPaint.setColor(mMessageColor);
    }

    /**
     * 设置提示文本文字字号
     *
     * @param spVal 单位sp
     */
    public void setMessageFontSize(float spVal) {
        mMessageFontSize = spVal;
        if (mTextPaint != null)
            mTextPaint.setTextSize(sp2px(mMessageFontSize));
    }

    /**
     * 文字距离扫描框的间距
     * @param dpVal
     */
    public void setMessageMarginDp(int dpVal) {
        mTextMarginBottom = dp2px(dpVal);
    }

    /**
     * 文字距离扫描框的间距
     * @param pxVal
     */
    public void setMessageMarginPx(int pxVal) {
        mTextMarginBottom = pxVal;
    }

    /**
     * 在扫描框下显示图片按钮
     *
     * @param resId  图片
     * @param size   图片显示大小
     */
    public void setBitmapButton(@DrawableRes int resId, Point size) {
        recycleBitmap();
        mBitmapButton = resId;
        mBitmapSize = size;
    }

    // 图片按钮点击事件
    public void setOnBitmapButtonClickListener(OnClickListener listener) {
        mButtonClickListener = listener;
    }

    // 是否停止扫描线绘制
    public void stopLaser(boolean isStop) {
        if (!(this.isStop = isStop)) {
            Rect framingRect = getFramingRect();
            mLaserLocation = 0;
            postInvalidateDelayed(ANIMATION_DELAY,
                    framingRect.left - POINT_SIZE,
                    framingRect.top - POINT_SIZE,
                    framingRect.right + POINT_SIZE,
                    framingRect.bottom + POINT_SIZE);
        }
    }

    private float downX = -1, downY = -1;
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (mButtonClickListener != null) {
            final OnClickListener clickListener = mButtonClickListener;

            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    isEffective = false;
                    if (click_effect_rect != null) {
                        downX = event.getX(); downY = event.getY();
                        isEffective = downX >= click_effect_rect.left
                                && downX <= click_effect_rect.right
                                && downY >= click_effect_rect.top
                                && downY <= click_effect_rect.bottom;

                        if (isEffective) {
                            return true;
                        }
                    }
                    break;

                case MotionEvent.ACTION_MOVE:
                    if (isEffective
                            && Math.abs(event.getX() - downX) >= TOUCH_SLOP
                            || Math.abs(event.getY() - downY) >= TOUCH_SLOP) {
                        isEffective = false;
                    }
                    break;

                case MotionEvent.ACTION_UP:
                    if (isEffective && click_effect_rect != null) {
                        isEffective = false;
                        float upX = event.getX(), upY = event.getY();
                        if (clickListener != null
                                && upX >= click_effect_rect.left
                                && upX <= click_effect_rect.right
                                && upY >= click_effect_rect.top
                                && upY <= click_effect_rect.bottom) {
                            clickListener.onClick(this);
                            return true;
                        }
                    }
                    break;

                case MotionEvent.ACTION_CANCEL:
                    isEffective = false;
                    break;

            }
        }
        return super.onTouchEvent(event);
    }

}
