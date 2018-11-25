package com.example.li.calorie_run.view.mainnavigations.saladtabs;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.li.calorie_run.R;
import com.example.li.calorie_run.view.mainnavigations.SaladFragment;


public class SaladHotFragment extends Fragment {

    public static SaladHotFragment newInstance(){
        SaladHotFragment saladFragment = new SaladHotFragment();
        return saladFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_salad_hot, container, false);
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
