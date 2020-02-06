package com.recycler.recyclerviewall.ui.loadmore;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;


import com.recycler.recyclerviewall.R;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class LoadMoreAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private final int VIEW_TYPE_ITEM = 0;
    private final int VIEW_TYPE_LOADING = 1;
    private List<String> list;
    public LoadMoreAdapter(List<String> list){
        this.list = list;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if(viewType == VIEW_TYPE_ITEM){
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_row,parent,false);
            return new ItemViewHolder(view);
        }else {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_loading,parent,false);
            return new LoadingViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if(holder instanceof ItemViewHolder){
            populateItemRows((ItemViewHolder)holder,position);
        }else if(holder instanceof LoadingViewHolder){
            showLoadingView((LoadingViewHolder) holder, position);
        }
    }


    @Override
    public int getItemCount() {
        return list != null? list.size() : 0;
    }

    @Override
    public int getItemViewType(int position) {
        return list.get(position) !=null ? VIEW_TYPE_ITEM : VIEW_TYPE_LOADING;
    }

    private class ItemViewHolder extends RecyclerView.ViewHolder{
        TextView tvItem;
         ItemViewHolder(@NonNull View itemView) {
            super(itemView);
            tvItem = itemView.findViewById(R.id.tvItem);
        }
    }

    private class LoadingViewHolder extends RecyclerView.ViewHolder{
        ProgressBar progressBar;
         LoadingViewHolder(@NonNull View itemView) {
            super(itemView);
            progressBar = itemView.findViewById(R.id.progressBar);
        }
    }

    private void populateItemRows(ItemViewHolder viewHolder, int position) {
        String item = list.get(position);
        viewHolder.tvItem.setText(item);

    }

    private void showLoadingView(LoadingViewHolder viewHolder, int position) {
        //ProgressBar would be displayed

    }
}
