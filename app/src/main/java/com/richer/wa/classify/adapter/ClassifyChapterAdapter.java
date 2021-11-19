package com.richer.wa.classify.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.richer.richers.richer_wa.R;
import com.richer.richers.richer_wa.databinding.ItemClassifyChapterBinding;
import com.richer.wa.classify.model.ClassifyModel;

import java.util.List;

public class ClassifyChapterAdapter extends RecyclerView.Adapter<ClassifyChapterAdapter.ViewHolder> {

    Context context;
    ItemClassifyChapterBinding mBinding;
    List<ClassifyModel.ChapterBean> chapterList;
    ClassifyChapterListener listener;
    int selectedPosition = -1;

    public ClassifyChapterAdapter(Context context, List<ClassifyModel.ChapterBean> chapterList, ClassifyChapterListener listener) {
        this.context = context;
        this.chapterList = chapterList;
        this.listener = listener;
    }

    public void setChapterList(List<ClassifyModel.ChapterBean> chapterList) {
        this.chapterList = chapterList;
    }

    public void setSelectedPosition(int position) {
        selectedPosition = position;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View root = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_classify_chapter, parent, false);
        mBinding = DataBindingUtil.bind(root);
        return new ViewHolder(mBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ClassifyModel.ChapterBean chapter = chapterList.get(position);
        if (position == selectedPosition) {
            holder.binding.tvChapterListClassify.setTextColor(ContextCompat.getColor(context, R.color.color_blue_100));
        } else {
            holder.binding.tvChapterListClassify.setTextColor(ContextCompat.getColor(context, R.color.color_white_100));
        }
        holder.binding.tvChapterListClassify.setText(chapter.getName());
        holder.binding.tvChapterListClassify.setOnClickListener(v -> {
            selectedPosition = position;
            listener.onChapterClick(chapter, position);
        });
    }

    @Override
    public int getItemCount() {
        return chapterList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        ItemClassifyChapterBinding binding;

        public ViewHolder(@NonNull ItemClassifyChapterBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

    public interface ClassifyChapterListener {
        void onChapterClick(ClassifyModel.ChapterBean chapter, int position);
    }

}
