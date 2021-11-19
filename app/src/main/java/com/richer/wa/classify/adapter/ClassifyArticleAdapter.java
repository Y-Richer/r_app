package com.richer.wa.classify.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.richer.richers.richer_wa.R;
import com.richer.richers.richer_wa.databinding.ItemClassifyArticleBinding;
import com.richer.wa.classify.model.ClassifyModel;
import com.richer.wa.home.model.ArticleInfo;
import com.richer.wa.view.AutoNewlineLayout;

import java.util.List;

public class ClassifyArticleAdapter extends RecyclerView.Adapter<ClassifyArticleAdapter.ViewHolder> {

    List<ClassifyModel.ChapterBean> chapterList;
    ClassifyArticleListener listener;
    ItemClassifyArticleBinding binding;

    public ClassifyArticleAdapter(List<ClassifyModel.ChapterBean> chapterList, ClassifyArticleListener listener) {
        this.chapterList = chapterList;
        this.listener = listener;
    }

    public void setChapterList(List<ClassifyModel.ChapterBean> chapterList) {
        this.chapterList = chapterList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View root = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_classify_article, parent, false);
        binding = DataBindingUtil.bind(root);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ClassifyModel.ChapterBean bean = chapterList.get(position);
        holder.binding.tvChapterNameClassify.setText(bean.getName());
        initArticleLayout(bean.getArticles(), holder);
    }

    @Override
    public int getItemCount() {
        return chapterList.size();
    }

    private void initArticleLayout(List<ArticleInfo> articleList, ViewHolder holder) {
        holder.binding.anlArticlesClassify.setAutoNewlineHelper(new AutoNewlineLayout.AutoNewlineHelper<ArticleInfo>() {
            @Override
            public List<ArticleInfo> getDataList() {
                return articleList;
            }

            @Override
            public View getItemView(ViewGroup container) {
                return LayoutInflater.from(container.getContext()).inflate(R.layout.item_article_name_classify, container, false);
            }

            @Override
            public void onClickItem(int position, ArticleInfo data) {
                listener.onArticleClick(position, data);
            }

            @Override
            public void bindItemView(View itemView, ArticleInfo data) {
                TextView tvArticleName = itemView.findViewById(R.id.tv_article_name_classify);
                tvArticleName.setText(data.getTitle());
            }
        });
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        ItemClassifyArticleBinding binding;
        public ViewHolder(@NonNull ItemClassifyArticleBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

    public interface ClassifyArticleListener {
        void onArticleClick(int position, ArticleInfo data);
    }

}
