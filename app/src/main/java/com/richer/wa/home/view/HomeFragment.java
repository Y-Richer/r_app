package com.richer.wa.home.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.richer.richers.richer_wa.R;
import com.richer.richers.richer_wa.databinding.FragmentHomeBinding;
import com.richer.wa.RWViewModelFactory;
import com.richer.wa.base.BaseFragment;
import com.richer.wa.base.BaseWebViewActivity;
import com.richer.wa.home.adapter.ArticleAdapter;
import com.richer.wa.home.model.ArticleInfo;
import com.richer.wa.home.view_model.HomeViewModel;
import com.richer.wa.network.NetWorkUtil;
import com.scwang.smart.refresh.layout.api.RefreshLayout;
import com.scwang.smart.refresh.layout.listener.OnLoadMoreListener;
import com.scwang.smart.refresh.layout.listener.OnRefreshListener;

import java.util.List;

/**
 * create by richer on 2021/10/14
 * 首页
 */
public class HomeFragment extends BaseFragment implements ArticleAdapter.OnArticleClickListener {

    private HomeViewModel mViewModel;
    private FragmentHomeBinding mBinding;
    private ArticleAdapter adapter;

    private List<ArticleInfo> commonArticleList;
    private List<ArticleInfo> topArticleList;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mViewModel = new ViewModelProvider(getViewModelStore(),
                new RWViewModelFactory(NetWorkUtil.getHomeApi())).get(HomeViewModel.class);
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
                mViewModel.getArticleList(true);
                mViewModel.getTopArticleList();
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
    public void onClick(ArticleInfo article) {
        String url = article.getLink();
        String title = article.getTitle();
        BaseWebViewActivity.start(getContext(), url, title);
    }

}
