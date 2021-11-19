package com.richer.wa.classify;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.richer.richers.richer_wa.R;
import com.richer.richers.richer_wa.databinding.ActivityClassifyBinding;
import com.richer.wa.CommonHeaderView;
import com.richer.wa.base.BaseActivity;
import com.richer.wa.base.BaseWebViewActivity;
import com.richer.wa.classify.adapter.ClassifyArticleAdapter;
import com.richer.wa.classify.adapter.ClassifyChapterAdapter;
import com.richer.wa.classify.model.ClassifyModel;
import com.richer.wa.classify.view_model.ClassifyViewModel;
import com.richer.wa.home.model.ArticleInfo;

import java.util.ArrayList;
import java.util.List;

public class ClassifyActivity extends BaseActivity<ClassifyViewModel> implements ClassifyChapterAdapter.ClassifyChapterListener, ClassifyArticleAdapter.ClassifyArticleListener {
    @Override
    protected void setClazz() {
        clazz = ClassifyViewModel.class;
    }

    public static void start(Context context) {
        Intent intent = new Intent(context, ClassifyActivity.class);
        context.startActivity(intent);
    }

    ActivityClassifyBinding mBinding;
    List<ClassifyModel.ChapterBean> mClassifyChapterList = new ArrayList<>();
    ClassifyChapterAdapter mChapterAdapter;
    ClassifyArticleAdapter mArticleAdapter;

    @Override
    protected void initActivity() {
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_classify);
        initData();
        initView();
    }

    private void initData() {
        mViewModel.getClassifyArticle();
        mViewModel.classifyArticleLiveData.observe(this, classifyModel -> {
            if (classifyModel.getData() != null) {
                mClassifyChapterList = classifyModel.getData();
                mChapterAdapter.setChapterList(mClassifyChapterList);
                mArticleAdapter.setChapterList(mClassifyChapterList);
                refreshView();
            }
        });
    }

    private void initView() {
        mBinding.headerClassify.init("导航", new CommonHeaderView.OnHeaderClickListener() {
            @Override
            public void onClickBack(View view) {
                finish();
            }
        });
        mChapterAdapter = new ClassifyChapterAdapter(this, mClassifyChapterList, this);
        LinearLayoutManager manager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mBinding.rvChapterClassify.setLayoutManager(manager);
        mBinding.rvChapterClassify.setAdapter(mChapterAdapter);

        mArticleAdapter = new ClassifyArticleAdapter(mClassifyChapterList, this);
        manager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mBinding.rvCatalogClassify.setLayoutManager(manager);
        mBinding.rvCatalogClassify.setAdapter(mArticleAdapter);
        mBinding.rvCatalogClassify.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                LinearLayoutManager layoutManager = (LinearLayoutManager) mBinding.rvCatalogClassify.getLayoutManager();
                int firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition();
                mChapterAdapter.setSelectedPosition(firstVisibleItemPosition);
                mChapterAdapter.notifyDataSetChanged();
            }
        });
    }

    private void refreshView() {
        mChapterAdapter.notifyDataSetChanged();
        mArticleAdapter.notifyDataSetChanged();
    }

    @Override
    public void onChapterClick(ClassifyModel.ChapterBean chapter, int position) {
        mChapterAdapter.setSelectedPosition(position);
        ((LinearLayoutManager) mBinding.rvCatalogClassify.getLayoutManager()).scrollToPositionWithOffset(position, 0);
    }

    @Override
    public void onArticleClick(int position, ArticleInfo data) {
        BaseWebViewActivity.start(this, data.getLink(), data.getTitle());
    }
}
