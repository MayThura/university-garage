package com.mycloud.pyaephyo.materialnav;


import android.annotation.TargetApi;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ListFragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.mycloud.pyaephyo.materialnav.Adapter.UniversityListAdapter;
import com.mycloud.pyaephyo.materialnav.Fragment.DetailDataFragment;
import com.mycloud.pyaephyo.materialnav.Model.Admission;
import com.mycloud.pyaephyo.materialnav.Model.MajorDetail;
import com.mycloud.pyaephyo.materialnav.Model.Support;
import com.mycloud.pyaephyo.materialnav.Model.UniversityInfo;
import com.mycloud.pyaephyo.materialnav.database.DatabaseHelper;
import com.mycloud.pyaephyo.materialnav.listener.RecyclerItemClickListener;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

/**
 * Created by ucsm on 10/28/2016.
 */

public class ShowUniversity extends AppCompatActivity{

    public static ShowUniversity instance;

    public static ShowUniversity getInstance() {
        return instance;
    }
    private Spinner spinner;
    public String title;
    public Toolbar toolbar;
    public String type;
    public String max,min,region,gender;
    private ListView lstUni;
    private DatabaseHelper mDatabase;
    private UniversityListAdapter uniListAdapter;
    private List<UniversityInfo> uniList;
    String uni_id;
    String[] sort = new String[]{
            "Mark(Low to High)",
            "Mark(High to Low)",

    };
    public String filter="no";
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.university_list);
        lstUni = (ListView) findViewById(R.id.lstView);
        mDatabase = new DatabaseHelper(this);
        Intent i = getIntent();
        String type = (String) i.getSerializableExtra("type");
        String title = (String) i.getSerializableExtra("title");
        filter=(String) i.getSerializableExtra("filter");
        if(filter.equals("yes")){
           region=(String)i.getSerializableExtra("region");
           gender=(String)i.getSerializableExtra("gender");
           max=(String)i.getSerializableExtra("max");
           min=(String) i.getSerializableExtra("min");
            uniList=mDatabase.getFilterResult(region,gender,Integer.parseInt(min),Integer.parseInt(max));

        }
        else{ uniList = mDatabase.getAllUniData(type);}
        /*if(uniList.size()<0){
            AlertDialog.Builder builder=new AlertDialog.Builder(ShowUniversity.this,R.style.MyDialogTheme);
            builder.setTitle("No such data");
            builder.setMessage("There are no universities for "+gender+" between "+min.toString()+" and  "+max.toString()+" in "+region);
            builder.setPositiveButton("OK",null);
            builder.setNegativeButton("Cancel",null);
            builder.setCancelable(true);
            AlertDialog dialog=builder.create();
            dialog.create();
            uniList = mDatabase.getAllUniData(type);
        }*/
        uniListAdapter = new UniversityListAdapter(this, uniList);

        toolbar = (Toolbar) findViewById(R.id.detailToolbar);
        //Log.i("Type", type);
        //Log.i("Title", title);
        toolbar.inflateMenu(R.menu.menu_search);
        toolbar.setTitle(title);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        addListenerOnSpinnerItemSelection();
        lstUni.setAdapter(uniListAdapter);
        initCollapsingToolbar();
        lstUni.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Animation animation=new AlphaAnimation(0.3f,1.0f);
                animation.setDuration(4000);
                view.startAnimation(animation);
                uni_id=uniList.get(position).getUniId();
                Intent in=new Intent(getApplicationContext(),InformationPager.class);
                in.putExtra("ID",uni_id);
                startActivity(in);

            }
        });
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent i=new Intent(ShowUniversity.this, FilterActivity.class);
                startActivity(i);
            }

        });

    }

    public void addListenerOnSpinnerItemSelection() {
        spinner = (Spinner) findViewById(R.id.sortSpinner);
        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(
                this, R.layout.spinner_item, sort);
        spinnerArrayAdapter.setDropDownViewResource(R.layout.spinner_item);
        spinner.setAdapter(spinnerArrayAdapter);
        spinner.setOnItemSelectedListener(new CustomOnItemSelectedListener());

    }

    private void initCollapsingToolbar() {
        final CollapsingToolbarLayout collapsingToolbar =
                (CollapsingToolbarLayout) findViewById(R.id.collapsing);
        collapsingToolbar.setTitle(" ");
        AppBarLayout appBarLayout = (AppBarLayout) findViewById(R.id.app_bar);
        appBarLayout.setExpanded(true);
        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            boolean isShow = false;
            int scrollRange = -1;

            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (scrollRange == -1) {
                    scrollRange = appBarLayout.getTotalScrollRange();
                }
                if (scrollRange + verticalOffset == 0) {
                    collapsingToolbar.setTitle(getString(R.string.app_name));
                    isShow = true;
                } else if (isShow) {
                    collapsingToolbar.setTitle(" ");
                    isShow = false;
                }
            }
        });
    }


}
