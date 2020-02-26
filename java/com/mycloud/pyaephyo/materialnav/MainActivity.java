package com.mycloud.pyaephyo.materialnav;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.NavigationView;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.mycloud.pyaephyo.materialnav.ApplicationService.About;
import com.mycloud.pyaephyo.materialnav.ApplicationService.ApplicationSetting;
import com.mycloud.pyaephyo.materialnav.ApplicationService.MemoActivity;
import com.mycloud.pyaephyo.materialnav.ApplicationService.RecordActivity;
import com.mycloud.pyaephyo.materialnav.ApplicationService.SendFeedback;
import com.mycloud.pyaephyo.materialnav.ApplicationService.TimetableActivity;
import com.mycloud.pyaephyo.materialnav.database.DatabaseHelper;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;


public class MainActivity extends AppCompatActivity {
    private DrawerLayout drawerLayout;
    private Toolbar toolbar;
    // private GoogleApiClient client;
    private RecyclerView recyclerView;
    public DatabaseHelper mdbHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mdbHelper=new DatabaseHelper(this);
        File database=getApplicationContext().getDatabasePath(mdbHelper.DBName);

        if(false==database.exists()) {
            mdbHelper.getReadableDatabase();
            if (copyDatabase(this)) {
               // Toast.makeText(this, "Copy success", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Copy denined", Toast.LENGTH_SHORT).show();
                return;
            }
        }
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.inflateMenu(R.menu.menu_search);
        toolbar.setTitle("University Garage");
        initCollapsingToolbar();

        setSupportActionBar(toolbar);

        initNavigationDrawer();
        try {
            Glide.with(this).load(R.drawable.app_logo).into((ImageView) findViewById(R.id.pp));
        } catch (Exception e) {
            e.printStackTrace();
        }
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

    public  void initNavigationDrawer() {

        NavigationView navigationView = (NavigationView) findViewById(R.id.navigation_view);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {

                int id = menuItem.getItemId();
                Intent i = new Intent();
                switch (id) {
                    case R.id.home:
                        i = new Intent(MainActivity.this, MainActivity.class);
                        startActivity(i);
                        drawerLayout.closeDrawers();
                        break;
                    case R.id.record:
                        i = new Intent(MainActivity.this, RecordActivity.class);
                        startActivity(i);
                        drawerLayout.closeDrawers();
                        break;
                    case R.id.logout:
                        finish();
                        break;
                    case R.id.sendFeedback:
                        i = new Intent(MainActivity.this, SendFeedback.class);
                        startActivity(i);
                        drawerLayout.closeDrawers();
                        break;
                    case R.id.favorite:
                        i = new Intent(MainActivity.this, FavoriteShow.class);
                        startActivity(i);
                        drawerLayout.closeDrawers();
                        break;
                    case R.id.note:
                        i = new Intent(MainActivity.this, MemoActivity.class);
                        startActivity(i);
                        drawerLayout.closeDrawers();
                        break;
                    case R.id.about:
                        i = new Intent(MainActivity.this, About.class);
                        startActivity(i);
                        drawerLayout.closeDrawers();
                        break;
                    case R.id.timetable:
                        i = new Intent(MainActivity.this, TimetableActivity.class);
                        startActivity(i);
                        drawerLayout.closeDrawers();
                        break;
                }
                return true;
            }
        });
        View header = navigationView.getHeaderView(0);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer);

        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.drawer_open, R.string.drawer_close) {

            @Override
            public void onDrawerClosed(View v) {
                super.onDrawerClosed(v);
            }

            @Override
            public void onDrawerOpened(View v) {
                super.onDrawerOpened(v);
            }
        };
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
    }

    private void initCollapsingToolbar() {
        final CollapsingToolbarLayout collapsingToolbar =
                (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        collapsingToolbar.setTitle(" ");
        AppBarLayout appBarLayout = (AppBarLayout) findViewById(R.id.appbar);
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
                    collapsingToolbar.setTitle("University Garage");
                    isShow = true;
                } else if (isShow) {
                    collapsingToolbar.setTitle(" ");
                    isShow = false;
                }
            }
        });
    }


    /*public Action getIndexApiAction() {
        Thing object = new Thing.Builder()
                .setName("Main Page") // TODO: Define a title for the content shown.
                // TODO: Make sure this auto-generated URL is correct.
                .setUrl(Uri.parse("http://[ENTER-YOUR-URL-HERE]"))
                .build();
        return new Action.Builder(Action.TYPE_VIEW)
                .setObject(object)
                .setActionStatus(Action.STATUS_TYPE_COMPLETED)
                .build();
    }

    @Override
    public void onStart() {
        super.onStart();
        client.connect();
        AppIndex.AppIndexApi.start(client, getIndexApiAction());
    }

    @Override
    public void onStop() {
        super.onStop();
        AppIndex.AppIndexApi.end(client, getIndexApiAction());
        client.disconnect();
    }*/
    public void categoryClick(View view) {
        Intent i = new Intent(MainActivity.this, CategoryShow.class);
        startActivity(i);

    }
    public void topClick(View view){
        Intent i=new Intent(MainActivity.this,TopUniversity.class);
        startActivity(i);
    }
    public void requirementClick(View view){
        Intent i=new Intent(MainActivity.this,Requirement.class);
        startActivity(i);
    }
    public void transferClick(View view){
        Intent i=new Intent(MainActivity.this,Transfer.class);
        startActivity(i);
    }
    public void ruleClick(View view){
        Intent i=new Intent(MainActivity.this,RuleActivity.class);
        startActivity(i);

    }
    public void markClick(View view){
        Intent i=new Intent(MainActivity.this,Mark.class);
        startActivity(i);
    }
    public void scholarClick(View v){
        Intent i=new Intent(MainActivity.this,Scholar.class);
        startActivity(i);
    }
    public void examClick(View v){
        Intent i=new Intent(MainActivity.this,Exam.class);
        startActivity(i);
    }
    public void qualificationClick(View v){
        Intent i=new Intent(MainActivity.this,Qualification.class);
        startActivity(i);
    }
    private boolean copyDatabase(Context context) {
        try {
            InputStream myInput = context.getAssets().open(mdbHelper.DBName);
            String outFileName = mdbHelper.DBLocatoin + mdbHelper.DBName;
            Log.i("Before file out","Succes...");
            OutputStream myOutput = new FileOutputStream(outFileName);
            Log.i("After file out","Succes...");
            byte[] buffer = new byte[1024];
            int length = 0;

            while ((length = myInput.read(buffer)) > 0) {
                myOutput.write(buffer, 0, length);
            }
            myOutput.close();
            myOutput.flush();
            myInput.close();
            Log.i("Copy Database","Copy Success....");
            return true;

        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
}