package com.richer.wa.home.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.richer.richers.richer_wa.R;
import com.richer.richers.richer_wa.databinding.LayoutArticleTagHomeBinding;
import com.richer.wa.home.adapter.ArticleTagAdapter;
import com.richer.wa.home.model.ArticleInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * create by richer on 2021/10/14
 * 首页文章item——标签view
 */
public class ArticleTagView extends LinearLayout {

    private LayoutArticleTagHomeBinding mBinding;
    private List<ArticleInfo.ArticleTag> tagList;

    private RecyclerView mArticleTagRv;
    private ArticleTagAdapter adapter;

    public ArticleTagView(Context context) {
        this(context, null);
    }

    public ArticleTagView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mBinding = LayoutArticleTagHomeBinding.inflate(LayoutInflater.from(context));
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
        addView(mBinding.getRoot(), params);
        initView(mBinding.getRoot());
    }

    private void initView(View itemView) {
        mArticleTagRv = itemView.findViewById(R.id.rv_article_tag_home);
        tagList = new ArrayList<>();
        LinearLayoutManager manager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        adapter = new ArticleTagAdapter(tagList);
        mArticleTagRv.setLayoutManager(manager);
        mArticleTagRv.setAdapter(adapter);
    }

    public void setData(List<ArticleInfo.ArticleTag> tagList) {
        this.tagList = tagList;
        adapter.setTagList(tagList);
        adapter.notifyDataSetChanged();
    }

}
