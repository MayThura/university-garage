package com.mycloud.pyaephyo.materialnav.TimeTableProcess;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.mycloud.pyaephyo.materialnav.Model.TimeTablePeriodModel;
import com.mycloud.pyaephyo.materialnav.Model.TimetableRowColHolder;
import com.mycloud.pyaephyo.materialnav.R;
import com.mycloud.pyaephyo.materialnav.TimetableDatabase.TimeTableDatabaseHandler;

public class TimetableShowActivity extends Activity {

    TableLayout timetable;
    TimeTableDatabaseHandler dh;
    TimeTablePeriodModel[] showPeriod;
    private static final String TAG = TimetableShowActivity.class.getSimpleName();

    EditText[] tv;

    Button btnEdit;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.show_timetable);

        btnEdit=(Button)findViewById(R.id.btnEdit);

        timetable = (TableLayout) findViewById(R.id.timetable);

        dh=new TimeTableDatabaseHandler(this);

try {
    showPeriod = dh.getAll();
}
catch (Exception e) {
    Log.i(TAG, e.toString());
    e.printStackTrace();
}
        try {
            Log.i(TAG, "DATA FROM DATABASE ......................!!!!!!!!!!!!!!!!!!!!................................!!!!!!!!!!!!!!!!!!!!!!...................");
            for (int i = 0; i < showPeriod.length; i++) {
                Log.i(TAG, showPeriod[i].toString());
            }

        }
        catch(Exception e) {
            Log.i(TAG, e.toString());
            e.printStackTrace();
        }
            final TimetableRowColHolder holder = TimetableRowColHolder.getInstance();

            TableRow tRow = new TableRow(this);
            tRow.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT,
                    TableRow.LayoutParams.WRAP_CONTENT));


        tv = new EditText[holder.getCol()*holder.getRow()];

        Log.i("Length of et array", tv.length + " is the length");

            for(int i=-1; i<holder.getCol() ; i++) {
                TextView temp = new TextView(this);

                temp.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT,
                        TableRow.LayoutParams.WRAP_CONTENT));

                temp.setBackgroundResource(R.drawable.timetable_rectangle);
                temp.setGravity(Gravity.CENTER);
                temp.setTextSize(20);
                temp.setPadding(0, 5, 0, 5);
                if(i != -1) {
                    temp.setText("Period " + (i+1));
                }

                else{
                    temp.setBackgroundColor(Color.parseColor("#55FFFFFF"));
                }

                tRow.addView(temp);

            }

            timetable.addView(tRow);

        final String[] days = new String[] {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};


        for (int i = 0; i < showPeriod.length; i++) {
            Log.i("Before reading input", showPeriod[i].toString());
        }


        for (int i = 0, k=0,l=0; i < holder.getRow(); i++) {

            TableRow tableRow = new TableRow(this);
            tableRow.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT,
                    TableRow.LayoutParams.WRAP_CONTENT));
            tableRow.setFocusable(true);
            for (int j = -1 ; j < holder.getCol(); j++) {

                if(j != -1) {
                    tv[l]  = new EditText(this);
                    decorateEditText(tv[l]);
                    tv[l].setText(showPeriod[k].getSubject().toString());
                    Log.i("READING INPUTTING VIEWS", showPeriod[j].toString());
                    tableRow.addView(tv[k]);
                    k++;
                    l++;
                    continue;
                }

                else {


                   EditText temp = new EditText(this);

                    decorateEditText(temp);

                    temp.setText(days[i]);
                    tableRow.addView(temp);
                    continue;

                }

            }
            timetable.addView(tableRow);

        }

        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent edit = new Intent(TimetableShowActivity.this, TimetableEditActivity.class);
                startActivity(edit);
                finish();
            }
        });

    }

    public void decorateEditText(EditText target) {

        target.setFocusable(false);
        target.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT,
                TableRow.LayoutParams.WRAP_CONTENT));
        target.setBackgroundResource(R.drawable.timetable_rectangle);
        target.setGravity(Gravity.CENTER);
        target.setTextSize(20);
        target.setPadding(0, 5, 0, 5);


    }

}
