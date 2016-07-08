package com.malin.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "MMMMMMMMMMMMMMMMMMM";
    //adb devices
    //adb shell
    //chmod 777 /data/anr/traces.txt

    //Genymotion模拟器中,直接使用如下两条命令即可
    //adb shell su 0 cp /data/anr/traces.txt /sdcard/
    //adb pull /sdcard/traces.txt /home/malin/anr/
    //或者使用
    //cat /data/anr/traces.txt | more

    //Activity中运行在UI线程中的方法有:onCreate,onStart,onRestart,onResume,onPause,,onStop,onDestroy,onKeyDown,onClick等等

    //BroadCastReceiver中运行在UI线程中的方法有:onReceive

    private Button mButtonStart;
    private Button mButtonTwo;
    private Button mButtonOrder;
    private Button mButtonLocal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(TAG, "onCreate:" + isMainUIThread());
        initView();
        initListener();
    }

    private void initView() {
        mButtonStart = (Button) findViewById(R.id.btn_start);
        mButtonTwo = (Button) findViewById(R.id.btn_static);
        mButtonOrder = (Button) findViewById(R.id.btn_order);
        mButtonLocal = (Button) findViewById(R.id.btn_local);
    }

    private void initListener() {
        mButtonStart.setOnClickListener(this);
        mButtonTwo.setOnClickListener(this);
        mButtonOrder.setOnClickListener(this);
        mButtonLocal.setOnClickListener(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart:" + isMainUIThread());
        registerBroadCast();
    }

    private void registerBroadCast() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(Contants.ACTION_TOAST);
        intentFilter.addAction("action_order_broadcast");
        registerReceiver(myBroadcastReceiver, intentFilter);
    }

    private void unregisterBroadCast() {
        if (myBroadcastReceiver != null) {
            unregisterReceiver(myBroadcastReceiver);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume:" + isMainUIThread());
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause:" + isMainUIThread());
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "onStop:" + isMainUIThread());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy:" + isMainUIThread());
        unregisterBroadCast();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(TAG, "onRestart:" + isMainUIThread());
    }

    /**
     * Check if a Thread Is Main Thread in Android
     *
     * @return
     */
    private boolean isMainUIThread() {
        return Looper.myLooper() == Looper.getMainLooper();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if (keyCode == KeyEvent.KEYCODE_BACK) {
            Log.d(TAG, "onKeyDown:" + isMainUIThread());
            Toast.makeText(MainActivity.this, "back", Toast.LENGTH_SHORT).show();
        }
        return super.onKeyDown(keyCode, event);
    }

    BroadcastReceiver myBroadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            Log.d(TAG, "BroadcastReceiver onReceive:" + isMainUIThread());
            String action = intent.getAction();
            if (action.equals(Contants.ACTION_TOAST)) {
                String message = intent.getStringExtra("message");
                if (!TextUtils.isEmpty(message)) {
                    Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT).show();
                }
//                while (true) {
//                    Log.d(TAG, "onReceive");
//                }
            }else if (action.equals("action_order_broadcast")){
                Toast.makeText(MainActivity.this, "MainActivity \n 收到有序广播", Toast.LENGTH_SHORT).show();
            }
        }
    };


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_start: {
                startActivity(new Intent(this, DetailActivity.class));
                break;
            }
            case R.id.btn_static: {
                startActivity(new Intent(this, MyStaticBroadCastActivity.class));
                break;
            }

            case R.id.btn_order:{
                startActivity(new Intent(this, OrderBroadCastActivity.class));
                break;
            }
            case R.id.btn_local:{
                startActivity(new Intent(this,LocalBroadCastActivity.class));
                break;
            }
            default: {
                break;
            }
        }
    }
}
