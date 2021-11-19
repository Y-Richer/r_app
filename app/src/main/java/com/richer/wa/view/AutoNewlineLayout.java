package com.richer.wa.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;

import java.util.List;

/**
 * create by yangrunqiu on 2021/10/29
 * 自动换行布局
 * 通过setAutoNewlineHelper设置item布局和数据
 *
 * mBinding.autoNewlineHotSearchWord.setAutoNewlineHelper(new AutoNewlineLayout.AutoNewlineHelper<DataBean>() {
 *             @Override
 *             public List<DataBean> getDataList() {
 *                 return dataList;
 *             }
 *
 *             @Override
 *             public View getItemView(ViewGroup container) {
 *                 return LayoutInflater.from(container.getContext()).inflate(R.layout._, container, false);
 *             }
 *
 *             @Override
 *             public void onClickItem(int position, DataBean data) {
 *                 ...
 *             }
 *
 *             @Override
 *             public void bindItemView(View itemView, HotSearchModel.HotSearchBean data) {
 *                 ...
 *             }
 *         });
 *
 */
public class AutoNewlineLayout extends LinearLayout {

    public AutoNewlineLayout(Context context) {
        this(context, null);
    }

    public AutoNewlineLayout(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public AutoNewlineLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public <T> void setAutoNewlineHelper(AutoNewlineHelper<T> helper) {
        removeAllViews();
        List<T> dataList = helper.getDataList();
        for (int i = 0; i < dataList.size(); i++) {
            View itemView = helper.getItemView(this);
            final int position = i;
            helper.bindItemView(itemView, dataList.get(position));
            itemView.setOnClickListener(v -> {
                helper.onClickItem(position, dataList.get(position));
            });
            addView(itemView);
        }
        requestLayout();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);

        int width = 0;
        int height = 0;
        //记录每一行子View宽度总和
        int childWidthSum = 0;
        int childHeightSum = 0;

        //子View数量
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            View child = getChildAt(i);
            measureChild(child, widthMeasureSpec, heightMeasureSpec);
            MarginLayoutParams childParams = (MarginLayoutParams) child.getLayoutParams();
            int childWidth = child.getMeasuredWidth() + childParams.leftMargin + childParams.rightMargin;
            int childHeight = child.getMeasuredHeight() + childParams.topMargin + childParams.bottomMargin;
            if (childWidthSum + childWidth > widthSize - getPaddingLeft() - getPaddingRight()) {
                //最大宽度
                width = Math.max(width, childWidthSum);
                //另起一行
                childWidthSum = childWidth;
                //高度增加一行
                childHeightSum += childHeight;
                height = Math.max(height, childHeightSum);
            } else {
                childWidthSum += childWidth;
                childHeightSum = Math.max(childHeightSum, childHeight);
            }
        }
        width = widthMode == MeasureSpec.EXACTLY ? widthSize : width + getPaddingLeft() + getPaddingRight();
        height = heightMode == MeasureSpec.EXACTLY ? heightSize : height + getPaddingTop() + getPaddingBottom();
        setMeasuredDimension(width, height);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int width = getWidth();
        int childWidthSum = 0;
        int childHeightSum = 0;

        int cl = getPaddingLeft();
        //item距离顶部距离
        int topPadding = getPaddingTop();
        int ct = getPaddingTop();
        int cr = cl;
        int cb = ct;

        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            View child = getChildAt(i);
            int childWidth = child.getMeasuredWidth();
            int childHeight = child.getMeasuredHeight();
            MarginLayoutParams params = (MarginLayoutParams) child.getLayoutParams();
            int leftMargin = params.leftMargin;
            int rightMargin = params.rightMargin;
            int topMargin = params.topMargin;
            int bottomMargin = params.bottomMargin;

            if (childWidthSum + childWidth + leftMargin + rightMargin > width - getPaddingLeft() - getPaddingRight()) {
                childHeightSum = childHeight + bottomMargin + topMargin;
                childWidthSum = 0;
                cl = getPaddingLeft();
                topPadding += childHeightSum;
            }
            cl += leftMargin;
            ct = topMargin + topPadding;
            cr = cl + childWidth;
            cb = ct + childHeight;
            child.layout(cl, ct, cr, cb);
            cl = cr + rightMargin;
            childWidthSum += childWidth + leftMargin + rightMargin;
        }
    }

    public interface AutoNewlineHelper<T> {

        List<T> getDataList();

        View getItemView(ViewGroup container);

        void onClickItem(int position, T data);

        void bindItemView(View itemView, T data);

    }

}
