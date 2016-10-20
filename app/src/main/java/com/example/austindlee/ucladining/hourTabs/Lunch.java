package com.example.austindlee.ucladining.hourTabs;

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

public class Lunch extends Fragment {

    private static final String ARG_EXAMPLE = "Thisi sa constatn";
    private String example_data;
    public Lunch() {

    }

    public static Lunch newInstance(String example_argument) {
        Lunch lunch = new Lunch();
        Bundle args = new Bundle();
        args.putString(ARG_EXAMPLE, example_argument);
        lunch.setArguments(args);
        return lunch;
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
        return inflater.inflate(R.layout.lunchhours, container, false);
    }
}