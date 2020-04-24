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

import java.math.BigInteger;
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

    static boolean isPrime(long n)
    {
        BigInteger b = new BigInteger(String.valueOf(n));
        return b.isProbablePrime(1);
    }

    private void mathGame(){
        setContentView(R.layout.math);
        TextView question = findViewById(R.id.question);

        Random r = new Random();
        int signVal = r.nextInt(4)+1;
        signVal = 4;
        if (signVal==1){
            r = new Random();
            int tnum1  = r.nextInt(100) + 20;
            int tnum2  = r.nextInt(100) + 20;
            answer = tnum1+tnum2;
            String tQuestion = tnum1 + " + " + tnum2;
            question.setText(tQuestion);
        }
        else if (signVal==2) {
            int tnum1  = r.nextInt(100) + 20;
            int tnum2;
            // making sure the first value is always greater than the second
            do {
                tnum2 = r.nextInt(100) + 20;
            } while (tnum2 > tnum1);
            answer = tnum1-tnum2;
            String tQuestion = tnum1 + " - " + tnum2;
            question.setText(tQuestion);
        }
        else if (signVal==3) {
            r = new Random();
            // limiting possible numbers to 10 for realistic expectations
            int tnum1  = r.nextInt(10) + 1;
            int tnum2  = r.nextInt(10) + 1;
            answer = tnum1*tnum2;
            String tQuestion = tnum1 + " × " + tnum2;
            question.setText(tQuestion);
        }
        else if (signVal==4) {
            int tnum1;
            int tnum2;
            // making sure the first value is not prime
            do {
                tnum1  = r.nextInt(100) + 1;
            } while (isPrime(tnum1));
            // making sure the first value is always divisible by the second,
            //  both values aren't equal, and the second value is not 1, making it too easy
            do {
                tnum2  = r.nextInt(100) + 1;
            } while ((tnum1 % tnum2) != 0 || tnum2 == 1 || tnum2 >= tnum1);
            answer = tnum1/tnum2;
            String tQuestion = tnum1 + " ÷ " + tnum2;
            question.setText(tQuestion);
        }
        Button submit = findViewById(R.id.submit);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText userInput = findViewById(R.id.userInput);
                int tuserInput = Integer.parseInt(userInput.getText().toString());
                if(tuserInput == answer) {
                    rt.stop();
                    finish();
                }
            }
        });
    }
}
