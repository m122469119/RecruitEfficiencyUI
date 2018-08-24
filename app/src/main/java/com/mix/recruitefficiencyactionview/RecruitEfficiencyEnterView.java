package com.mix.recruitefficiencyactionview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.View;

/**
 * Des:       RecruitProgressView
 * Create by: m122469119
 * On:        2018/6/5 11:33
 * Email:     122469119@qq.com
 */
public class RecruitEfficiencyEnterView extends View {

    /**
     * 起始角度
     */
    private int mStartAngle = -90;

    /**
     * 绘制弧度
     */
    private int mSweepAngle = 360;


    /**
     * padding
     */
    private int mPadding;

    /**
     * 圆心
     */
    private float mCenterX, mCenterY; // 圆心坐标


    private Paint mPaint;
    private Paint mPaint1;
    private Paint mPaint2;
    private Paint mPaint3;
    private Paint mPaint4;
    private Paint mPaint5;
    private Paint mPaint6;

    private RectF mRectFArc1;

    private RectF mRectFArc2;

    private RectF mRectFArc3;

    private RectF mRectFArc4;


    /**
     * 最小值
     */
    private int mMin = 0;
    /**
     * 最大值
     */
    private int mMax = 360;


    private Path mPath;


    public RecruitEfficiencyEnterView(Context context) {
        this(context, null);
    }

    public RecruitEfficiencyEnterView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RecruitEfficiencyEnterView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        init();
    }

    private void init() {


        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setColor(Color.WHITE);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(UIHelper.dpToPx(getContext(), 1));
        mPaint.setAlpha(80);

        mPaint1 = new Paint();
        mPaint1.setAntiAlias(true);
        mPaint1.setColor(Color.WHITE);
        mPaint1.setStyle(Paint.Style.STROKE);
        mPaint1.setStrokeWidth(UIHelper.dpToPx(getContext(), 6));
        mPaint1.setAlpha(80);


        mPaint2 = new Paint();
        mPaint2.setAntiAlias(true);
        mPaint2.setColor(Color.WHITE);
        mPaint2.setStyle(Paint.Style.STROKE);
        mPaint2.setStrokeWidth(UIHelper.dpToPx(getContext(), 1));
        mPaint2.setAlpha(80);

        mPaint3 = new Paint();
        mPaint3.setAntiAlias(true);
        mPaint3.setColor(Color.WHITE);
        mPaint3.setStyle(Paint.Style.FILL);

        mPaint4 = new Paint();
        mPaint4.setAntiAlias(true);
        mPaint4.setColor(Color.WHITE);
        mPaint4.setStyle(Paint.Style.FILL);

        mPaint5 = new Paint();
        mPaint5.setAntiAlias(true);
        mPaint5.setColor(Color.WHITE);
        mPaint5.setStyle(Paint.Style.FILL);
        mPaint5.setStrokeCap(Paint.Cap.ROUND);
        mPaint5.setStrokeWidth(UIHelper.dpToPx(getContext(), 1.3f));
        mPaint5.setAlpha(180);


        mPaint6 = new Paint();
        mPaint6.setAntiAlias(true);
        mPaint6.setStyle(Paint.Style.FILL);
        mPaint6.setColor(ContextCompat.getColor(getContext(), R.color.app_primary_color));
        mPaint6.setTextSize(UIHelper.spToPx(getContext(), 16));
        mPaint6.setTextAlign(Paint.Align.CENTER);


        mRectFArc1 = new RectF();

        mRectFArc2 = new RectF();

        mRectFArc3 = new RectF();

        mRectFArc4 = new RectF();


        mPath = new Path();

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        mPadding = Math.max(
                Math.max(getPaddingLeft(), getPaddingTop()),
                Math.max(getPaddingRight(), getPaddingBottom())
        );


        int width = getMeasuredWidth();


        mCenterX = mCenterY = width / 2f;


        mRectFArc1.set(
                mPadding,
                mPadding,
                width - mPadding,
                width - mPadding
        );

        mRectFArc2.set(
                mPadding + width * 0.083f,
                mPadding + width * 0.083f,
                width - mPadding - width * 0.083f,
                width - mPadding - width * 0.083f
        );

        mRectFArc3.set(
                mPadding + width * 0.215f,
                mPadding + width * 0.215f,
                width - mPadding - width * 0.215f,
                width - mPadding - width * 0.215f
        );

        mRectFArc4.set(
                mPadding + width * 0.307f,
                mPadding + width * 0.307f,
                width - mPadding - width * 0.307f,
                width - mPadding - width * 0.307f
        );


        float sx0 = mCenterX;
        float sy0 = mPadding + (getMeasuredWidth() - mPadding * 2) * 0.161f;
        float sx1 = mCenterX - UIHelper.dpToPx(getContext(), 4);
        float sy1 = sy0 - UIHelper.dpToPx(getContext(), 6);
        float sx2 = mCenterX + UIHelper.dpToPx(getContext(), 4);
        float sy2 = sy0 - UIHelper.dpToPx(getContext(), 6);

        mPath.moveTo(sx0, sy0);
        mPath.lineTo(sx1, sy1);
        mPath.lineTo(sx2, sy2);
        mPath.close();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);


        //最外层圆环
        canvas.drawArc(mRectFArc1, mStartAngle, mSweepAngle, false, mPaint);

        //第二层圆环
        canvas.drawArc(mRectFArc2, mStartAngle, mSweepAngle, false, mPaint1);

        //第三层圆环
        canvas.drawArc(mRectFArc3, mStartAngle, mSweepAngle, false, mPaint2);

        //第四层圆形
        canvas.drawArc(mRectFArc4, mStartAngle, mSweepAngle, false, mPaint3);

        //画三角
        canvas.save();
        canvas.rotate(-mProgressValue, mCenterX, mCenterY);
        for (int i = 0; i < 4; i++) {
            canvas.rotate(-90, mCenterX, mCenterY);
            canvas.drawPath(mPath, mPaint4);

        }
        canvas.restore();


        //画刻度
        float x0 = mCenterX;
        float y0 = mPadding + (getMeasuredWidth() - mPadding * 2) * 0.243f;
        float x1 = mCenterX;
        float y1 = y0 + UIHelper.dpToPx(getContext(), 9);

        canvas.save();
        canvas.drawLine(x0, y0, x1, y1, mPaint5);
        float degree = mSweepAngle / 90;
        for (int i = 0; i < 90 - 1; i++) {
            canvas.rotate(-degree, mCenterX, mCenterY);
            canvas.drawLine(x0, y0, x1, y1, mPaint5);
        }
        canvas.restore();


        canvas.save();
        canvas.drawLine(x0, y0, x1, y1, mPaint5);
        for (int i = 0; i < mProgressValue / 4; i++) {
            canvas.rotate(degree, mCenterX, mCenterY);
            canvas.drawLine(x0, y0, x1, y1, mPaint5);
        }
        canvas.restore();

        //画文本
        canvas.drawText("分析中", mCenterX, mCenterY + UIHelper.calcTextHeight(mPaint6, "分析中") / 2, mPaint6);

    }


    private float mProgressValue = 0;


    public float getProgressValue() {
        return mProgressValue;
    }

    public void setProgressValue(float progressValue) {
        if (mProgressValue == progressValue || progressValue < mMin || progressValue > mMax) {
            return;
        }
        mProgressValue = progressValue;
        invalidate();
    }
}
