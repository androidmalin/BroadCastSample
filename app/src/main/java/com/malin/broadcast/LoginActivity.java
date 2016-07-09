package com.malin.broadcast;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class LoginActivity extends BaseActivity implements View.OnClickListener {

    private EditText mEditTextNickName;
    private EditText mEditTextPwd;
    private Button mButtonLogin;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_layout);
        initView();
        initListener();
    }

    private void initView() {
        mEditTextNickName = (EditText) findViewById(R.id.ed_nickname);
        mEditTextPwd = (EditText) findViewById(R.id.ed_pwd);
        mButtonLogin = (Button) findViewById(R.id.btn_login);
    }

    private void initListener() {
        mButtonLogin.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_login: {
                String name = mEditTextNickName.getText().toString();
                String password = mEditTextPwd.getText().toString();
                if (name.equals("abc")&&password.equals("abc")){
                    startActivity(new Intent(LoginActivity.this,ForceCloseActivity.class));
                }
                break;
            }

            default: {
                break;
            }
        }
    }
}
