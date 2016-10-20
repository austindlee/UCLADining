package com.example.austindlee.ucladining;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.design.widget.TabLayout;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import java.io.IOException;

import android.widget.TextView;
import org.jsoup.*;
import android.content.Context;
import java.util.*;
import android.util.Log;
import android.widget.Toast;
import android.support.v7.util.AsyncListUtil;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TableLayout;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringWriter;
import java.io.Writer;

import com.example.austindlee.ucladining.menuTabs.Breakfastm;
import com.example.austindlee.ucladining.menuTabs.Lunchm;
import com.example.austindlee.ucladining.menuTabs.Dinnerm;
import com.example.austindlee.ucladining.menuTabs.Latenightm;

public class MenusPage extends AppCompatActivity {

 /*   ListView classListView;
    ListAdapter adapter;
    Context mContent;
    List<String> data;
    */
    private int checkGitHub;

    private Document htmlDocument;
    private String htmlPageUrl = "https://inducesmile.com/";
    private TextView parsedHtmlNode;
    private String htmlContentInString;


    private ViewPager viewPager;
    private TabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menus_page);

        // Set up multiple screens
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        setupViewPager(viewPager);
        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);

        parsedHtmlNode = (TextView)findViewById(R.id.htmlOut);
    //    mContent = this;
    //    data = new ArrayList<String>();
    //    classListView = (ListView) findViewById(R.id.textView);
    //    adapter = new ListAdapter(mContent, data);
        ParsePage parser = new ParsePage();
        parser.execute();

    }

    private class ParsePage extends AsyncTask<Void,Void,Void>{
        @Override
        protected Void doInBackground(Void... params){
            //load the document
            try {
                htmlDocument = Jsoup.connect(htmlPageUrl).get();
                htmlContentInString = htmlDocument.title();
            } catch (IOException e){
                e.printStackTrace();
            }

            return null;

        }

        protected  void onPostExecute(String result){
            parsedHtmlNode.setText(htmlContentInString);
        }
        protected  void onPreExecute(String res){
            super.onPreExecute();
        }

    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(Breakfastm.newInstance("this is data for fragment one"), "Breakfast");
        adapter.addFragment(Lunchm.newInstance("this is data for fragment 2"), "Lunch");
        adapter.addFragment(Dinnerm.newInstance("this is data for fragment 3"), "Dinner");
        adapter.addFragment(Latenightm.newInstance("this is data for fragment 4"), "Late Night");
        viewPager.setAdapter(adapter);
    }
}
