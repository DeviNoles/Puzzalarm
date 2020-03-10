package com.cop4020.puzzalarm.services;

import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.cop4020.puzzalarm.R;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

public class alarmActive extends AppCompatActivity {
    Ringtone rt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String TAG = "alarmActive";
        Log.d(TAG, "RINGING");
        Uri rm = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_RINGTONE);
        rt = RingtoneManager.getRingtone(getApplicationContext(), rm);
        rt.play();
        mathGame();
    }
    private void mathGame(){
        setContentView(R.layout.math);
        TextView num1 = findViewById(R.id.num1);
        TextView num2 = findViewById(R.id.num2);
        Random r = new Random();
        num1.setText(String.valueOf(r.nextInt(100)));
        r = new Random();
        num2.setText(String.valueOf(r.nextInt(100)));
        Button submit = findViewById(R.id.submit);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText userInput = findViewById(R.id.userInput);
                TextView answerNum1 = findViewById(R.id.num1);
                TextView answerNum2 = findViewById(R.id.num2);
                int tnum1 = Integer.parseInt(answerNum1.getText().toString());
                int tnum2 = Integer.parseInt(answerNum2.getText().toString());
                int tuserInput = Integer.parseInt(userInput.getText().toString());
                if(tuserInput == tnum1 * tnum2)
                {
                    rt.stop();

                }
            }
        });



    }



}
