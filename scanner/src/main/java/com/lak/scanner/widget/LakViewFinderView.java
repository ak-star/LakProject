package com.lak.scanner.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.os.Build;
import android.text.TextPaint;
import android.text.TextUtils;
import android.util.AttributeSet;

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

    private RectF rect_lt, rect_rt, rect_lb, rect_rb;

    private String sMsg = "";
    private TextPaint mTextPaint;

    public LakViewFinderView(Context context) {
        this(context, null);
        initialTextPaint(); // 设置文本画笔
    }

    // 设置文本画笔
    private void initialTextPaint() {
        mTextPaint = new TextPaint(Paint.ANTI_ALIAS_FLAG | Paint.DEV_KERN_TEXT_FLAG);// 设置画笔
        mTextPaint.setTextSize(14);// 字体大小
        mTextPaint.setTypeface(Typeface.DEFAULT_BOLD);// 采用默认的宽度
        mTextPaint.setColor(Color.WHITE);// 采用的颜色
        this.mTextPaint.setAntiAlias(true);
    }

    public LakViewFinderView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        mDensityDpi = context.getResources().getDisplayMetrics().densityDpi;
        mLaserPaint.setStrokeWidth(mLaserStrokeWidth = dp2px(2));
        mLaserStartLocation = dp2px(20);

        mBorderPaint.setStrokeWidth(dp2px(3));
        mBorderPaint.setAntiAlias(true);

        rect_lt = new RectF(); rect_rt = new RectF();
        rect_lb = new RectF(); rect_rb = new RectF();
    }

    protected int dp2px(int dp) {
        return (int) (dp * (mDensityDpi / 160) + 0.5f);
    }

    private void setRectF(RectF rectF, float left, float top, float right, float bottom) {
        rectF.left = left; rectF.top = top;
        rectF.right = right; rectF.bottom = bottom;
    }

    @Override
    public void drawViewFinderMask(Canvas canvas) {
        super.drawViewFinderMask(canvas);

        Rect framingRect = this.getFramingRect();
        int width = canvas.getWidth();
        int height = canvas.getHeight();
        drawOverMaskBottom(canvas, new Rect(0, framingRect.bottom + 1, width, height));
    }

    // 在底层遮罩上绘制
    public void drawOverMaskBottom(Canvas canvas, Rect rect) {
//        if (TextUtils.isEmpty(sMsg)) { return; }
//        // 得到使用该paint写上text的时候,像素为多少
//        float txtLength = mTextPaint.measureText(sMsg);
//        int width = Math.abs(rect.right - rect.left);
//        int txt_l = rect.left; //起始位置
//        if (width > txtLength)
//            txt_l += (int) ((width - txtLength) / 2);
//        mTextPaint.
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

}
