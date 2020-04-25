package com.cop4020.puzzalarm.ui.alarms;

import android.content.Context;
import android.util.Log;
import android.util.Pair;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class AlarmsInternalStorage {
    private static final String TAG = "AlarmsInternalStorage";

    // ----- member data -----
    private static final String FILENAME = "/data/data/com.cop4020.puzzalarm/files/alarms.txt";
    private ArrayList<Pair<Integer, Integer> > st_times = new ArrayList<>();
    private ArrayList<Boolean> st_checks = new ArrayList<>();
    private Context context;

    // ----- public class functions -----
    public AlarmsInternalStorage (Context context_c){
        context = context_c;

        // Load data into array
        FileInputStream fis = null;

        try {
            fis = new FileInputStream(FILENAME);
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader br = new BufferedReader(isr);
            String line;

            while((line = br.readLine()) != null){
                String[] data = line.split(":");
                int hr = Integer.valueOf(data[0]);
                int mn = Integer.valueOf(data[1]);
                String check = data[2];

                st_times.add(Pair.create(hr, mn));

                if (check.equals("true"))
                    st_checks.add(true);
                else
                    st_checks.add(false);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fis != null){
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    // adds an alarm to internal storage
    public boolean add(int hr, int mn, Boolean check){
        int index = exists(hr, mn);
        if (index != -1)
            return false;
        else{
            st_times.add(Pair.create(hr, mn));
            st_checks.add(check);
            write();
            return true;
        }
    }

    public boolean delete(int hr, int mn){
        int index = exists(hr, mn);
        if (index != -1){
            st_times.remove(index);
            st_checks.remove(index);
            write();
            return true;
        }
        else
            return false;
    }

    public boolean update(int hr, int mn, Boolean check){
        int index = exists(hr, mn);
        if (index != -1){
            st_checks.set(index, check);
            write();
            return true;
        }
        else
            return false;
    }

    public ArrayList< Pair <Integer, Integer> > getSt_times(){
        return st_times;
    }

    public ArrayList<Boolean> getSt_checks(){
        return st_checks;
    }


    // ----- private helper functions -----
    // returns index if item exists, -1 otherwise
    private int exists(int hr, int mn){
        for (int i = 0; i < st_times.size(); i++){
            if(st_times.get(i).first == hr && st_times.get(i).second == mn)
                return i;
        }
        return -1;
    }

    // writes the newly altered list to the file
    private void write(){
        // save contents of alarms into one string
        String times = new String();
        for (int i = 0; i < st_times.size(); i++){
            times = times.concat(st_times.get(i).first + ":");
            times = times.concat(st_times.get(i).second + ":");
            if (st_checks.get(i))
                times = times.concat("true" + "\n");
            else
                times = times.concat("false" + "\n");
        }

        // write the times string to the file
        FileOutputStream fos = null;

        try {
            fos = new FileOutputStream(FILENAME);
            fos.write(times.getBytes());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
