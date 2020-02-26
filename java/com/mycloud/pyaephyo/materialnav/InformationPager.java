package com.mycloud.pyaephyo.materialnav;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.mycloud.pyaephyo.materialnav.Adapter.ViewPagerAdapter;
import com.mycloud.pyaephyo.materialnav.Model.UniversityInfo;

public class InformationPager extends AppCompatActivity{
    Toolbar toolbar;
    ViewPager pager;
    ViewPagerAdapter adapter;
    TabLayout tabs;
    public static String id;
    CharSequence Titles[]={"Detail Info","Opportunity","Living"};
    int Numboftabs =3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_pager_layout);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.inflateMenu(R.menu.menu_search);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Intent i=getIntent();
        id=(String) i.getSerializableExtra("ID");
        i.putExtra("ID",id);
        adapter =  new ViewPagerAdapter(getSupportFragmentManager(),Titles,Numboftabs);
        pager = (ViewPager) findViewById(R.id.viewpager);
        pager.setAdapter(adapter);
        tabs = (TabLayout) findViewById(R.id.tabs);
        tabs.setupWithViewPager(pager);

    }
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_search, menu);
        MenuItem searchViewItem = menu.findItem(R.id.action_search);
        final SearchView searchViewAndroidActionBar = (SearchView) MenuItemCompat.getActionView(searchViewItem);
        searchViewAndroidActionBar.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                searchViewAndroidActionBar.clearFocus();
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                return false;
            }

        });

        return super.onCreateOptionsMenu(menu);
    }

  /*  public void routeClick(View v){
        Intent i=new Intent(this,RouteActivity.class);
        startActivity(i);
    }*/
}
