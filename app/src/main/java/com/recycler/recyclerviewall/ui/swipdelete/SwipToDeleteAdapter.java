package com.recycler.recyclerviewall.ui.swipdelete;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.recycler.recyclerviewall.R;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class SwipToDeleteAdapter extends RecyclerView.Adapter<SwipToDeleteAdapter.MyViewHolder> {
    private ArrayList<String> data;

    public SwipToDeleteAdapter(ArrayList<String> data){
        this.data = data;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view_row, parent, false);
        return new MyViewHolder(itemView);
    }


    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.mTitle.setText(data.get(position));
    }

    @Override
    public int getItemCount() {
        return data.size();
    }
    class MyViewHolder extends RecyclerView.ViewHolder{
        private TextView mTitle;
        RelativeLayout relativeLayout;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            mTitle = itemView.findViewById(R.id.txtTitle);
        }
    }

    public void removeItem(int position) {
        data.remove(position);
        notifyItemRemoved(position);
    }

    public void restoreItem(String item, int position) {
        data.add(position, item);
        notifyItemInserted(position);
    }

    public ArrayList<String> getData() {
        return data;
    }
}
