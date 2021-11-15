package com.richer.wa.home;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.richer.richers.richer_wa.R;
import com.richer.richers.richer_wa.databinding.FragmentHomeBinding;
import com.richer.wa.MainActivity;
import com.richer.wa.base.BaseFragment;
import com.richer.wa.base.BaseWebViewActivity;
import com.richer.wa.eventbus.event.DataChangeEvent;
import com.richer.wa.home.adapter.ArticleAdapter;
import com.richer.wa.home.model.ArticleInfo;
import com.richer.wa.home.view_model.HomeViewModel;
import com.scwang.smart.refresh.layout.api.RefreshLayout;
import com.scwang.smart.refresh.layout.listener.OnLoadMoreListener;
import com.scwang.smart.refresh.layout.listener.OnRefreshListener;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

/**
 * create by richer on 2021/10/14
 * 首页
 */
public class HomeFragment extends BaseFragment<HomeViewModel> implements ArticleAdapter.OnArticleClickListener {

    private FragmentHomeBinding mBinding;
    private ArticleAdapter adapter;

    private List<ArticleInfo> commonArticleList;
    private List<ArticleInfo> topArticleList;

    private MainActivity mActivity;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        try {
            mActivity = (MainActivity) context;
        } catch (Exception e) {
            mActivity = null;
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mActivity = null;
    }

    @Override
    protected void setClazz() {
        clazz = HomeViewModel.class;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        mBinding = DataBindingUtil.bind(root);
        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        initObserve();
        initData();
        initView();
    }

    private void initObserve() {
        if (mViewModel != null) {
            mViewModel.getRefreshArticles().observe(getViewLifecycleOwner(), articleModel -> {
                if (articleModel != null && articleModel.getData() != null) {
                    commonArticleList = articleModel.getData().getDatas();
                    if (commonArticleList != null) {
                        adapter.setCommonArticleList(commonArticleList);
                        adapter.notifyDataSetChanged();
                    }
                }
            });
            mViewModel.refreshFinish().observe(getViewLifecycleOwner(), refreshFinish -> {
                if (refreshFinish) {
                    mBinding.refreshLayoutHome.finishRefresh();
                }
            });
            mViewModel.addRefreshArticles().observe(getViewLifecycleOwner(), articleModel -> {
                if (articleModel != null && articleModel.getData() != null) {
                    commonArticleList.addAll(articleModel.getData().getDatas());
                    if (commonArticleList != null) {
                        adapter.setCommonArticleList(commonArticleList);
                        adapter.notifyDataSetChanged();
                    }
                }
            });
            mViewModel.loadMoreFinish().observe(getViewLifecycleOwner(), loadMoreFinish -> {
                if (loadMoreFinish) {
                    mBinding.refreshLayoutHome.finishLoadMore();
                }
            });
            mViewModel.addTopArticles().observe(getViewLifecycleOwner(), articleModel -> {
                if (articleModel != null) {
                    topArticleList = articleModel.getTopArticles();
                    if (topArticleList != null) {
                        for (ArticleInfo article : topArticleList) {
                            article.setTop(true);
                        }
                        adapter.setTopArticleList(topArticleList);
                        adapter.notifyDataSetChanged();
                    }
                }
            });
        }
    }

    private void initData() {
        if (mViewModel != null) {
            mViewModel.getArticleList(true);
            mViewModel.getTopArticleList();
            EventBus.getDefault().post(new DataChangeEvent());
        }
    }

    private void initView() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        mBinding.rvArticleListHome.setLayoutManager(layoutManager);
        adapter = new ArticleAdapter(this);
        mBinding.rvArticleListHome.setAdapter(adapter);

        mBinding.refreshLayoutHome.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                initData();
            }
        });
        mBinding.refreshLayoutHome.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                mViewModel.getArticleList(false);
            }
        });

    }

    @Override
    public void clickArticle(ArticleInfo article) {
        String url = article.getLink();
        String title = article.getTitle();
        BaseWebViewActivity.start(getContext(), url, title);
    }

}
