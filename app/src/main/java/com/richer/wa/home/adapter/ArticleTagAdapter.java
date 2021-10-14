package com.richer.wa.home.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.richer.richers.richer_wa.R;
import com.richer.wa.home.model.ArticleInfo;

import java.util.List;

/**
 * create by richer on 2021/10/14
 * 首页文章列表——文章标签adapter
 */
public class ArticleTagAdapter extends RecyclerView.Adapter<ArticleTagAdapter.ViewHolder> {

    private List<ArticleInfo.ArticleTag> tagList;

    public ArticleTagAdapter(List<ArticleInfo.ArticleTag> tagList) {
        this.tagList = tagList;
    }

    public void setTagList(List<ArticleInfo.ArticleTag> tagList) {
        this.tagList = tagList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View root = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_article_tag_home, parent, false);
        return new ViewHolder(root);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ArticleInfo.ArticleTag tag = tagList.get(position);
        holder.mTagTv.setText(tag.getName());
    }

    @Override
    public int getItemCount() {
        return tagList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        TextView mTagTv;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mTagTv = itemView.findViewById(R.id.tv_article_tag_home);
        }
    }

}
