package com.richer.wa.classify;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.richer.richers.richer_wa.R;
import com.richer.richers.richer_wa.databinding.ActivityClassifyBinding;
import com.richer.wa.base.BaseFragment;
import com.richer.wa.base.BaseWebViewActivity;
import com.richer.wa.classify.adapter.ClassifyArticleAdapter;
import com.richer.wa.classify.adapter.ClassifyChapterAdapter;
import com.richer.wa.classify.model.ClassifyModel;
import com.richer.wa.classify.view_model.ClassifyViewModel;
import com.richer.wa.home.model.ArticleInfo;

import java.util.ArrayList;
import java.util.List;

public class ClassifyFragment extends BaseFragment<ClassifyViewModel> implements ClassifyChapterAdapter.ClassifyChapterListener, ClassifyArticleAdapter.ClassifyArticleListener {
    @Override
    protected void setClazz() {
        clazz = ClassifyViewModel.class;
    }

    ActivityClassifyBinding mBinding;
    List<ClassifyModel.ChapterBean> mClassifyChapterList = new ArrayList<>();
    ClassifyChapterAdapter mChapterAdapter;
    ClassifyArticleAdapter mArticleAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = LayoutInflater.from(container.getContext()).inflate(R.layout.activity_classify, container, false);
        mBinding = DataBindingUtil.bind(root);
        return root;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        initData();
        initView();
    }

    private void initData() {
        mViewModel.getClassifyArticle();
        mViewModel.classifyArticleLiveData.observe(getViewLifecycleOwner(), classifyModel -> {
            if (classifyModel.getData() != null) {
                mClassifyChapterList = classifyModel.getData();
                mChapterAdapter.setChapterList(mClassifyChapterList);
                mArticleAdapter.setChapterList(mClassifyChapterList);
                refreshView();
            }
        });
    }

    private void initView() {
        mChapterAdapter = new ClassifyChapterAdapter(getContext(), mClassifyChapterList, this);
        LinearLayoutManager manager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        mBinding.rvChapterClassify.setLayoutManager(manager);
        mBinding.rvChapterClassify.setAdapter(mChapterAdapter);

        mArticleAdapter = new ClassifyArticleAdapter(mClassifyChapterList, this);
        manager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
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
        BaseWebViewActivity.start(getContext(), data.getLink(), data.getTitle());
    }
}
