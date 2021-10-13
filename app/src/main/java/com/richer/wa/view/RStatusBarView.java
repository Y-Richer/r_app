package com.richer.wa.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import com.richer.wa.utils.StatusBarUtil;

/**
 * create by richer on 2021/10/12
 * 状态栏View, 适应状态栏高度
 */
public class RStatusBarView extends View {
    public RStatusBarView(Context context) {
        super(context);
    }

    public RStatusBarView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public RStatusBarView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        setMeasuredDimension(widthSize, StatusBarUtil.getStatusBarHeight(getContext()));
    }
}
