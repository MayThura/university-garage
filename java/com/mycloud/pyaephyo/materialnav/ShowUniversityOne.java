package com.mycloud.pyaephyo.materialnav;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

/*import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import com.mycloud.pyaephyo.materialnav.Adapter.RecyclerViewAdapter;
import com.mycloud.pyaephyo.materialnav.Model.UniversityData;
import java.util.ArrayList;
import java.util.List;

*/
public class ShowUniversityOne extends AppCompatActivity {
   /* public Toolbar toolbar;
    private Spinner spinner;
    private RecyclerView recyclerView;
    String[] sort= new String[]{
            "Region",
            "Mark(Low to High)",
            "Mark(High to Low)",

    };
    private RecyclerViewAdapter adapter;
    List<UniversityData>dataList;
    @Override*/
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.university_list);
      /*  Intent i=getIntent();
        String type=(String)i.getSerializableExtra("type");
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.inflateMenu(R.menu.menu_search);
        toolbar.setTitle(type);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        addListenerOnSpinnerItemSelection();
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        dataList = new ArrayList<>();
        adapter = new RecyclerViewAdapter(this, dataList);

        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(this, 1);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
        prepareData();
        initCollapsingToolbar();

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

    }
        public void addListenerOnSpinnerItemSelection() {
            spinner= (Spinner) findViewById(R.id.sortSpinner);
            ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(
                    this,R.layout.spinner_item,sort);
            spinnerArrayAdapter.setDropDownViewResource(R.layout.spinner_item);
            spinner.setAdapter(spinnerArrayAdapter);
            spinner.setOnItemSelectedListener(new CustomOnItemSelectedListener());*/

    }
}
