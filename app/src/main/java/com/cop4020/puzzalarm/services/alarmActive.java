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
    int answer;
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
        TextView sign= findViewById(R.id.sign);

        Random r = new Random();
        num1.setText(String.valueOf(r.nextInt(5))+1);
        r = new Random();
        num2.setText(String.valueOf(r.nextInt(5))+1);
        int tnum1 = Integer.parseInt(num1.getText().toString());
        int tnum2 = Integer.parseInt(num2.getText().toString());
        r = new Random();
        int signVal = r.nextInt(4)+1;
        if (signVal==1){
            sign.setText("+");
            answer = tnum1+tnum2;
        }
        else if (signVal==2) {
            sign.setText("-");
            answer = tnum1-tnum2;
        }
        else if (signVal==3) {
            sign.setText("*");
            answer = tnum1*tnum2;
        }
        else if (signVal==4) {
            sign.setText("/");
            answer = tnum1/tnum2;
        }
        Button submit = findViewById(R.id.submit);





        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText userInput = findViewById(R.id.userInput);
                int tuserInput = Integer.parseInt(userInput.getText().toString());
                if(tuserInput == alarmActive.this.answer)
                {
                    rt.stop();

                }
            }
        });



    }



}
