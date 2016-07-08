package com.malin.broadcast;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

/**
 * 自定义静态广播
 */
public class MyStaticBroadCastActivity extends AppCompatActivity implements View.OnClickListener {

    private Button mButtonStatic;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.static_layout);
        initView();
        initListener();
    }

    private void initView() {
        mButtonStatic = (Button) findViewById(R.id.btn_send_static);
    }

    private void initListener() {
        mButtonStatic.setOnClickListener(this);
    }

    @Override
    protected void onStart() {
        super.onStart();

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_send_static: {
                Intent intent = new Intent();
                intent.setAction(Contants.ACTION_MY_BROADCAST);
                intent.putExtra("static_data","自定义静态广播");
                sendBroadcast(intent);
                break;
            }

            default: {
                break;
            }
        }
    }
}
