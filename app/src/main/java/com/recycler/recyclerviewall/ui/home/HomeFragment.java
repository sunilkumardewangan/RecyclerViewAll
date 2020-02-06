package com.recycler.recyclerviewall.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.recycler.recyclerviewall.R;

import java.util.ArrayList;

public class HomeFragment extends Fragment {
    FloatingActionButton fab;
    RecyclerView recyclerView;
    HomeAdapter homeAdapter;
    ArrayList<String> arrayList = new ArrayList<>();
    private HomeViewModel homeViewModel;
    int[] animationList = {R.anim.layout_animation_up_to_down, R.anim.layout_animation_right_to_left, R.anim.layout_animation_down_to_up, R.anim.layout_animation_left_to_right};

    int i = 0;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);

//        final TextView textView = root.findViewById(R.id.text_home);
//        homeViewModel.getText().observe(this, new Observer<String>() {
//            @Override
//            public void onChanged(@Nullable String s) {
//                textView.setText(s);
//            }
//        });

        fab = root.findViewById(R.id.fab);
        recyclerView = root.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(),2, GridLayoutManager.VERTICAL,false));
        populateData();
        initAdapter();

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (i < animationList.length - 1) {
                    i++;
                } else {
                    i = 0;
                }
                runAnimationAgain();

            }
        });


        return root;
    }

    private void populateData() {

        for (int i = 0; i < 12; i++) {
            arrayList.add("Item " + i);
        }
    }

    private void initAdapter() {
        homeAdapter = new HomeAdapter(arrayList);
        recyclerView.setAdapter(homeAdapter);
    }

    private void runAnimationAgain() {

        final LayoutAnimationController controller =
                AnimationUtils.loadLayoutAnimation(getContext(), animationList[i]);


        recyclerView.setLayoutAnimation(controller);
        homeAdapter.notifyDataSetChanged();
        recyclerView.scheduleLayoutAnimation();

    }

}