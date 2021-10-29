package com.richer.wa.view;

import android.content.Context;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatTextView;

import com.richer.wa.utils.UIUtil;

import java.util.List;

/**
 * create by richer on 2021/10/27
 * 自动换行TextView
 * setText()传入一个字符串List, 根据屏幕宽度自动换行
 */
public class AutoNewLineTextView extends AppCompatTextView {

    int specMode;
    int leftMargin;
    int rightMargin;

    public AutoNewLineTextView(@NonNull Context context) {
        super(context);
    }

    public AutoNewLineTextView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public AutoNewLineTextView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    /**
     * wrap_content时需要设置所有边距
     * 单位: dip
     */
    public void setMargins(int leftMargin, int rightMargin) {
        this.leftMargin = UIUtil.dp2px(leftMargin);
        this.rightMargin = UIUtil.dp2px(rightMargin);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        specMode = MeasureSpec.getMode(widthMeasureSpec);
    }

    public void setText(List<String> textList) {
        StringBuilder stringBuilder = new StringBuilder();
        StringBuilder result = new StringBuilder();
        int start = 0;
        int end = 0;
        TextPaint paint = new TextPaint(getPaint());
        int width;
        if (specMode == MeasureSpec.EXACTLY || specMode == MeasureSpec.UNSPECIFIED) {
            width = getMaxWidth();
        } else {
            width = UIUtil.getScreenWidth(getContext()) - leftMargin - rightMargin;
        }
        if (textList != null) {
            for (String text : textList) {
                end = start + text.length() + 1;
                stringBuilder.append(text);
                stringBuilder.append(" ");
                if (paint.measureText(stringBuilder.toString()) > width) {
                    //加上下一项后宽度超出范围
                    //换行
                    stringBuilder.replace(start, end, "\n");
                    result.append(stringBuilder);
                    //下一行重新计算
                    stringBuilder = new StringBuilder(text + " ");
                    start = text.length() + 1;
                } else {
                    start = end;
                }
            }
            //最后一行
            if (stringBuilder.length() > 0) {
                result.append(stringBuilder);
            }
        }
        setText(result.toString());
    }

}
