package com.mycloud.pyaephyo.materialnav;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
/**
 * Created by Windows on 10/29/2016.
 */
public class FilterActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    public EditText txtMin, txtMax;
    public Spinner spinner;
    public String gender;
    public String region;
    String[] list = new String[]{"Yangon", "Mandalay", "Magway", "Pyin Oo Lwin", "Meikhtila"};

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.filter_layout);
        txtMin=(EditText) findViewById(R.id.txtMin);
        txtMax=(EditText)findViewById(R.id.txtMax);
        Spinner spinner=(Spinner) findViewById(R.id.regionSpinner);
        spinner.setOnItemSelectedListener(FilterActivity.this);
        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(
                this,R.layout.spinner_item,list);
        spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(spinnerArrayAdapter);

    }
    public void onRadioButtonClick(View view){
        boolean radio=((RadioButton)view).isChecked();
        switch(view.getId()){
            case R.id.female_Radio:
                if(radio){  gender="girl";}

                break;
            case R.id.male_Radio:
                if(radio){  gender="boy";}

                break;
        }

    }
    public void closeClick(View v){
        finish();
    }
    public void clearClick(View v){
        txtMin.setText("");
        txtMax.setText("");
    }
    public void applyClick(View v){
        Intent i=new Intent(this,ShowUniversity.class);
        i.putExtra("filter","yes");
        i.putExtra("min",txtMin.getText().toString());
        i.putExtra("max",txtMax.getText().toString());
        i.putExtra("region",region);
        i.putExtra("gender",gender);
        startActivity(i);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        region=parent.getItemAtPosition(position).toString();

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {


    }
}
