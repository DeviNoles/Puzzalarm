package com.cop4020.puzzalarm.services;

import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

public class alarmActive extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);

        String TAG = "alarmActive";
        Log.d(TAG, "RINGING");
        Uri rm = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_RINGTONE);
        Ringtone rt = RingtoneManager.getRingtone(getApplicationContext(), rm);
        rt.play();


    }


}
