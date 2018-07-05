package com.example.se02.bulletinboard;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RelativeLayout;

import static android.graphics.Color.WHITE;

public class NoticeActivity extends AppCompatActivity {
    private ViewPager viewPager;
    private ViewPager viewContent;
    private TabLayout tabLayout;
    private RelativeLayout rl_contents;
    String name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notice);
        Intent intent =getIntent();
        name = intent.getExtras().getString("studentName");
        viewPager = (ViewPager)findViewById(R.id.viewPager);
        viewContent = (ViewPager)findViewById(R.id.viewContent);
        tabLayout = (TabLayout) findViewById(R.id.tabs);
        viewContent.setAdapter(new pagerAdapter(getSupportFragmentManager()));
        tabLayout.setupWithViewPager(viewContent);

    }
    private class pagerAdapter extends FragmentStatePagerAdapter
    {
        public pagerAdapter(android.support.v4.app.FragmentManager fm)
        {
            super(fm);
        }
        @Override
        public android.support.v4.app.Fragment getItem(int position)
        {
            Log.v("Position:"," "+position);
            switch(position) {
                case 0:
                    return new FirstFragment();
                case 1:
                    return new SecondFragment();
                case 2:
                    return new ThirdFragment();
                case 3:
                    return new GameFragment();
                default:
                    return null;
            }
        }
        @Override
        public int getCount()
        {
            return 4;
        }
        @Override
        public CharSequence getPageTitle(int position)
        {
            switch(position) {
                case 0:
                    return "전체 보기";
                case 1:
                    return "자유 게시판";
                case 2:
                    return "시간표";
                case 3:
                    return "게임";
                default:
                    return  null;
            }
        }
    }
    public String getName(){
        return name;
    }
}
