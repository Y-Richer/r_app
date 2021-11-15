package com.richer.wa.home.adapter;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.richer.richers.richer_wa.R;
import com.richer.richers.richer_wa.databinding.ItemArticleInfoHomeBinding;
import com.richer.wa.home.model.ArticleInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * create by richer on 2021/10/14
 * 首页文章列表adapter
 */
public class ArticleAdapter extends RecyclerView.Adapter<ArticleAdapter.ViewHolder> {

    private final String TEXT_AUTHOR = "作者";
    private final String TEXT_SHARE_USER = "分享人";
    private final String TEXT_CLASSIFY = "分类";
    private final String TEXT_TIME = "时间";

    private List<ArticleInfo> commonArticleList = new ArrayList<>();
    private List<ArticleInfo> topArticleList = new ArrayList<>();
    private List<ArticleInfo> articleList = new ArrayList<>();
    private OnArticleClickListener clickListener;

    public ArticleAdapter(OnArticleClickListener clickListener) {
        this.clickListener = clickListener;
    }

    private void resetArticleList() {
        articleList.clear();
        articleList.addAll(topArticleList);
        articleList.addAll(commonArticleList);
    }

    public void setCommonArticleList(List<ArticleInfo> commonArticleList) {
        this.commonArticleList = commonArticleList;
        resetArticleList();
    }

    public void setTopArticleList(List<ArticleInfo> topArticleList) {
        this.topArticleList = topArticleList;
        resetArticleList();
    }

    public void addCommonArticleList(List<ArticleInfo> commonArticleList) {
        this.commonArticleList.addAll(commonArticleList);
        resetArticleList();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View root = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_article_info_home, parent, false);
        return new ViewHolder(root);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ArticleInfo article = articleList.get(position);

        //点击事件
        holder.mBinding.clArticleItem.setOnClickListener(v -> {
            clickListener.clickArticle(article);
        });

        holder.mBinding.tvTitleArticle.setText(article.getTitle());
        //展示new标签
        if (article.isFresh()) {
            holder.mBinding.tvNewArticle.setVisibility(View.VISIBLE);
        } else {
            holder.mBinding.tvNewArticle.setVisibility(View.GONE);
        }
        //展示置顶标签
        if (article.isTop()) {
            holder.mBinding.tvTopArticle.setVisibility(View.VISIBLE);
        } else {
            holder.mBinding.tvTopArticle.setVisibility(View.GONE);
        }
        //展示标签
        if (article.getTags().size() == 0) {
            holder.mBinding.atvArticleTagHome.setVisibility(View.GONE);
        } else {
            holder.mBinding.atvArticleTagHome.setVisibility(View.VISIBLE);
            holder.mBinding.atvArticleTagHome.setData(article.getTags());
        }

        //展示作者或分享人
        if (!TextUtils.isEmpty(article.getAuthor())) {
            holder.mBinding.tvArticleAuthorKey.setText(TEXT_AUTHOR);
            holder.mBinding.tvArticleAuthorValue.setText(article.getAuthor());
        } else if (!TextUtils.isEmpty(article.getShareUser())) {
            holder.mBinding.tvArticleAuthorKey.setText(TEXT_SHARE_USER);
            holder.mBinding.tvArticleAuthorValue.setText(article.getShareUser());
        }

        //分类
        String chapterName = "";
        if (!TextUtils.isEmpty(article.getSuperChapterName())) {
            chapterName = article.getSuperChapterName() + "/";
        }
        if (!TextUtils.isEmpty(article.getChapterName())) {
            chapterName += article.getChapterName();
        }
        holder.mBinding.tvArticleClassifyKey.setText(TEXT_CLASSIFY);
        holder.mBinding.tvArticleClassifyValue.setText(chapterName);

        //时间
        holder.mBinding.tvArticleTimeKey.setText(TEXT_TIME);
        holder.mBinding.tvArticleTimeValue.setText(article.getNiceDate());

    }

    @Override
    public int getItemCount() {
        return commonArticleList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        ItemArticleInfoHomeBinding mBinding;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mBinding = DataBindingUtil.bind(itemView);
        }
    }

    public interface OnArticleClickListener {
        void clickArticle(ArticleInfo article);
    }

}
