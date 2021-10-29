package com.richer.wa.search.view;

import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.richer.richers.richer_wa.R;
import com.richer.richers.richer_wa.databinding.ActivitySearchBinding;
import com.richer.wa.CommonHeaderView;
import com.richer.wa.base.BaseActivity;
import com.richer.wa.base.BaseWebViewActivity;
import com.richer.wa.home.adapter.ArticleAdapter;
import com.richer.wa.home.model.ArticleInfo;
import com.richer.wa.search.model.HotSearchModel;
import com.richer.wa.search.view_model.SearchViewModel;
import com.richer.wa.view.AutoNewlineLayout;

import java.util.ArrayList;
import java.util.List;

public class SearchActivity extends BaseActivity<SearchViewModel> {

    ActivitySearchBinding mBinding;

    String mSearchKey;
    List<ArticleInfo> mSearchArticleList;
    ArticleAdapter mAdapter;

    @Override
    public void setClazz() {
        clazz = SearchViewModel.class;
    }

    @Override
    protected void initActivity() {
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_search);
        showSoftKeyboard();
        initHeader();
        initObserve();
        initView();
        initData();
    }

    private void showSoftKeyboard() {
        mBinding.etSearch.setFocusable(true);
        mBinding.etSearch.setFocusableInTouchMode(true);
        mBinding.etSearch.requestFocus();
    }

    private void initData() {
        mViewModel.getHotSearchWord();
    }

    private void initObserve() {
        mViewModel.hotSearchModel().observe(this, hotSearchModel -> {
            if (hotSearchModel != null && hotSearchModel.getData() != null) {
                initHotSearchWord(hotSearchModel.getData());
            }
        });
        mViewModel.searchArticles().observe(this, articleListResponseModel -> {
            if (articleListResponseModel != null &&
                    articleListResponseModel.getData() != null &&
                    articleListResponseModel.getData().getDatas() != null &&
                    articleListResponseModel.getData().getDatas().size() > 0) {
                showSearchResult(articleListResponseModel.getData().getDatas());
            } else {
                showSearchError();
            }
        });
    }

    private void initView() {
        mBinding.ivResetSearch.setVisibility(View.GONE);
        mBinding.etSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                showHotSearch();
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!TextUtils.isEmpty(mBinding.etSearch.getText())) {
                    mBinding.ivResetSearch.setVisibility(View.VISIBLE);
                } else {
                    mBinding.ivResetSearch.setVisibility(View.GONE);
                }
            }
        });
        showHotSearch();

        if (bundle != null) {
            mSearchKey = bundle.getString("search_key");
        } else {
            mSearchKey = "";
        }

        mSearchArticleList = new ArrayList<>();
        LinearLayoutManager manager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mBinding.rvResultSearch.setLayoutManager(manager);
        mAdapter = new ArticleAdapter(article -> {
            BaseWebViewActivity.start(SearchActivity.this, article.getLink(), article.getTitle());
        });
        mAdapter.setCommonArticleList(mSearchArticleList);
        mBinding.rvResultSearch.setAdapter(mAdapter);
    }

    private void doSearch() {
        mBinding.etSearch.setText(mSearchKey);
        mBinding.etSearch.setSelection(mSearchKey.length());
        mViewModel.doSearchArticle(true, mSearchKey);
    }

    private void showSearchResult(List<ArticleInfo> articles) {
        if (articles != null && articles.size() > 0) {
            mBinding.layoutHotSearch.setVisibility(View.GONE);
            mBinding.rvResultSearch.setVisibility(View.VISIBLE);
            mBinding.tvSearchFail.setVisibility(View.GONE);
            mAdapter.addCommonArticleList(articles);
            mAdapter.notifyDataSetChanged();
        }
    }

    private void showHotSearch() {
        mBinding.layoutHotSearch.setVisibility(View.VISIBLE);
        mBinding.rvResultSearch.setVisibility(View.GONE);
        mBinding.tvSearchFail.setVisibility(View.GONE);
    }

    private void showSearchError() {
        mBinding.layoutHotSearch.setVisibility(View.GONE);
        mBinding.rvResultSearch.setVisibility(View.GONE);
        mBinding.tvSearchFail.setVisibility(View.VISIBLE);
    }

    private void initHeader() {
        mBinding.headerSearch.init("搜索", new CommonHeaderView.OnHeaderClickListener() {
            @Override
            public void onClickBack(View view) {
                finish();
            }

            @Override
            public void onClickMenu(View view) {
            }
        });
    }

    private void initHotSearchWord(List<HotSearchModel.HotSearchBean> dataList) {
        mBinding.autoNewlineHotSearchWord.setAutoNewlineHelper(new AutoNewlineLayout.AutoNewlineHelper<HotSearchModel.HotSearchBean>() {
            @Override
            public List<HotSearchModel.HotSearchBean> getDataList() {
                return dataList;
            }

            @Override
            public View getItemView(ViewGroup container) {
                return LayoutInflater.from(container.getContext()).inflate(R.layout.item_hot_search_word, container, false);
            }

            @Override
            public void onClickItem(int position, HotSearchModel.HotSearchBean data) {
                mSearchKey = data.getName();
                doSearch();
            }

            @Override
            public void bindItemView(View itemView, HotSearchModel.HotSearchBean data) {
                TextView hotSearchWordTv = itemView.findViewById(R.id.tv_hot_search_word);
                hotSearchWordTv.setText(data.getName());
            }
        });
    }

    public void onClickDoSearch(View view) {
        mSearchKey = mBinding.etSearch.getText().toString();
        doSearch();
    }

    public void onClickResetSearch(View view) {
        mBinding.etSearch.setText("");
    }
}
