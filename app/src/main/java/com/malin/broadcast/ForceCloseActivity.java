package com.malin.broadcast;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;

public class ForceCloseActivity extends BaseActivity implements View.OnClickListener {
    private Button mButtonClose;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.force_close_layout);
        initView();
        initListener();
    }

    private void initView() {
        mButtonClose = (Button) findViewById(R.id.btn_send_close);
    }

    private void initListener() {
        mButtonClose.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_send_close: {
                Intent intent = new Intent();
                intent.setAction(Constant.ACTION_MY_FORCE_CLOSE);
                sendBroadcast(intent);
                break;
            }

            default: {
                break;
            }
        }
    }
}
