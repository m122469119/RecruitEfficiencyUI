package com.mix.recruitefficiencyactionview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.view.View;

/**
 * Des:       RecruitProgressView
 * Create by: m122469119
 * On:        2018/6/5 11:33
 * Email:     122469119@qq.com
 */
public class RecruitEfficiencyActionView extends View {


    /**
     * 起始角度
     */
    private int mStartAngle = 90;
    /**
     * 结束角度
     */
    private int mSweepAngle = 360;

    /**
     * 最小值
     */
    private int mMin = 0;
    /**
     * 最大值
     */
    private int mMax = 100;

    /**
     * 进度
     */
    private int mProgressValue = 0;


    /**
     * 圆弧宽度
     */
    //  private int mProgressWidth;

    private int mPadding;

    /**
     * 圆心x坐标
     */
    private float mCenterX;

    /**
     * 圆心y坐标
     */
    private float mCenterY;

    private Paint mPaint;
    private Paint mPaint1;
    private Paint mPaint2;
    private Paint mPaint3;
    private Paint mPaint4;
    private Paint mPaint5;

    private RectF mRectFArc1;
    private RectF mRectFArc2;


    private int mWidth;


    public RecruitEfficiencyActionView(Context context) {
        this(context, null);
    }

    public RecruitEfficiencyActionView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RecruitEfficiencyActionView(Context context, AttributeSet attrs, int defStyleAttr) {
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
        mPaint1.setStrokeWidth(UIHelper.dpToPx(getContext(), 4));
        mPaint1.setAlpha(80);


        mPaint2 = new Paint();
        mPaint2.setAntiAlias(true);
        mPaint2.setColor(Color.WHITE);
        mPaint2.setStyle(Paint.Style.FILL);
        mPaint2.setStrokeWidth(UIHelper.dpToPx(getContext(), 1));
        mPaint2.setAlpha(80);


        mPaint3 = new Paint();
        mPaint3.setAntiAlias(true);
        mPaint3.setStrokeCap(Paint.Cap.SQUARE);
        mPaint3.setStyle(Paint.Style.FILL);
        mPaint3.setTextAlign(Paint.Align.CENTER);
        mPaint3.setColor(Color.WHITE);
        mPaint3.setTypeface(Typeface.DEFAULT_BOLD);
        mPaint3.setTextSize(UIHelper.spToPx(getContext(), 20));

        //文字画笔
        mPaint4 = new Paint();
        mPaint4.setAntiAlias(true);
        mPaint4.setStrokeCap(Paint.Cap.SQUARE);
        mPaint4.setStyle(Paint.Style.FILL);
        mPaint4.setTextAlign(Paint.Align.CENTER);
        mPaint4.setColor(Color.WHITE);
        mPaint4.setTypeface(Typeface.DEFAULT_BOLD);
        mPaint4.setTextSize(UIHelper.spToPx(getContext(), 9));

        //文字画笔2
        mPaint5 = new Paint();
        mPaint5.setAntiAlias(true);
        mPaint5.setStrokeCap(Paint.Cap.SQUARE);
        mPaint5.setStyle(Paint.Style.FILL);
        mPaint5.setTextAlign(Paint.Align.CENTER);
        mPaint5.setColor(Color.WHITE);
        mPaint5.setTextSize(UIHelper.spToPx(getContext(), 12));

        //    Typeface typeface = Typeface.createFromAsset(getContext().getAssets(), "font/fontzipMin.ttf");

        //  mProgressWidth = UIHelper.dpToPx(getContext(), 2);

        //
        //        //最外圆弧背景画笔
        //        mPaint1 = new Paint();
        //        mPaint1.setAntiAlias(true);
        //        mPaint1.setStrokeCap(Paint.Cap.SQUARE);
        //        mPaint1.setColor(Color.parseColor("#F2F5F9"));
        //        mPaint1.setStyle(Paint.Style.STROKE);
        //        mPaint1.setStrokeWidth(mProgressWidth);
        //
        //        //圆弧画笔
        //        mPaint2 = new Paint();
        //        mPaint2.setAntiAlias(true);
        //        mPaint2.setStrokeCap(Paint.Cap.SQUARE);
        //        mPaint2.setColor(ContextCompat.getColor(getContext(), R.color.app_primary_color));
        //        mPaint2.setStyle(Paint.Style.STROKE);
        //        mPaint2.setStrokeWidth(mProgressWidth);
        //
        //        //亮点画笔
        //        mPaint3 = new Paint();
        //        mPaint3.setAntiAlias(true);
        //        mPaint3.setStrokeCap(Paint.Cap.SQUARE);
        //        mPaint3.setColor(ContextCompat.getColor(getContext(), R.color.app_primary_color));
        //        mPaint3.setStyle(Paint.Style.FILL);
        //        mPaint3.setStrokeWidth(mProgressWidth);
        //
        //
        //        //文字画笔
        //        mPaint4 = new Paint();
        //        mPaint4.setTypeface(typeface);
        //        mPaint4.setAntiAlias(true);
        //        mPaint4.setStrokeCap(Paint.Cap.SQUARE);
        //        mPaint4.setColor(ContextCompat.getColor(getContext(), R.color.app_primary_color));
        //        mPaint4.setStyle(Paint.Style.FILL);
        //        mPaint4.setTextAlign(Paint.Align.CENTER);
        //        mPaint4.setTextSize(UIHelper.spToPx(getContext(), 21));
        //
        //        //文字画笔2
        //        mPaint5 = new Paint();
        //        mPaint5.setAntiAlias(true);
        //        mPaint5.setStrokeCap(Paint.Cap.SQUARE);
        //        mPaint5.setColor(Color.parseColor("#666666"));
        //        mPaint5.setStyle(Paint.Style.FILL);
        //        mPaint5.setTextAlign(Paint.Align.CENTER);
        //        mPaint5.setTextSize(UIHelper.spToPx(getContext(), 10));


        mRectFArc1 = new RectF();

        mRectFArc2 = new RectF();


    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(widthMeasureSpec, widthMeasureSpec);

        mPadding = Math.max(
                Math.max(getPaddingLeft(), getPaddingTop()),
                Math.max(getPaddingRight(), getPaddingBottom())
        );

        mWidth = getMeasuredWidth();

        mCenterX = mCenterY = mWidth / 2f;

        // mRadius = (mWidth - mPadding * 2) / 2;


        mRectFArc1.set(
                mPadding,
                mPadding,
                mWidth - mPadding,
                mWidth - mPadding
        );

        mRectFArc2.set(
                mPadding + mWidth * 0.0517f,
                mPadding + mWidth * 0.0517f,
                mWidth - mPadding - mWidth * 0.0517f,
                mWidth - mPadding - mWidth * 0.0517f
        );


    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);


