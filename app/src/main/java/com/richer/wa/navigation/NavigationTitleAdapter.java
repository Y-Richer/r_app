package com.richer.wa.navigation;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.richer.richers.richer_wa.R;
import com.richer.richers.richer_wa.databinding.ItemTitleNavigationBinding;
import com.richer.wa.navigation.model.NavigationTab;

import java.util.List;

public class NavigationTitleAdapter extends RecyclerView.Adapter<NavigationTitleAdapter.ViewHolder> {

    Context context;
    ItemTitleNavigationBinding binding;
    List<NavigationTab> fragmentList;
    NavigationTitleListener listener;
    int selectedPosition;

    public NavigationTitleAdapter(Context context, List<NavigationTab> fragmentList, NavigationTitleListener listener) {
        this.context = context;
        this.fragmentList = fragmentList;
        this.listener = listener;
    }

    public void setSelectedPosition(int selectedPosition) {
        this.selectedPosition = selectedPosition;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View root = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_title_navigation, parent, false);
        binding = DataBindingUtil.bind(root);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        NavigationTab tab = fragmentList.get(position);
        if (position == selectedPosition) {
            holder.binding.tvChapterListClassify.setTextColor(ContextCompat.getColor(context, R.color.color_white_100));
        } else {
            holder.binding.tvChapterListClassify.setTextColor(ContextCompat.getColor(context, R.color.color_808080_50));
        }
        holder.binding.tvChapterListClassify.setText(tab.getTitle());
        holder.binding.tvChapterListClassify.setOnClickListener(v -> listener.onTitleClick(tab.getTitle(), position));
    }

    @Override
    public int getItemCount() {
        return fragmentList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        ItemTitleNavigationBinding binding;
        public ViewHolder(@NonNull ItemTitleNavigationBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

    public interface NavigationTitleListener {
        void onTitleClick(String title, int position);
    }

}
