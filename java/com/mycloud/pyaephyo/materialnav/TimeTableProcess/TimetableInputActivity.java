package com.mycloud.pyaephyo.materialnav.TimeTableProcess;

import android.app.Activity;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mycloud.pyaephyo.materialnav.Model.TimeTablePeriodModel;
import com.mycloud.pyaephyo.materialnav.Model.TimetableRowColHolder;
import com.mycloud.pyaephyo.materialnav.R;
import com.mycloud.pyaephyo.materialnav.TimetableDatabase.TimeTableDatabaseHandler;


public class TimetableInputActivity extends Activity {

    private static final String TAG=TimetableInputActivity.class.getSimpleName();

    static int count=0;
    String sub;
    TimeTablePeriodModel[] timeTablePeriodModels;
    TimeTableDatabaseHandler dh;
    TextView[] tv;
    EditText[] et;
    TextView dayName;
    public int current =0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.timetable_input);

        dh = new TimeTableDatabaseHandler(this);

       // LinearLayout layout = new LinearLayout(getBaseContext());
        LinearLayout layoutVertical=(LinearLayout)findViewById(R.id.layoutVertical);
        LinearLayout layoutHorizontal=(LinearLayout)findViewById(R.id.layoutHorizontal);
       // layout.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
       // layout.setOrientation(LinearLayout.VERTICAL);

        final TimetableRowColHolder holder = TimetableRowColHolder.getInstance();

         tv=new TextView[holder.getCol()];
         et=new EditText[holder.getCol()];

        timeTablePeriodModels = new TimeTablePeriodModel[holder.getCol()];

        final String[] days = new String[] {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};

        dayName=new TextView(getApplicationContext());
        dayName.setText(days[current]);
        dayName.setTextSize(25);
        dayName.setTextColor(Color.parseColor("#ff7018"));
        dayName.setPadding(0,0,0,30);
        layoutVertical.addView(dayName);


        for(int i=0; i<holder.getCol(); i++){

            timeTablePeriodModels[i] = new TimeTablePeriodModel();

            //LinearLayout layout1 = new LinearLayout(getApplicationContext());
            //layout1.setOrientation(LinearLayout.HORIZONTAL);

            tv[i] = new TextView(getApplicationContext());
            tv[i].setText("Period " + (i+1));

            et[i] = new EditText(getBaseContext());

            et[i].setSingleLine(true);
            et[i].setHint("Enter Subject at Period " + (i+1));

            layoutHorizontal.addView(tv[i]);
            layoutHorizontal.addView(et[i]);





        }

       // Button btnNext=new Button(getApplicationContext());
       // btnNext.setText("Next");
        //btnNext.setTextSize(20);
        //layout.addView(btnNext);

        this.setContentView(R.layout.timetable_input);
        Button btnNext=(Button) findViewById(R.id.btnNext);
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                for(int i=0; i<holder.getCol(); i++) {
                    sub=et[i].getText().toString();

                    timeTablePeriodModels[i].setDay(dayName.getText().toString());
                    timeTablePeriodModels[i].setPeriod(i+1);
                    timeTablePeriodModels[i].setSubject(sub);



                }

               dh.insertPeriods(timeTablePeriodModels);
                if(++count<holder.getRow()){
                    for(int i=0; i<holder.getCol(); i++) {
                        et[i].setText("");
                    }
                    dayName.setText(days[++current]);

                }
                else{
                    Intent i=new Intent(TimetableInputActivity.this, TimetableShowActivity.class);
                    startActivity(i);
                }
            }
        });
    }
}
