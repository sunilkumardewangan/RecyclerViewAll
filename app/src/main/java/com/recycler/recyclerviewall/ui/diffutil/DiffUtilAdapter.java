package com.recycler.recyclerviewall.ui.diffutil;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.recycler.recyclerviewall.R;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

public class DiffUtilAdapter extends RecyclerView.Adapter<DiffUtilAdapter.ViewModel> {

    private ArrayList<DiffModel> data;

    public DiffUtilAdapter(ArrayList<DiffModel> data){
        this.data = data;
    }

    @NonNull
    @Override
    public ViewModel onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_dift_card_layout, parent, false);
        return new ViewModel(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewModel holder, int position) {
        holder.mName.setText(data.get(position).name);
        holder.mPrice.setText(data.get(position).price + " USD");
    }

    @Override
    public void onBindViewHolder(@NonNull ViewModel holder, int position, @NonNull List<Object> payloads) {
        super.onBindViewHolder(holder, position, payloads);

        if (payloads.isEmpty()) {
            super.onBindViewHolder(holder, position, payloads);
        } else {
            Bundle o = (Bundle) payloads.get(0);
            for (String key : o.keySet()) {
                if (key.equals("price")) {
                    holder.mName.setText(data.get(position).name);
                    holder.mPrice.setText(data.get(position).price + " USD");
                    holder.mPrice.setTextColor(Color.GREEN);
                }
            }
        }
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewModel extends RecyclerView.ViewHolder {
        private TextView mName, mPrice;
        public ViewModel(@NonNull View itemView) {
            super(itemView);
            mName = itemView.findViewById(R.id.txtName);
            mPrice = itemView.findViewById(R.id.txtPrice);
        }
    }

    public ArrayList<DiffModel> getData() {
        return data;
    }

    public void setData(ArrayList<DiffModel> newData) {

        DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(new MyDiffUtilCallBack(newData, data));
        diffResult.dispatchUpdatesTo(this);
        data.clear();
        this.data.addAll(newData);
    }
}
