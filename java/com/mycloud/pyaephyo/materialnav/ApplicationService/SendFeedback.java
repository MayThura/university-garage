package com.mycloud.pyaephyo.materialnav.ApplicationService;

/**
 * Created by ucsm on 10/27/2016.
 */
import android.app.Activity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.mycloud.pyaephyo.materialnav.R;

public class SendFeedback extends Activity {
    Button sendBtn;
   public String phoneNo="09400551949";
    EditText txtMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.feedback_layout);

        sendBtn = (Button) findViewById(R.id.btnMessage);

        txtMessage = (EditText) findViewById(R.id.message);

        sendBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                sendSMSMessage();
            }
        });
    }
    protected void sendSMSMessage() {
        Log.i("Send SMS", "");

        String message = txtMessage.getText().toString();

        try {
            SmsManager smsManager = SmsManager.getDefault();
            smsManager.sendTextMessage(phoneNo, null, message, null, null);
            Toast.makeText(getApplicationContext(), "Feedback have been  sent.", Toast.LENGTH_LONG).show();
        }

        catch (Exception e) {
            Toast.makeText(getApplicationContext(), "SMS fail, please try again.", Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }
    }


}