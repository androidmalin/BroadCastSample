package com.malin.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class LocalBroadCastActivity extends AppCompatActivity implements View.OnClickListener {
    private Button mButtonLocal;
    private LocalBroadcastManager mLocalBroadcastManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.local_layout);
        initView();
        initListener();
    }

    @Override
    protected void onStart() {
        super.onStart();
        mLocalBroadcastManager = LocalBroadcastManager.getInstance(this);
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("local_broadcast");
        mLocalBroadcastManager.registerReceiver(localBroadcastReceiver,intentFilter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mLocalBroadcastManager.unregisterReceiver(localBroadcastReceiver);
    }

    private void initView() {
        mButtonLocal = (Button) findViewById(R.id.btn_send_local);
    }

    private void initListener() {
        mButtonLocal.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_send_local: {
                Intent intent = new Intent();
                intent.setAction("local_broadcast");
                mLocalBroadcastManager.sendBroadcast(intent);
                break;
            }

            default: {
                break;
            }
        }
    }

    BroadcastReceiver localBroadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (action.equals("local_broadcast")){
                Toast.makeText(LocalBroadCastActivity.this, "收到本地广播一条", Toast.LENGTH_SHORT).show();
            }
        }
    };
}
