package com.recycler.recyclerviewall.ui.draganddrop;

import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.recycler.recyclerviewall.R;

import java.util.ArrayList;

public class DrageAndDropFragment extends Fragment {

    private DrageAndDropViewModel mViewModel;
    private View rootView;
    private RecyclerView recyclerView;
    private DragDropAdapter dragDropAdapter;
    private ArrayList<String> list = new ArrayList<>();

    public static DrageAndDropFragment newInstance() {
        return new DrageAndDropFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.drage_and_drop_fragment, container, false);
        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(DrageAndDropViewModel.class);
        recyclerView = rootView.findViewById(R.id.recyclerView);
        populateList();
    }

    private void populateList(){
        list.add("item1");
        list.add("item2");
        list.add("item3");
        list.add("item4");
        list.add("item5");
        list.add("item6");
        list.add("item7");
        list.add("item8");
        list.add("item9");
        list.add("item10");
        list.add("item11");
        list.add("item12");
        list.add("item13");
        list.add("item14");

        dragDropAdapter = new DragDropAdapter(list);

        ItemTouchHelper.Callback callback = new ItemMoveCallback(dragDropAdapter);
        ItemTouchHelper touchHelper = new ItemTouchHelper(callback);
        touchHelper.attachToRecyclerView(recyclerView);

        recyclerView.setAdapter(dragDropAdapter);



    }

}
