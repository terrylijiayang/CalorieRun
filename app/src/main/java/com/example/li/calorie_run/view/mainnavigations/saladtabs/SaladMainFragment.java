package com.example.li.calorie_run.view.mainnavigations.saladtabs;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.li.calorie_run.R;

public class SaladMainFragment extends Fragment {

    public static SaladMainFragment newInstance(){
        SaladMainFragment saladMainFragment = new SaladMainFragment();
        return saladMainFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_salad_main, container, false);
    }
}
