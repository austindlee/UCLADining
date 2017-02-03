package com.example.austindlee.ucladining.hourTabs;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.austindlee.ucladining.R;
import android.support.annotation.Nullable;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.Calendar;

/**
 * Created by AustinDLee on 10/19/16.
 */

public class Breakfast extends Fragment {

    private static final String ARG_EXAMPLE = "Thisi sa constatn";
    private String example_data;
    public Breakfast() {
    }

    public static Breakfast newInstance(String example_argument) {
        Breakfast breakfast = new Breakfast();
        Bundle args = new Bundle();
        args.putString(ARG_EXAMPLE, example_argument);
        breakfast.setArguments(args);
        return breakfast;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        example_data = getArguments().getString(ARG_EXAMPLE);
        Log.i("Fragment created with", example_data);
    }

    @Nullable @Override public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        String [] diningHrs;
        String[] diningHalls = {"Covel", "De Neve", "Feast", "Bruin Plate", "Bruin Cafe", "Cafe 1919", "Rendezvous", "The Study"};
        Calendar c = Calendar.getInstance();
        int dayOfWeek = c.get(Calendar.DAY_OF_WEEK);
        if(dayOfWeek == Calendar.SATURDAY || dayOfWeek == Calendar.SUNDAY)
        {
            diningHrs = new String [] {"9:30 AM - 3:00 PM","9:30 AM - 2:00 PM","CLOSED","10:00 AM - 2:00 PM","CLOSED","CLOSED","10:00 AM - 2:00 PM","CLOSED"};
        }
        else
        {
            diningHrs = new String [] {"CLOSED","7:00 AM - 10:00 AM","CLOSED","7:00 AM - 9:00 AM","7:00 AM - 11:00 AM","CLOSED","7:00 AM - 10:30 AM","7:00 AM - 11:00 AM"};

        }
        View view = inflater.inflate(R.layout.bfasthours, container, false);              // looks inside bfasthours
        ListView listView = (ListView) view.findViewById(R.id.listHalls);                 // goes to listBHours
        ArrayAdapter<String> listBHoursAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, diningHalls);
        listView.setAdapter(listBHoursAdapter);

        ListView hrList = (ListView) view.findViewById(R.id.listHours);                   // gets hours
        ArrayAdapter<String> listBHours = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, diningHrs);
        hrList.setAdapter(listBHours);

        return view;
    }



}