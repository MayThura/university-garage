package com.mycloud.pyaephyo.materialnav;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import com.mycloud.pyaephyo.materialnav.database.DatabaseHelper;
/**
 * Created by Windows on 10/26/2016.
 */
public class CategoryShow extends AppCompatActivity {
    public Toolbar toolbar;
    public DatabaseHelper mdbHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.category_show_layout);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.inflateMenu(R.menu.menu_search);
        toolbar.setTitle("Categories");
        setSupportActionBar(toolbar);
         getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
    public void medicalClick(View view){
        Intent i=new Intent(this,ShowUniversity.class);
        i.putExtra("title","Medical Universities");
        i.putExtra("type","medical");
        i.putExtra("filter","no");
        startActivity(i);
    }
    public void technologyClick(View view){
        Intent i=new Intent(this,ShowUniversity.class);
        i.putExtra("title","Technological Universities");
        i.putExtra("type","technology");
        i.putExtra("filter","no");
        startActivity(i);
    }
    public void itClick(View view){
        Intent i=new Intent(this,ShowUniversity.class);
        i.putExtra("title","Information Technological Universities");
        i.putExtra("type","IT");
        i.putExtra("filter","no");
        startActivity(i);
    }
    public void privateClick(View view){
        Intent i=new Intent(this,ShowUniversity.class);
        i.putExtra("title","Private Universities");
        i.putExtra("type","private");
        i.putExtra("filter","no");
        startActivity(i);
    }
    public void newspaperClick(View view){
        Intent i=new Intent(this,ShowUniversity.class);
        i.putExtra("title","From newspaper");
        i.putExtra("filter","no");
        i.putExtra("type","newspaper");
        startActivity(i);

    }
    public void maritimeClick(View view){
        Intent i=new Intent(this,ShowUniversity.class);
        i.putExtra("title","Maritime Universities");
        i.putExtra("type","marine");
        i.putExtra("filter","no");
        startActivity(i);
    }
    public void aerospaceClick(View view){
        Intent i=new Intent(this,ShowUniversity.class);
        i.putExtra("title","Aeronautical Universities");
        i.putExtra("type","air");
        i.putExtra("filter","no");
        startActivity(i);
    }
    public void linguisticClick(View view){
        Intent i=new Intent(this,ShowUniversity.class);
        i.putExtra("title","Linguistic Universities");
        i.putExtra("type","language");
        i.putExtra("filter","no");
        startActivity(i);
    }

}
