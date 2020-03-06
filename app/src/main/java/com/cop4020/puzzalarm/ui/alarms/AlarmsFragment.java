package com.cop4020.puzzalarm.ui.alarms;

import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.cop4020.puzzalarm.R;
import com.cop4020.puzzalarm.RecyclerViewAdapter;
import com.cop4020.puzzalarm.ui.alarms.AlarmsViewModel;
import com.cop4020.puzzalarm.ui.timepicker.TimePicker;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.Calendar;

public class AlarmsFragment extends Fragment {

    private AlarmsViewModel alarmViewModel;
    private ArrayList<String> mNames = new ArrayList<>();
    private ArrayList<String> mImageUrls = new ArrayList<>();



    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {


        alarmViewModel =
                ViewModelProviders.of(this).get(AlarmsViewModel.class);
        View root = inflater.inflate(R.layout.fragment_alarms, container, false);

        com.google.android.material.floatingactionbutton.FloatingActionButton fabbtn = root.findViewById(R.id.floating_action_button);
        mImageUrls.add("https://c1.staticflickr.com/5/4636/25316407448_de5fbf183d_o.jpg");
        mNames.add("Havasu Falls");

        mImageUrls.add("https://i.redd.it/tpsnoz5bzo501.jpg");
        mNames.add("Trondheim");

        mImageUrls.add("https://i.redd.it/qn7f9oqu7o501.jpg");
        mNames.add("Portugal");
        RecyclerView recyclerView = (RecyclerView) root.findViewById(R.id.my_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(getContext(), mImageUrls, mNames);

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

                    }
                };
                final Calendar myCalender = Calendar.getInstance();
                int hour = myCalender.get(Calendar.HOUR_OF_DAY);
                int minute = myCalender.get(Calendar.MINUTE);
                TimePickerDialog timePickerDialog = new TimePickerDialog(getActivity(), android.R.style.Theme_Holo_Light_Dialog_NoActionBar, myTimeListener, hour, minute, false);
                timePickerDialog.setTitle("Set New Alarm:");
                timePickerDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
                timePickerDialog.show();
            }
        });

        return root;
    }

    private void hideMenu(){

    }


}