package com.study.android.zhangsht.hqs.activity;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.study.android.zhangsht.hqs.R;
import com.study.android.zhangsht.hqs.adapter.ViewPagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class MyClinic extends AppCompatActivity {
    private ViewPager viewPager;
    private List<View> viewList;
    private List<String> titleList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_clinic);


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        viewPager = (ViewPager)findViewById(R.id.myClinicVP);
        viewList = new ArrayList<>();
        titleList = new ArrayList<>();
        View waitTreat = View.inflate(this,R.layout.myclinic_wait_view,null);
        View inTreat = View.inflate(this,R.layout.myclinic_in_view,null);
        viewList.add(inTreat);
        viewList.add(waitTreat);
        titleList.add("就诊");
        titleList.add("候诊");
        ViewPagerAdapter adapter = new ViewPagerAdapter(viewList, titleList);
        viewPager.setAdapter(adapter);
    }

}
