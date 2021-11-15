package com.richer.wa.view;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.ColorInt;

import com.richer.richers.richer_wa.R;
import com.richer.wa.utils.UIUtil;

import java.util.List;

public class ExpandableButton extends View {

    private final float DEFAULT_BUTTON_WIDTH = UIUtil.dp2px(50);
    private final float DEFAULT_BUTTON_HEIGHT = UIUtil.dp2px(50);

    float mButtonWidth;
    float mButtonHeight;
    @ColorInt
    int mButtonColor;
    @ColorInt
    int mBackgroundColor;

    Paint mPaint;
    boolean isExpand;
    ExpandHelper mHelper;

    public ExpandableButton(Context context) {
        this(context, null);
    }

    public ExpandableButton(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ExpandableButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        this.setOnLongClickListener(v -> {
            isExpand = true;
            invalidate();
            return true;
        });

        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.ExpandableButton);
        mButtonWidth = array.getDimension(R.styleable.ExpandableButton_button_width, DEFAULT_BUTTON_WIDTH);
        mButtonHeight = array.getDimension(R.styleable.ExpandableButton_button_height, DEFAULT_BUTTON_HEIGHT);
        mButtonColor = array.getColor(R.styleable.ExpandableButton_button_color, Color.GRAY);
        mBackgroundColor = array.getColor(R.styleable.ExpandableButton_background_color, Color.parseColor("#2B2B2B"));
        array.recycle();

    }

    public void setExpandHelper(ExpandHelper helper) {
        this.mHelper = helper;
        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        mPaint.setStyle(Paint.Style.FILL);
        float radius = Math.min(mButtonWidth, mButtonHeight) / 2;
        if (isExpand) {
            mPaint.setColor(mHelper.getExpansionColor());
            float startAngle = -30f;
            float sweepAngle = 60f;
            for (int i = 0; i < 4; i++) {
                canvas.drawArc(0, 0, mButtonWidth * 3, mButtonHeight * 3, startAngle, sweepAngle, true, mPaint);
                startAngle += sweepAngle + 30;
            }
        }
        mPaint.setColor(Color.parseColor("#00FFFFFF"));
        canvas.drawRect(0, mButtonHeight * 3 / 2, mButtonWidth * 3, mButtonHeight * 3, mPaint);
        mPaint.setColor(mButtonColor);
        canvas.drawCircle(mButtonWidth * 3 / 2, mButtonHeight * 3 / 2, radius, mPaint);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int width = (int) mButtonWidth * 3;
        int height = (int) mButtonHeight * 2;
        setMeasuredDimension(width, height);
    }

    public interface ExpandHelper {
        //展开界面背景色
        @ColorInt
        int getExpansionColor();

        //展开标题
        List<String> getExpansionTitle();

        //点击事件
        void onClickExpansion(int position);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                break;
            case MotionEvent.ACTION_MOVE:
                break;
            case MotionEvent.ACTION_UP:
                isExpand = false;
                invalidate();
                break;
        }
        return super.onTouchEvent(event);
    }

}
