package com.malin.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.util.Log;
import android.widget.Toast;

/**
 * 静态广播--接收系统的广播
 */
public class NetWorkChangeBroadCast extends BroadcastReceiver {
    private static final String TAG = NetWorkChangeBroadCast.class.getSimpleName();

    @Override
    public void onReceive(final Context context, Intent intent) {
        String action = intent.getAction();
        if (action.equals(ConnectivityManager.CONNECTIVITY_ACTION)) {
            Toast.makeText(context, NetWorkUtil.getNetWorkInfo(context), Toast.LENGTH_LONG).show();
            Log.d(TAG, "静态广播:" + NetWorkUtil.getNetWorkInfo(context));
        }
    }
}
