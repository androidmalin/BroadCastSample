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
        //网络状态改变广播--需要权限
        //开机广播--需要权限
        if (action.equals(ConnectivityManager.CONNECTIVITY_ACTION)) {
            Toast.makeText(context, NetWorkUtil.getNetWorkInfo(context), Toast.LENGTH_LONG).show();
            Log.d(TAG, "静态广播:" + NetWorkUtil.getNetWorkInfo(context));
        }else if (action.equals("android.intent.action.BOOT_COMPLETED")){
            Log.d(TAG,"收到开机广播");
            Toast.makeText(context, "收到开机广播", Toast.LENGTH_SHORT).show();
        }


    }
}
