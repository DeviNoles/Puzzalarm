package com.cop4020.puzzalarm.ui.alarms;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CompoundButton;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.cop4020.puzzalarm.R;
import com.cop4020.puzzalarm.services.AlarmManager;

import java.util.ArrayList;


public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    private static final String TAG = "RecyclerViewAdapter";
   // private ArrayList<String> mImages = new ArrayList<>();
    private ArrayList<Pair <Integer,Integer> > alarmList;
    private ArrayList<Intent> serviceList = new ArrayList<>();
    private Context mContext;
    private AlarmsInternalStorage alarmStore;

    public RecyclerViewAdapter(Context context, ArrayList<Pair <Integer,Integer>> times ) {
        alarmList = times;
        mContext = context;
        alarmStore = new AlarmsInternalStorage(mContext);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        Log.d(TAG, "onBindViewHolder: called.");

        Integer hr = alarmList.get(position).first;
        Integer mn = alarmList.get(position).second;
        if(holder.alarmSwitch.isChecked()){
            holder.parentLayout.setBackground(mContext.getResources().getDrawable(R.drawable.layout_bg, null));
        }
        if(!holder.alarmSwitch.isChecked()){
            holder.parentLayout.setBackground(mContext.getResources().getDrawable(R.drawable.layout_bg_off, null));
        }
        if(hr>12){
            hr = hr-12;
        }
        if (hr == 0){
            hr = 12;
        }
        if(mn<10){
            String min = "0";
            min= min + mn;
            holder.alarmTime.setText("" + hr + ":" + min);

        }
        else{
            holder.alarmTime.setText("" + hr + ":" + mn);
        }

        holder.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "onClick: clicked on: " + alarmList.get(position).first + alarmList.get(position).second);


           //     Toast.makeText(mContext, + alarmList.get(position).first + ":" + alarmList.get(position).second, Toast.LENGTH_SHORT).show();
            }

        });
        holder.alarmSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                // making variables to update internal storage
                String temp = holder.alarmTime.getText().toString();
                String temp_split[] = temp.split(":");
                int h = Integer.valueOf(temp_split[0]);
                int m = Integer.valueOf(temp_split[1]);

                // do something, the isChecked will be
                // true if the switch is in the On position
                if(holder.alarmSwitch.isChecked()==Boolean.TRUE){
                    holder.parentLayout.setBackground(mContext.getResources().getDrawable(R.drawable.layout_bg, null));

                    // Update checks internal storage
                    alarmStore.update(h, m, true);

                } else if (holder.alarmSwitch.isChecked()==Boolean.FALSE) {
                    holder.parentLayout.setBackground(mContext.getResources().getDrawable(R.drawable.layout_bg_off, null));

                    // Update checks internal storage
                    alarmStore.update(h, m, false);
                }
            }
        });

        // add alarm to internal storage
        alarmStore.add(hr, mn, true);
        // Updating switches
        holder.alarmSwitch.setChecked(alarmStore.getSt_checks().get(position));
        Log.d(TAG, "onBindViewHolder: " + alarmStore.getSt_checks().get(position));

        Log.d(TAG, "HERERERE");
        callIntent(hr, mn);


    }

    public void callIntent(int hour, int minute) {
        Intent intent = new Intent(mContext.getApplicationContext(), AlarmManager.class);
        intent.putExtra("hour",hour);
        intent.putExtra("minute",minute);
        serviceList.add(intent);
        mContext.getApplicationContext().startService(intent);
    }

    @Override
    public int getItemCount() {
        return alarmList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView alarmTime;
        ConstraintLayout parentLayout;
        Switch alarmSwitch;
        public ViewHolder(View itemView) {
            super(itemView);
            alarmTime = itemView.findViewById(R.id.alarmTime);
            parentLayout = itemView.findViewById(R.id.parentLayout);
            alarmSwitch = itemView.findViewById(R.id.alarmSwitch);
        }
    }
}