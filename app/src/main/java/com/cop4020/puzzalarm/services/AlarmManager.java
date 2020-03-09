package com.cop4020.puzzalarm.services;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

import androidx.annotation.Nullable;

public class AlarmManager extends IntentService {
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
        super("GameService");
    }
    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "MyIntentService onCreate() method is invoked.");
    }

    public AlarmManager(String name, Integer hour, Integer minute ) {
        super(name);

    }


    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        try {
            Log.d(TAG, "Alarm Manager Intent ");
            this.hour = intent.getIntExtra("hour", 0);
            this.minute = intent.getIntExtra("minute", 0);

            Thread.sleep(5000);
        } catch (InterruptedException e) {
            // Restore interrupt status.
            Thread.currentThread().interrupt();
        }
    }
}
