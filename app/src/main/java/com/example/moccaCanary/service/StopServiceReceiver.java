package com.example.moccaCanary.service;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.example.moccaCanary.MainActivity;
import com.example.moccaCanary.menu.home.HomeFragment;


public class StopServiceReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {

        Intent service = new Intent(context, CanaryService.class);
        context.stopService(service);


        Intent activity = new Intent(context, MainActivity.class);
        activity.putExtra("sign", 2);
        context.startActivity(activity.addFlags(intent.FLAG_ACTIVITY_NEW_TASK));


    }
}
