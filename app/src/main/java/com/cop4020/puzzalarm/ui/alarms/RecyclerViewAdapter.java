package com.cop4020.puzzalarm.ui.alarms;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.cop4020.puzzalarm.R;
import com.cop4020.puzzalarm.services.AlarmManager;

import java.util.ArrayList;


public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>{

    private static final String TAG = "RecyclerViewAdapter";
   // private ArrayList<String> mImages = new ArrayList<>();
    private ArrayList<Pair <Integer,Integer> > alarmList;
    private ArrayList<Intent> serviceList = new ArrayList<>();
    private Context mContext;

    public RecyclerViewAdapter(Context context, ArrayList<Pair <Integer,Integer>> times ) {
        alarmList = times;
        mContext = context;
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
        if(hr>12){
            hr = hr-12;
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
        RelativeLayout parentLayout;
        public ViewHolder(View itemView) {
            super(itemView);
            alarmTime = itemView.findViewById(R.id.alarmTime);
            parentLayout = itemView.findViewById(R.id.parentLayout);
        }
    }
}