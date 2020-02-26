package com.mycloud.pyaephyo.materialnav;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by ucsm on 10/27/2016.
 */

/*import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import com.mycloud.pyaephyo.materialnav.Adapter.RecyclerViewAdapter;
import com.mycloud.pyaephyo.materialnav.Model.UniversityData;
import java.util.ArrayList;
import java.util.List;
*/


public class FavoriteShow extends AppCompatActivity {
  /*  public Toolbar toolbar;

    private RecyclerView recyclerView;
    private RecyclerViewAdapter adapter;
    List<UniversityData> dataList;*/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.favorite_list_view);
       /*Intent i=getIntent();
        String type=(String)i.getSerializableExtra("type");
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.inflateMenu(R.menu.menu_search);
        toolbar.setTitle(type);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        dataList = new ArrayList<>();
        adapter = new RecyclerViewAdapter(this, dataList);

        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
        prepareData();

    }

    private void prepareData() {
        UniversityData data=new UniversityData("Batman vs Superman",  R.drawable.cover);

        dataList.add(data);
        data=new UniversityData("X-Men: Apocalypse", R.drawable.cover);
        dataList.add(data);
        data=new UniversityData("Captain America: Civil War",R.drawable.cover);
        dataList.add(data);
        data=new UniversityData("Kung Fu Panda 3",  R.drawable.cover);
        dataList.add(data);
        data=new UniversityData("Warcraft",  R.drawable.cover);
        dataList.add(data);
        data=new UniversityData("Alice in Wonderland",R.drawable.cover);
        dataList.add(data);

    }*/

}}

