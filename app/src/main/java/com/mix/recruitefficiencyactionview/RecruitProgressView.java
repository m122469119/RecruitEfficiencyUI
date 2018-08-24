package com.mix.recruitefficiencyactionview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RadialGradient;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.Typeface;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.View;

/**
 * Des:       RecruitProgressView
 * Create by: m122469119
 * On:        2018/6/5 11:33
 * Email:     122469119@qq.com
 */
public class RecruitProgressView extends View {

    /**
     * 去掉padding的半径
     */
    private int mRadius;

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
     * 亮点宽度
     */
    private int mSparkleWidth;

    /**
     * 圆弧宽度
     */
    private int mProgressWidth;

    private int mPadding;

    /**
     * 圆心x坐标
     */
    private float mCenterX;

    /**
     * 圆心y坐标
     */
    private float mCenterY;

    private Paint mPaint1;
    private Paint mPaint2;
    private Paint mPaint3;
    private Paint mPaint4;
    private Paint mPaint5;


    private RectF mRectFProgressArc;
    private int mWidth;


    public RecruitProgressView(Context context) {
        this(context, null);
    }

    public RecruitProgressView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RecruitProgressView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        init();
    }

    private void init() {
        Typeface typeface = Typeface.createFromAsset(getContext().getAssets(), "font/fontzipMin.ttf");

        mSparkleWidth = UIHelper.dpToPx(getContext(), 6);

        mProgressWidth = UIHelper.dpToPx(getContext(), 2);


        //最外圆弧背景画笔
        mPaint1 = new Paint();
        mPaint1.setAntiAlias(true);
        mPaint1.setStrokeCap(Paint.Cap.SQUARE);
        mPaint1.setColor(Color.parseColor("#F2F5F9"));
        mPaint1.setStyle(Paint.Style.STROKE);
        mPaint1.setStrokeWidth(mProgressWidth);

        //圆弧画笔
        mPaint2 = new Paint();
        mPaint2.setAntiAlias(true);
        mPaint2.setStrokeCap(Paint.Cap.SQUARE);
        mPaint2.setColor(ContextCompat.getColor(getContext(), R.color.app_primary_color));
        mPaint2.setStyle(Paint.Style.STROKE);
        mPaint2.setStrokeWidth(mProgressWidth);

        //亮点画笔
        mPaint3 = new Paint();
        mPaint3.setAntiAlias(true);
        mPaint3.setStrokeCap(Paint.Cap.SQUARE);
        mPaint3.setColor(ContextCompat.getColor(getContext(), R.color.app_primary_color));
        mPaint3.setStyle(Paint.Style.FILL);
        mPaint3.setStrokeWidth(mProgressWidth);


        //文字画笔
        mPaint4 = new Paint();
        mPaint4.setTypeface(typeface);
        mPaint4.setAntiAlias(true);
        mPaint4.setStrokeCap(Paint.Cap.SQUARE);
        mPaint4.setColor(ContextCompat.getColor(getContext(), R.color.app_primary_color));
        mPaint4.setStyle(Paint.Style.FILL);
        mPaint4.setTextAlign(Paint.Align.CENTER);
        mPaint4.setTextSize(UIHelper.spToPx(getContext(), 17));

        //        //文字画笔2
        //        mPaint5 = new Paint();
        //        mPaint5.setAntiAlias(true);
        //        mPaint5.setStrokeCap(Paint.Cap.SQUARE);
        //        mPaint5.setColor(Color.parseColor("#666666"));
        //        mPaint5.setStyle(Paint.Style.FILL);
        //        mPaint5.setTextAlign(Paint.Align.CENTER);
        //        mPaint5.setTextSize(UIHelper.spToPx(getContext(), 10));

        mRectFProgressArc = new RectF();

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(widthMeasureSpec, widthMeasureSpec);

        mPadding = Math.max(
                Math.max(getPaddingLeft(), getPaddingTop()),
                Math.max(getPaddingRight(), getPaddingBottom())
        );

        mWidth = getMeasuredWidth();

        mRadius = (mWidth - mPadding * 2) / 2;

        mCenterX = mCenterY = mWidth / 2f;


        mRectFProgressArc.set(
                mPadding + mSparkleWidth / 2f,
                mPadding + mSparkleWidth / 2f,
                mWidth - mPadding - mSparkleWidth / 2f,
                mWidth - mPadding - mSparkleWidth / 2f
        );


    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        //画进度圆弧背景
        canvas.drawArc(mRectFProgressArc, mStartAngle, mSweepAngle, false, mPaint1);

        //画进度圆弧
        canvas.drawArc(mRectFProgressArc, mStartAngle, calculateRelativeAngleWithValue(mProgressValue), false, mPaint2);


        //画指示器亮点
        if (mProgressValue != 0 && mProgressValue != 100) {
            float[] point = getCoordinatePoint(mRadius - mSparkleWidth / 2f, mStartAngle + calculateRelativeAngleWithValue(mProgressValue));
            mPaint3.setShader(generateRadialGradient(point[0], point[1]));
            canvas.drawCircle(point[0], point[1], mSparkleWidth / 2f, mPaint3);
        }

        //画实时进度
        canvas.drawText(String.valueOf(mProgressValue), mCenterX, mCenterY + UIHelper.calcTextHeight(mPaint4, String.valueOf(mProgressValue)) / 2, mPaint4);

        //画招聘效率
        //   canvas.drawText("招聘效率", mCenterX, mCenterY + UIHelper.calcTextHeight(mPaint5, String.valueOf("招聘效率")) + UIHelper.dpToPx(getContext(), 6), mPaint5);

    }


    private RadialGradient generateRadialGradient(float x, float y) {
        return new RadialGradient(x, y, mSparkleWidth / 2f,
                new int[]{Color.argb(255, 3, 120, 216), Color.argb(80, 3, 120, 216)},
                new float[]{0.4f, 1},
                Shader.TileMode.CLAMP
        );
    }

    private float[] getCoordinatePoint(float radius, float angle) {
        float[] point = new float[2];

        double arcAngle = Math.toRadians(angle); //将角度转换为弧度
        if (angle < 90) {
            point[0] = (float) (mCenterX + Math.cos(arcAngle) * radius);
            point[1] = (float) (mCenterY + Math.sin(arcAngle) * radius);
        } else if (angle == 90) {
            point[0] = mCenterX;
            point[1] = mCenterY + radius;
        } else if (angle > 90 && angle < 180) {
            arcAngle = Math.PI * (180 - angle) / 180.0;
            point[0] = (float) (mCenterX - Math.cos(arcAngle) * radius);
            point[1] = (float) (mCenterY + Math.sin(arcAngle) * radius);
        } else if (angle == 180) {
            point[0] = mCenterX - radius;
            point[1] = mCenterY;
        } else if (angle > 180 && angle < 270) {
            arcAngle = Math.PI * (angle - 180) / 180.0;
            point[0] = (float) (mCenterX - Math.cos(arcAngle) * radius);
            point[1] = (float) (mCenterY - Math.sin(arcAngle) * radius);
        } else if (angle == 270) {
            point[0] = mCenterX;
            point[1] = mCenterY - radius;
        } else {
            arcAngle = Math.PI * (360 - angle) / 180.0;
            point[0] = (float) (mCenterX + Math.cos(arcAngle) * radius);
            point[1] = (float) (mCenterY - Math.sin(arcAngle) * radius);
        }
        return point;
    }

    /**
     * 相对起始角度计算信用分所对应的角度大小
     */
    private float calculateRelativeAngleWithValue(int value) {
        return mSweepAngle * value * 1f / mMax;
    }


    public int getProgressValue() {
        return mProgressValue;
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


}
