package com.example.austindlee.ucladining;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import org.joda.time.DateTime;
import org.joda.time.Days;
import org.joda.time.LocalDate;
import org.joda.time.LocalDateTime;


public class SwipesPage extends AppCompatActivity {

    TextView totalSwipesView;
    private int p19 = 1;
    private int p14 = 2;
    private int r19 = 3;
    private int r14 = 4;
    private int total19P = 203;
    private int total14P = 151;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_swipes_page);
        totalSwipesView = (TextView) findViewById(R.id.swipesLeftTxt);

    }
    public int computeSwipes(int mealPlan){
        LocalDateTime now = LocalDateTime.now();
        int swipesRemain = 0;
        Calendar calendar = Calendar.getInstance();
        int day = calendar.get(Calendar.DAY_OF_WEEK);
        if(mealPlan == r19)
            swipesRemain = regularSwipes(day,r19);
        else if(mealPlan == r14)
            swipesRemain = regularSwipes(day,r14);
        else if(mealPlan == p19)
            swipesRemain = preSwipes(day, p19);
        else if(mealPlan == p14)
            swipesRemain = preSwipes(day, p14);
        return swipesRemain;
    }

    public int preSwipes(int day, int mealPlan)
    {
        LocalDateTime time = new LocalDateTime();
        int hourOfDay = time.getHourOfDay();
        int totalSwipesLeft = 0;
        // gets number of days between start of meal period to now
        LocalDate dateNow = new LocalDate();
        LocalDate startFall = new LocalDate(2017, 1, 9);
        Days days = Days.daysBetween(dateNow, startFall);
        int daysBtwn = days.getDays() * -1;
        totalSwipesLeft = calcPreSwipes(daysBtwn, mealPlan);
        totalSwipesLeft = deductSwipes(day, hourOfDay, mealPlan, totalSwipesLeft);
        return totalSwipesLeft;
    }

    public int calcPreSwipes(int days, int mealPlan)
    {
        int swipes = 0;
        if(mealPlan == p19)
            swipes = total19P;
        else if (mealPlan == p14)
            swipes = total14P;

        // Day of Week 5 and 6 are weekend dates
        int dayOfWeek = days%7;
        int weeks = days/7;
        if(mealPlan == p19){
            // 66, 66 ,67, 68
            // if it is a weekend subtract 19 * weeks + 2 * day
            if (dayOfWeek == 5 | dayOfWeek == 6) {
                // 19 per week + 15 for the weekdays and then the weekend swipes
                swipes -= (1 + 19 * weeks + 15 + (((dayOfWeek % 5) * 2)));
            }
            // if it is a weekday subtract 19 * weeks + 3 * day
            else {
                swipes -= (1 + 19 * weeks + 3 * dayOfWeek);
            }
        }
        if(mealPlan == p14){
            swipes -= (1 + 14 * weeks + 2 * dayOfWeek);
        }
        return swipes;
    }

    public int deductSwipes(int day, int hourOfDay, int mealPlan, int initialSwipes)
    {
        if(mealPlan == r19 | mealPlan == p19){
            if(day == Calendar.SUNDAY | day == Calendar.SATURDAY){
                // lunch has passed
                if (hourOfDay > 16 && hourOfDay <= 24) {
                    initialSwipes--;
                }
            }
            else{
                // breakfast has passed
                if (hourOfDay <= 16 && hourOfDay > 11) {
                    initialSwipes--;
                }
                // lunch has passed
                if (hourOfDay > 16 && hourOfDay <= 24) {
                    initialSwipes -= 2;
                }

            }
        }
        if(mealPlan == r14 | mealPlan == p14) {
            if (hourOfDay > 16 && hourOfDay <= 24) {
                initialSwipes--;
            }
        }
        return initialSwipes;
    }


    public int regularSwipes(int day, int mealPlan)
    {
        LocalDateTime time = new LocalDateTime();
        int hourOfDay = time.getHourOfDay();
        Log.i("hr", Integer.toString(hourOfDay));
        int swipesRemain = 0;
        switch(day){
            case Calendar.MONDAY:
                if(mealPlan == r14)
                    swipesRemain = 14;
                else if(mealPlan == r19)
                    swipesRemain = 19;
                break;
            case Calendar.TUESDAY:
                if(mealPlan == r14)
                    swipesRemain = 12;
                else if(mealPlan == r19)
                    swipesRemain = 16;
                break;
            case Calendar.WEDNESDAY:
                if(mealPlan == r14)
                    swipesRemain = 10;
                else if(mealPlan == r19)
                    swipesRemain = 13;
                break;
            case Calendar.THURSDAY:
                if(mealPlan == r14)
                    swipesRemain = 8;
                else if(mealPlan == r19)
                    swipesRemain = 10;
                break;
            case Calendar.FRIDAY:
                if(mealPlan == r14)
                    swipesRemain = 6;
                else if(mealPlan == r19)
                    swipesRemain = 7;
                break;
            case Calendar.SATURDAY:
                if(mealPlan == r14)
                    swipesRemain = 4;
                else if(mealPlan == r19)
                    swipesRemain = 4;
                break;
            case Calendar.SUNDAY:
                if(mealPlan == r14)
                    swipesRemain = 2;
                else if(mealPlan == r19)
                    swipesRemain = 2;
                break;
        }
        swipesRemain = deductSwipes(day, hourOfDay, mealPlan, swipesRemain);
        return swipesRemain;
    }
    public void click19P(View view)
    {
        int swipesLeft = computeSwipes(p19);
        totalSwipesView.setText(Integer.toString(swipesLeft));
    }
    public void click14P(View view)
    {
        int swipesLeft = computeSwipes(p14);
        totalSwipesView.setText(Integer.toString(swipesLeft));
    }
    public void click19R(View view)
    {
        int swipesLeft = computeSwipes(r19);
        totalSwipesView.setText(Integer.toString(swipesLeft));
    }
    public void click14R(View view)
    {

        int swipesLeft = computeSwipes(r14);
        totalSwipesView.setText(Integer.toString(swipesLeft));
    }

}
