package com.mycloud.pyaephyo.materialnav.ApplicationService;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.mycloud.pyaephyo.materialnav.Model.TimetableRowColHolder;
import com.mycloud.pyaephyo.materialnav.R;
import com.mycloud.pyaephyo.materialnav.TimeTableProcess.TimetableInputActivity;

public class TimetableActivity extends Activity {

    EditText txtDay,txtTime;
    Button btnCreate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.timetable_layout);

        txtDay=(EditText)findViewById(R.id.txtDay);
        txtTime=(EditText)findViewById(R.id.txtTime);
        btnCreate=(Button)findViewById(R.id.btnCreate);




        btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TimetableRowColHolder holder = TimetableRowColHolder.getInstance();

                holder.setRow(Integer.parseInt(txtDay.getText().toString()));
                holder.setCol(Integer.parseInt(txtTime.getText().toString()));
                Intent i=new Intent(TimetableActivity.this, TimetableInputActivity.class);
                startActivity(i);
            }
        });
    }
}

