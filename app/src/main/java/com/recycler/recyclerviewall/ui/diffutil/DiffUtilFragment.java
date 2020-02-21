package com.recycler.recyclerviewall.ui.diffutil;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.recycler.recyclerviewall.R;
import java.util.ArrayList;


public class DiffUtilFragment extends Fragment {

    private RecyclerView recyclerView;
    private FloatingActionButton fabAddList, fabChangeList;
    private DiffUtilAdapter diffUtilAdapter;
    private ArrayList<DiffModel> modelArrayList = new ArrayList<>();

    public int i = 1;
    private View rootView;
    public DiffUtilFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       rootView = inflater.inflate(R.layout.fragment_diff_util, container, false);
       return rootView;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        recyclerView = rootView.findViewById(R.id.recyclerView);
        fabAddList = rootView.findViewById(R.id.fabAddList);
        fabChangeList = rootView.findViewById(R.id.fabChangeList);

        dummyData();
        diffUtilAdapter = new DiffUtilAdapter(modelArrayList);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(diffUtilAdapter);

        fabAddList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addMoreCoinsToTheList();
            }
        });

        fabChangeList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changePricesInTheList();
            }
        });
    }

    private void dummyData(){
        modelArrayList.add(new DiffModel(i++,"Bitcoins",8000));
        modelArrayList.add(new DiffModel(i++,"Ethereum",600));
        modelArrayList.add(new DiffModel(i++,"Litecoin",250));
        modelArrayList.add(new DiffModel(i++,"Bitcoin cash",1000));
    }

    private void addMoreCoinsToTheList() {
        ArrayList<DiffModel> list = new ArrayList<>();
        try {
            for(DiffModel diffModel:modelArrayList){
                list.add((DiffModel) diffModel.clone());
            }
        }catch (CloneNotSupportedException e){
            throw new RuntimeException(e);
        }

        list.add(new DiffModel(i++,"Tron",1));
        list.add(new DiffModel(i++,"Ripple",5));
        list.add(new DiffModel(i++,"NEO",100));
        list.add(new DiffModel(i++,"OMG",20));
        diffUtilAdapter.setData(list);
    }

    private void changePricesInTheList(){
        ArrayList<DiffModel> list = new ArrayList<>();

        for(DiffModel diffModel:modelArrayList){
            try {
                list.add((DiffModel) diffModel.clone());
            } catch (CloneNotSupportedException e) {
                e.printStackTrace();
            }
        }

        for (DiffModel model:list){
            if(model.price<900)
                model.price = 900;
        }

        diffUtilAdapter.setData(list);
    }
}
