package com.example.austindlee.ucladining;

import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.widget.TableLayout;
import android.view.Window;
import android.view.WindowManager;

import com.example.austindlee.ucladining.hourTabs.Breakfast;
import com.example.austindlee.ucladining.hourTabs.Dinner;
import com.example.austindlee.ucladining.hourTabs.Lunch;
import com.example.austindlee.ucladining.hourTabs.Latenight;

public class HoursPage extends AppCompatActivity {

    private ViewPager viewPager;
    private TabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hours_page);
//Remove title bar
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        setupViewPager(viewPager);

        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);

    }
    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(Breakfast.newInstance("this is data for fragment one"), "Breakfast");
        adapter.addFragment(Lunch.newInstance("this is data for fragment 2"), "Lunch");
        adapter.addFragment(Dinner.newInstance("this is data for fragment 3"), "Dinner");
        adapter.addFragment(Latenight.newInstance("this is data for fragment 4"), "Late Night");
        viewPager.setAdapter(adapter);
    }
}
