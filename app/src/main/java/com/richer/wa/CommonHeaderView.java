package com.richer.wa;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;

import com.richer.richers.richer_wa.databinding.LayoutHeaderCommonBinding;
import com.richer.wa.utils.StatusBarUtil;

/**
 * create by richer on 2021/10/15
 * 通用头部
 * 包含: 回退, 标题, 菜单
 * 菜单icon支持自定义图标
 */
public class CommonHeaderView extends LinearLayout {

    LayoutHeaderCommonBinding mBinding;

    public CommonHeaderView(Context context) {
        this(context, null);
    }

    public CommonHeaderView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CommonHeaderView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mBinding = LayoutHeaderCommonBinding.inflate(LayoutInflater.from(context));
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
        addView(mBinding.getRoot(), params);
    }

    public void init(String title, OnHeaderClickListener clickListener) {
        //根据状态栏高度下移header
        ViewGroup.LayoutParams params = mBinding.view.getLayoutParams();
        params.height = StatusBarUtil.getStatusBarHeight(getContext());
        params.width = 0;
        mBinding.view.setLayoutParams(params);

        mBinding.tvTitleHeader.setText(title);
        if (clickListener != null) {
            mBinding.ivBackHeader.setOnClickListener(clickListener::onClickBack);
            mBinding.ivMenuHeader.setOnClickListener(clickListener::onClickMenu);
        }
    }

    public void setMenuImage(Drawable drawable) {
        mBinding.ivMenuHeader.setImageDrawable(drawable);
    }

    public interface OnHeaderClickListener {
        void onClickBack(View view);

        void onClickMenu(View view);
    }

}
