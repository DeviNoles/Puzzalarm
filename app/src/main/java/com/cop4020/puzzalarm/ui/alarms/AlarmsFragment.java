package com.cop4020.puzzalarm.ui.alarms;

import android.app.TimePickerDialog;
import android.os.Bundle;
import android.util.Log;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.cop4020.puzzalarm.R;

import java.util.ArrayList;
import java.util.Calendar;

public class AlarmsFragment extends Fragment {

    private AlarmsViewModel alarmViewModel;
    private ArrayList<Pair <Integer,Integer> > times = new ArrayList<>();



    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {


        alarmViewModel =
                ViewModelProviders.of(this).get(AlarmsViewModel.class);
        View root = inflater.inflate(R.layout.fragment_alarms, container, false);

        com.google.android.material.floatingactionbutton.FloatingActionButton fabbtn = root.findViewById(R.id.floating_action_button);
        RecyclerView recyclerView = root.findViewById(R.id.my_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        final RecyclerViewAdapter adapter = new RecyclerViewAdapter(getActivity(), times);

        recyclerView.setAdapter(adapter);

        fabbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {/*
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
               // AlarmsFragment fragment = new AlarmsFragment();
                TimePicker frag = new TimePicker();
               // fragmentTransaction.replace(R.id.nav_host_fragment, frag);
              //  fragmentTransaction.commit();
              -------------- going to copy and paste into open game function later.
*/
                TimePickerDialog.OnTimeSetListener myTimeListener = new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(android.widget.TimePicker view, int hourOfDay, int minute) {
                       // String TAG = Integer.toString(hourOfDay);
                        Log.d("ADebugTag", "Value: " + hourOfDay + ":" + minute);
                        times.add(Pair.create(hourOfDay, minute));
                        adapter.notifyDataSetChanged();

                    }
                };
                final Calendar myCalender = Calendar.getInstance();
                int hour = myCalender.get(Calendar.HOUR_OF_DAY);
                int minute = myCalender.get(Calendar.MINUTE);
                TimePickerDialog timePickerDialog = new TimePickerDialog(getActivity(), android.R.style.Theme_Holo_Light_Dialog_NoActionBar, myTimeListener, hour, minute, false);
                timePickerDialog.setTitle("Set New Alarm:");
                timePickerDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);


            //    AlarmManager alarmManager = new AlarmManager("alarmManager", hour, minute);

                //  mContext.startService(alarmManager);
                timePickerDialog.show();


            }
        });

        return root;
    }

    private void hideMenu(){

    }


}