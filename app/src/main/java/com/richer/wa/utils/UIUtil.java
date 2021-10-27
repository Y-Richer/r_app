package com.richer.wa.utils;

import android.content.Context;
import android.util.DisplayMetrics;

import com.richer.wa.RWApplication;

public class UIUtil {

    public static int dp2px(Context context, float dipValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dipValue * scale + 0.5f);
    }

    public static int dp2px(float dipValue) {
        return dp2px(RWApplication.getAppContext(), dipValue);
    }

    /**
     * 获取屏幕宽度
     * @param context
     * @return
     */
    public static int getScreenWidth(Context context) {
        if (context == null) {
            context = RWApplication.getAppContext();
        }
        DisplayMetrics dm = context.getResources().getDisplayMetrics();
        if (dm == null) {
            return 0;
        } else {
            return dm.widthPixels;
        }
    }
}
