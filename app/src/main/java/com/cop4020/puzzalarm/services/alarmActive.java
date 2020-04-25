package com.cop4020.puzzalarm.services;

import android.content.Context;
import android.content.SharedPreferences;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.cop4020.puzzalarm.R;

import androidx.appcompat.app.AppCompatActivity;

import java.math.BigInteger;
import java.util.Random;

public class alarmActive extends AppCompatActivity {

    Ringtone rt;
    private int math_answer;
    private String pw_answer;

    RadioButton radioButton;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String TAG = "alarmActive";
        Log.d(TAG, "RINGING");
        Uri rm = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_RINGTONE);
        rt = RingtoneManager.getRingtone(getApplicationContext(), rm);
        rt.play();

        sharedPreferences = getApplicationContext().getSharedPreferences("PuzzlePref", Context.MODE_PRIVATE);
        String nameOfRadButton = sharedPreferences.getString("selectedPuzzle", "");

        // need to switch between depending on result of shared preferences
        if (nameOfRadButton.equals("Math Puzzle"))
            mathGame();
        else if (nameOfRadButton.equals("Password Puzzle"))
            passwordGame();
    }

    // this function was created using the GeeksforGeeks method seen at: https://www.geeksforgeeks.org/quick-ways-to-check-for-prime-and-find-next-prime-in-java/
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
            math_answer = tnum1+tnum2;
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
            math_answer = tnum1-tnum2;
            String tQuestion = tnum1 + " - " + tnum2;
            question.setText(tQuestion);
        }
        else if (signVal==3) {
            r = new Random();
            // limiting possible numbers to 10 for realistic expectations
            int tnum1  = r.nextInt(10) + 1;
            int tnum2  = r.nextInt(10) + 1;
            math_answer = tnum1*tnum2;
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
            math_answer = tnum1/tnum2;
            String tQuestion = tnum1 + " ÷ " + tnum2;
            question.setText(tQuestion);
        }
        Button submit = findViewById(R.id.submit);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText userInput = findViewById(R.id.userInput);
                int tuserInput = Integer.parseInt(userInput.getText().toString());
                if(tuserInput == math_answer) {
                    rt.stop();
                    finish();
                }
            }
        });
    }

    static String getRandomString(int size) {

        Random r = new Random();
        char[] characterPool = {'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q',
                'R','S','T','U','V','W','X','Y','Z','0','1','2','3','4','5','6','7','8','9','a','b',
                'c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','x',
                'y','z'};

        String res = "";
        for (int i = 0; i < size; i++) {
            res = res + characterPool[r.nextInt(characterPool.length)];
        }

        return res;
    }

    private void passwordGame() {
        setContentView(R.layout.password);
        TextView password = findViewById(R.id.password_text);

        password.setText(getRandomString(18));
        pw_answer = password.getText().toString();

        Button submit = findViewById(R.id.pw_submit);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText userInput = findViewById(R.id.pw_userInput);
                String tuserInput = userInput.getText().toString();
                if(tuserInput.equals(pw_answer)) {
                    rt.stop();
                    finish();
                }
            }
        });
    }
}
