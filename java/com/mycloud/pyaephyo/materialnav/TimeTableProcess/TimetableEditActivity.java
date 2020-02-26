package com.mycloud.pyaephyo.materialnav.TimeTableProcess;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.mycloud.pyaephyo.materialnav.Model.TimeTablePeriodModel;
import com.mycloud.pyaephyo.materialnav.Model.TimetableRowColHolder;
import com.mycloud.pyaephyo.materialnav.R;
import com.mycloud.pyaephyo.materialnav.TimetableDatabase.TimeTableDatabaseHandler;


public class TimetableEditActivity extends Activity {

    EditText[] et;
    TimetableRowColHolder holder;
    LinearLayout layout;
    Button btnShow;
    TimeTableDatabaseHandler dh = new TimeTableDatabaseHandler(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.timetable_edit);

        layout=(LinearLayout)findViewById(R.id.layout);



        btnShow = (Button) findViewById(R.id.btnShow);

        holder = TimetableRowColHolder.getInstance();


        final TimeTablePeriodModel[] showPeriod = dh.getAll();


        et = new EditText[ (holder.getCol())*(holder.getRow())];


        for (int i = 0, k=0,l=0; i < holder.getRow(); i++) {

            for (int j = 0 ; j < holder.getCol(); j++) {

                    et[l]  = new EditText(this);
                    decorateEditText(et[l]);
                    et[l].setText(showPeriod[k].getSubject().toString());
                    Log.i("READING INPUTTING VIEWS", showPeriod[j].toString());

                    layout.addView(et[l]);
                    k++;
                    l++;

                continue;


            }

        }
        final String[] days = new String[] {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};
        btnShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                for(int i=0; i<holder.getCol()*holder.getRow(); i++) {

                    showPeriod[i].setSubject(et[i].getText().toString());


                }

                dh.updatePeriods(showPeriod);

                Intent intent = new Intent(TimetableEditActivity.this, TimetableShowActivity.class);
                startActivity(intent);
                finish();

            }
        });

    }

    public void decorateEditText(EditText target) {
        target.setGravity(Gravity.CENTER);
        target.setTextSize(20);
        target.setPadding(0, 5, 0, 5);


    }

}
