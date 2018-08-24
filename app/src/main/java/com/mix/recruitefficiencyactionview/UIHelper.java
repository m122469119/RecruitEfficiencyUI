package com.mix.recruitefficiencyactionview;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.StateListDrawable;
import android.os.Build;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.ViewGroup;
import android.view.WindowManager;


/**
 * 作者：鬰  on 2017/2/18 16:19
 * 邮箱：122469119@qq.com
 */
public class UIHelper {

    public static DisplayMetrics getDisplayMetrics(Context context) {
        return context.getResources().getDisplayMetrics();
    }


    public static int dpToPx(Context context, int dpValue) {
        return (int) (dpValue * getDisplayMetrics(context).density + 0.5f);
    }

    public static float dpToPx(Context context, float dp) {
        return dp * getDisplayMetrics(context).density;
    }

    public static int dpToPx(Context context, double dpValue) {
        return (int) (dpValue * getDisplayMetrics(context).density + 0.5f);
    }


    public static float spToPx(Context context, float sp) {
        DisplayMetrics metrics = getDisplayMetrics(context);
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, sp, metrics);
    }


    public static float getScreenHeight(Context context) {
        return getDisplayMetrics(context).heightPixels;
    }

    public static float getScreenWidth(Context context) {
        return getDisplayMetrics(context).widthPixels;
    }

    /**
     * 获得状态栏的高度
     */
    public static int getStatusBarHeight(Context context) {
        Resources resources = context.getResources();
        int resourceId = resources.getIdentifier("status_bar_height", "dimen", "android");
        int height = resources.getDimensionPixelSize(resourceId);
        return height;
    }


    /**
     * 是否全屏
     *
     * @param activity
     * @return
     */
    public static boolean isFullScreen(final Activity activity) {
        return (activity.getWindow().getAttributes().flags &
                WindowManager.LayoutParams.FLAG_FULLSCREEN) != 0;
    }

    /**
     * 是否透明
     *
     * @param activity
     * @return
     */
    public static boolean isTranslucentStatus(final Activity activity) {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT &&
                (activity.getWindow().getAttributes().flags & WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS) != 0;
    }


    public static boolean isFitsSystemWindows(final Activity activity) {
        return ((ViewGroup) activity.findViewById(android.R.id.content)).getChildAt(0).getFitsSystemWindows();
    }


    public static GradientDrawable getShape(int shape, int radius, int argb) {
        GradientDrawable gd = new GradientDrawable();
        gd.setShape(shape);// 设置形状
        gd.setCornerRadius(radius);// 设置圆角
        gd.setColor(argb);
        return gd;
    }

    public static StateListDrawable getSelector(Drawable normalBg, Drawable pressedBg) {
        StateListDrawable selector = new StateListDrawable();
        selector.addState(new int[]{android.R.attr.state_pressed}, pressedBg);
        selector.addState(new int[]{}, normalBg);
        return selector;
    }


    private static Rect mCalcTextHeightRect = new Rect();

    public static int calcTextHeight(Paint paint, String demoText) {

        Rect r = mCalcTextHeightRect;
        r.set(0, 0, 0, 0);
        paint.getTextBounds(demoText, 0, demoText.length(), r);
        return r.height();
    }

    public static int calcTextWidth(Paint paint, String demoText) {
        return (int) paint.measureText(demoText);
    }

}
