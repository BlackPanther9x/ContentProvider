package com.example.vaio.everythingme;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class MyReceiver extends BroadcastReceiver {
    public static String EXTRA_VALUE= "extra value";

    @Override
    public void onReceive(Context context, Intent intent) {
        String value= intent.getExtras().getString(MainActivity.EXTRA_SEND_SONG);
        if(value!=null){
            context.sendBroadcast(new Intent("test").putExtra(EXTRA_VALUE, value));
        }
    }
}
