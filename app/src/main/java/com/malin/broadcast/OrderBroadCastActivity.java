package com.malin.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Toast;

public class OrderBroadCastActivity extends AppCompatActivity implements View.OnClickListener,CompoundButton.OnCheckedChangeListener {

    private Button mButtonOrder;
    private CheckBox mCheckBox;
    private boolean mClose;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.order_layout);
        initView();
        initListener();
    }

    @Override
    protected void onStart() {
        super.onStart();

        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(Constant.ACTION_MY_ORDER);
        intentFilter.setPriority(100);
        registerReceiver(orderBroadcastReceiver, intentFilter);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (orderBroadcastReceiver != null) {
            unregisterReceiver(orderBroadcastReceiver);
        }
    }

    private void initView() {
        mButtonOrder = (Button) findViewById(R.id.btn_send_order);
        mCheckBox  = (CheckBox) findViewById(R.id.checkbox);

    }

    private void initListener() {
        mButtonOrder.setOnClickListener(this);
        mCheckBox.setOnCheckedChangeListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_send_order: {
                Intent intent = new Intent();
                intent.setAction(Constant.ACTION_MY_ORDER);
                sendOrderedBroadcast(intent, null);
                break;
            }

            default: {
                break;
            }
        }
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

        if (isChecked){
            mClose = true;
        }else{
            mClose = false;
        }
    }



    BroadcastReceiver orderBroadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (action.equals(Constant.ACTION_MY_ORDER)) {
                Toast.makeText(OrderBroadCastActivity.this, "OrderBroadCastActivity \n 收到有序广播", Toast.LENGTH_SHORT).show();
            }
            if (mClose){
                abortBroadcast();
            }
        }
    };


}
