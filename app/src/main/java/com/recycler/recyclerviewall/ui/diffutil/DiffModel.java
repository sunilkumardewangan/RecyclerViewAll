package com.recycler.recyclerviewall.ui.diffutil;

import androidx.annotation.NonNull;

public class DiffModel implements Comparable,Cloneable {
    public String name;
    public int id, price;

    public DiffModel(int id, String name, int price){
        this.id = id;
        this.name = name;
        this.price = price;
    }

    @Override
    public int compareTo(Object o) {
        DiffModel diffModel = (DiffModel) o;
        if(diffModel.id == this.id && diffModel.name.equals(this.name) && diffModel.price == this.price){
            return 0;
        }
        return 1;
    }

    @NonNull
    @Override
    protected Object clone() throws CloneNotSupportedException {
        DiffModel diffModel;
        try {
            diffModel = (DiffModel) super.clone();
        }catch (CloneNotSupportedException e){
            throw new RuntimeException(e);
        }
        return diffModel;
    }
}
