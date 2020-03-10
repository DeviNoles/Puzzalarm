package com.cop4020.puzzalarm.services;

import android.app.IntentService;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.Calendar;

public class AlarmManager extends IntentService {
    private ArrayList<Intent> serviceList;
    Integer hour;
    Integer minute;
    private static final String TAG = "AlarmManager";

    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     *
     * @param name Used to name the worker thread, important only for debugging.
     */

    public AlarmManager()
    {
        super("AlarmManager");
    }
    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "MyIntentService onCreate() method is invoked.");
    }

    private void timeFound(){
        Intent dialogIntent = new Intent(this, alarmActive.class);
        dialogIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        getApplicationContext().startActivity(dialogIntent);
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        Bundle extras = intent.getExtras();
        this.hour = extras.getInt("hour");
        this.minute = extras.getInt("minute");
        Log.d(TAG, "Alarm Manager Intent " + this.hour + this.minute);


        new Thread(new Runnable(){
            public void run() {
                while(true)
                {
                    Calendar myCalender = Calendar.getInstance();
                    int currentHour = myCalender.get(Calendar.HOUR_OF_DAY);
                    int currentMinute = myCalender.get(Calendar.MINUTE);
                    if (currentHour == hour && currentMinute == minute) {
                        timeFound();
                       break;

                    } else {

                        Log.d(TAG, "ALARM TIME " + hour + minute);
                        Log.d(TAG, "SYSTEM TIME " +  currentHour + currentMinute);
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            Log.d(TAG, e.getMessage());
                        }
                    }

                }

            }
        }).start();



      //  Toast.makeText(getApplicationContext(), this.hour + ":" + this.minute, Toast.LENGTH_LONG).show();
       // Toast.makeText(getApplicationContext(), + this.hour + ":" + this.minute, Toast.LENGTH_LONG).show();

    }

}