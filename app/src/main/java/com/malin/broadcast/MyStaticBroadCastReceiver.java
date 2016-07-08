package com.malin.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.widget.Toast;

public class MyStaticBroadCastReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();

        switch (action) {
            case Contants.ACTION_MY_BROADCAST: {
                String message = intent.getStringExtra("static_data");
                if (!TextUtils.isEmpty(message)) {
                    Toast.makeText(context, "MyStaticBroadCastReceiver 收到:\n "+message, Toast.LENGTH_SHORT).show();
                }
            }
        }
    }
}
