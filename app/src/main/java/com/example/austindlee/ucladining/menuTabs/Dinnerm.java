package com.example.austindlee.ucladining.menuTabs;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.austindlee.ucladining.R;


/**
 * Created by AustinDLee on 10/19/16.
 */

public class Dinnerm extends Fragment {

    private static final String ARG_EXAMPLE = "Thisi sa constatn";
    private String example_data;
    public Dinnerm() {

    }

    public static Dinnerm newInstance(String example_argument) {
        Dinnerm dinner = new Dinnerm();
        Bundle args = new Bundle();
        args.putString(ARG_EXAMPLE, example_argument);
        dinner.setArguments(args);
        return dinner;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        example_data = getArguments().getString(ARG_EXAMPLE);
        Log.i("Fragment created with", example_data);
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        return inflater.inflate(R.layout.dinnerhours, container, false);
    }



}