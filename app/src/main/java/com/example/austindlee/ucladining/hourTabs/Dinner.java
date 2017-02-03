package com.example.austindlee.ucladining.hourTabs;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.austindlee.ucladining.R;

import java.util.Calendar;


/**
 * Created by AustinDLee on 10/19/16.
 */

public class Dinner extends Fragment {

    private static final String ARG_EXAMPLE = "Thisi sa constatn";
    private String example_data;
    public Dinner() {

    }

    public static Dinner newInstance(String example_argument) {
        Dinner dinner = new Dinner();
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
        String [] diningHrs;
        String[] diningHalls = {"Covel", "De Neve", "Feast", "Bruin Plate", "Bruin Cafe", "Cafe 1919", "Rendezvous", "The Study"};
        Calendar c = Calendar.getInstance();
        int dayOfWeek = c.get(Calendar.DAY_OF_WEEK);
        if(dayOfWeek == Calendar.SATURDAY || dayOfWeek == Calendar.SUNDAY)
        {
            diningHrs = new String [] {"5:00 PM - 9:00 PM","5:00 PM - 8:00 PM","CLOSED","5:00 PM - 8:00 PM","5:00 PM - 9:00 PM","CLOSED","4:00 PM - 8:00 PM","11:00 AM - 5:00 PM"};
        }
        else
        {
            diningHrs = new String [] {"5:00 PM - 9:00 PM","5:00 PM - 8:00 PM","5:00 PM - 8:00 PM","5:00 PM - 8:00 PM","5:00 PM - 9:00 PM","5:00 PM - 9:00 PM","5:00 PM - 9:00 PM","11:00 AM - 5:00 PM"};
        }
        View view = inflater.inflate(R.layout.dinnerhours, container, false);              // looks inside dinnerHours
        ListView listView = (ListView) view.findViewById(R.id.listHalls);                 // goes to listofHalls
        ArrayAdapter<String> listBHoursAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, diningHalls);
        listView.setAdapter(listBHoursAdapter);

        ListView hrList = (ListView) view.findViewById(R.id.listHours);                   // gets hours
        ArrayAdapter<String> listBHours = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, diningHrs);
        hrList.setAdapter(listBHours);

        return view;
    }



}