        //最外层圆环
        canvas.drawArc(mRectFArc1, mStartAngle, mSweepAngle, false, mPaint);

        //第二层圆环
        canvas.drawArc(mRectFArc2, -90, calculateRelativeAngleWithValue(mProgressValue), false, mPaint1);

        float sx0 = mCenterX + mPadding;
        float sy0 = 0 - UIHelper.dpToPx(getContext(), 1);
        float sx1 = mCenterX + mPadding;
        float sy1 = sy0 - UIHelper.dpToPx(getContext(), 5);

        canvas.save();
        canvas.rotate(-45, mCenterX, mCenterY);
        for (int i = 0; i < 4; i++) {
            canvas.rotate(-90, mCenterX, mCenterY);
            canvas.drawLine(sx0, sy0, sx1, sy1, mPaint2);
        }
        canvas.restore();

        //画进度圆弧背景
        //  canvas.drawArc(mRectFProgressArc, mStartAngle, mSweepAngle, false, mPaint1);

        //画进度圆弧
        //  canvas.drawArc(mRectFProgressArc, mStartAngle, calculateRelativeAngleWithValue(mProgressValue), false, mPaint2);


        //画实时进度
        canvas.drawText(String.valueOf(mProgressValue), mCenterX - UIHelper.calcTextWidth(mPaint3, String.valueOf(mProgressValue)) / 2, mCenterY, mPaint3);

        canvas.drawText("/100", mCenterX + UIHelper.calcTextWidth(mPaint4, String.valueOf("10")), mCenterY, mPaint4);
        //画招聘效率
        canvas.drawText("今日得分", mCenterX, mCenterY + UIHelper.calcTextHeight(mPaint5, String.valueOf("今日得分")) + UIHelper.dpToPx(getContext(), 6), mPaint5);

    }


    /**
     * 设置进度
     *
     * @param progressValue
     */
    public void setProgressValue(int progressValue) {
        if (mProgressValue == progressValue || progressValue < mMin || progressValue > mMax) {
            return;
        }
        mProgressValue = progressValue;
        invalidate();
    }


    public int getProgressValue() {
        return mProgressValue;
    }

    /**
     * 相对起始角度计算信用分所对应的角度大小
     */
    private float calculateRelativeAngleWithValue(int value) {
        return mSweepAngle * value * 1f / mMax;
    }


    public void setMax(int max) {
        mMax = max;
    }
}
