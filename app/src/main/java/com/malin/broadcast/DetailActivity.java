package com.malin.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


public class DetailActivity extends AppCompatActivity implements View.OnClickListener {

    private Button mButtonSend;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_layout);
        mButtonSend = (Button) findViewById(R.id.btn_send);
        mButtonSend.setOnClickListener(this);


    }

    @Override
    protected void onStart() {
        super.onStart();
        registerReceiver();
    }

    private void registerReceiver() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(ConnectivityManager.CONNECTIVITY_ACTION);
        registerReceiver(netWorkChangeReceiver, intentFilter);
    }

    private void unregisterReceiver() {
        if (netWorkChangeReceiver != null) {
            unregisterReceiver(netWorkChangeReceiver);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_send: {
                Intent intent = new Intent();
                intent.setAction(Contants.ACTION_TOAST);
                intent.putExtra("message", "come from DetailActivity's message");
                sendBroadcast(intent);
                break;
            }

            default: {
                break;
            }
        }
    }


    private static final String TAG = "HHHHHHHHHHHH";
    BroadcastReceiver netWorkChangeReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (action.equals(ConnectivityManager.CONNECTIVITY_ACTION)) {
                Log.d(TAG, "onReceive change");
                ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
                NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
                if (networkInfo!=null&&networkInfo.isAvailable()){
                    Toast.makeText(DetailActivity.this, "网络可用", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(DetailActivity.this, "网络不可用", Toast.LENGTH_SHORT).show();
                }
            }
        }
    };

}
