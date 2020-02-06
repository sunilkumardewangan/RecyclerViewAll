package com.recycler.recyclerviewall.ui.diffutil;

import android.os.Bundle;
import java.util.ArrayList;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.DiffUtil;

public class MyDiffUtilCallBack extends DiffUtil.Callback {
    ArrayList<DiffModel> newList;
    ArrayList<DiffModel> oldList;

    MyDiffUtilCallBack(ArrayList<DiffModel> newList, ArrayList<DiffModel> oldList){
        this.newList = newList;
        this.oldList = oldList;
    }

    @Override
    public int getOldListSize() {
        return oldList != null? oldList.size():0;
    }

    @Override
    public int getNewListSize() {
        return newList != null ?newList.size():0;
    }

    @Override
    public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
        return newList.get(newItemPosition).id == oldList.get(oldItemPosition).id;
    }

    @Override
    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
        int result = newList.get(newItemPosition).compareTo(oldList.get(oldItemPosition));
        return result == 0;
    }

    @Nullable
    @Override
    public Object getChangePayload(int oldItemPosition, int newItemPosition) {
        DiffModel newModel = newList.get(newItemPosition);
        DiffModel oldModel = oldList.get(oldItemPosition);

        Bundle bundle = new Bundle();
        if(newModel.price != oldModel.price){
            bundle.putInt("price",newModel.price);
        }

        if(bundle.size() ==  0)
            return null;
       return bundle;
    }
}
