package com.mix.recruitefficiencyactionview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.view.View;


public class RecruitEfficiencyView extends View {

    /**
     * 起始角度
     */
    private int mStartAngle = -110;

    /**
     * 绘制角度
     */
    private int mSweepAngle = 220;

    /**
     * 最小值
     */
    private int mMin = 0;
    /**
     * 最大值
     */
    private int mMax = 100;

    /**
     * 招聘效率
     */
    private int mProgress = 0;


    private int mSparkleWidth;


    private float mLength1;


    private int mPadding;

    /**
     * 圆心x
     */
    private float mCenterX;
    /**
     * 圆心y
     */
    private float mCenterY;

    /**
     * 文本画笔
     */
    private Paint mPaint;


    /**
     * 子弹路径
     */
    private Path mPath;


    /**
     * 子弹填充画笔
     */
    private Paint mFillCirclePaint;

    /**
     * 子弹进度填充画笔(白)
     */
    private Paint mProgresCiclePaint;


    /**
     * 阀值
     */
    private float blackMagic = 0.51915024494f;


    /**
     * 贝塞尔垂直上下两点
     */
    private HPoint p1, p3;

    /**
     * 贝塞尔水平左右两点
     */
    private VPoint p2, p4;

    private float cDistance;
    private float radius;
    private float c;


    public RecruitEfficiencyView(Context context) {
        this(context, null);
    }

    public RecruitEfficiencyView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RecruitEfficiencyView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }


    private void init() {

        mSparkleWidth = UIHelper.dpToPx(getContext(), 7);

        Typeface typeface = Typeface.createFromAsset(getContext().getAssets(), "font/fontzipMin.ttf");
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setTextAlign(Paint.Align.CENTER);
        mPaint.setTypeface(typeface);
        mPaint.setColor(Color.WHITE);


        mFillCirclePaint = new Paint();
        mFillCirclePaint.setColor(0xFF0066BB);
        mFillCirclePaint.setStyle(Paint.Style.FILL);
        mFillCirclePaint.setStrokeWidth(1);
        mFillCirclePaint.setAntiAlias(true);


        mProgresCiclePaint = new Paint();
        mProgresCiclePaint.setColor(0xFFFFFFFF);
        mProgresCiclePaint.setStyle(Paint.Style.FILL);
        mProgresCiclePaint.setStrokeWidth(1);
        mProgresCiclePaint.setAntiAlias(true);


        p2 = new VPoint();
        p4 = new VPoint();
        p1 = new HPoint();
        p3 = new HPoint();

        radius = UIHelper.dpToPx(getContext(), 2.8);

        c = radius * blackMagic;
        cDistance = c * 0.48f;

        p1.setY(radius);
        p3.setY(-radius);
        p3.x = p1.x = 0;
        p3.left.x = p1.left.x = -c;
        p3.right.x = p1.right.x = c;

        p2.setX(radius);
        p4.setX(-radius);
        p2.y = p4.y = 0;
        p2.top.y = p4.top.y = -c;
        p2.bottom.y = p4.bottom.y = c;

        p1.setY(radius + radius * 1.3f);

        //拉伸两个控制点的Y坐标
        p2.adjustY(radius / 2 * 0.28f);
        p4.adjustY(radius / 2 * 0.28f);

        //修正控制点的横坐标
        p1.adjustAllX(cDistance);
        p3.adjustAllX(cDistance);


        //生成路径
        mPath = new Path();
        mPath.moveTo(p1.x, p1.y);
        mPath.cubicTo(p1.right.x, p1.right.y, p2.bottom.x, p2.bottom.y, p2.x, p2.y);
        mPath.cubicTo(p2.top.x, p2.top.y, p3.right.x, p3.right.y, p3.x, p3.y);
        mPath.cubicTo(p3.left.x, p3.left.y, p4.top.x, p4.top.y, p4.x, p4.y);
        mPath.cubicTo(p4.bottom.x, p4.bottom.y, p1.left.x, p1.left.y, p1.x, p1.y);

    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        //获取padding
        mPadding = Math.max(
                Math.max(getPaddingLeft(), getPaddingTop()),
                Math.max(getPaddingRight(), getPaddingBottom())
        );


        mLength1 = mPadding + mSparkleWidth;

        //计算圆心
        mCenterX = mCenterY = getMeasuredWidth() / 2f;

    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);


        int cnt = 23;
        int stepAngle = mSweepAngle / 22;


        int cnt2 = cnt * mProgress / 100;


        canvas.save();
        for (int i = 0; i < cnt; i++) {
            canvas.save();
            canvas.rotate(mStartAngle + i * stepAngle, mCenterX, mCenterY);
            canvas.translate(mCenterX, mPadding + mLength1);
            canvas.drawPath(mPath, mFillCirclePaint);
            canvas.restore();
        }
        canvas.restore();


        canvas.save();
        for (int i = 0; i < cnt2; i++) {
            canvas.save();
            canvas.rotate(mStartAngle + i * stepAngle, mCenterX, mCenterY);
            canvas.translate(mCenterX, mPadding + mLength1);
            canvas.drawPath(mPath, mProgresCiclePaint);
            canvas.restore();
        }
        canvas.restore();


        /**
         * 画描述文本
         */
        mPaint.setTextSize(UIHelper.spToPx(getContext(), 16));
        canvas.drawText("综合水平", mCenterX, mCenterY + UIHelper.calcTextHeight(mPaint, "综合水平")
                - UIHelper.dpToPx(getContext(), 5), mPaint);

        /**
         * 画进度文本
         */
        mPaint.setTextSize(UIHelper.spToPx(getContext(), 32));
        String value = String.valueOf(mProgress);
        canvas.drawText(value, mCenterX, mCenterY - UIHelper.dpToPx(getContext(), 15), mPaint);

    }


    public void setProgress(int progress) {
        if (mProgress == progress || progress < mMin || progress > mMax) {
            return;
        }
        mProgress = progress;
        postInvalidateOnAnimation();
    }


    class VPoint {
        public float x;
        public float y;
        public PointF top = new PointF();
        public PointF bottom = new PointF();

        public void setX(float x) {
            this.x = x;
            top.x = x;
            bottom.x = x;
        }

        public void adjustY(float offset) {
            this.y += offset;
            top.y += offset;
            bottom.y += offset;
        }

    }

    class HPoint {
        public float x;
        public float y;
        public PointF left = new PointF();
        public PointF right = new PointF();

        public void setY(float y) {
            this.y = y;
            left.y = y;
            right.y = y;
        }

        public void adjustAllX(float offset) {
            //  this.y += offset;
            left.x -= offset;
            right.x += offset;
        }
    }


}
