package com.example.administrator.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText etName;
    private EditText etPwd;
    private TextView btLogin;
    private TextView tv_register;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        StatusBarUtil.setTranslucent(this,0);
        init();
    }

    private void init() {
        etName = findViewById(R.id.et_name);
        etPwd = findViewById(R.id.et_pwd);
        btLogin = findViewById(R.id.bt_login);
        tv_register = findViewById(R.id.tv_register);
        btLogin.setOnClickListener(this);
        tv_register.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.bt_login:
                String name=etName.getText().toString().trim();
                String pass=etPwd.getText().toString().trim();
                if (TextUtils.isEmpty(name) || TextUtils.isEmpty(pass)){
                    Toast.makeText(LoginActivity.this,"用户名和密码不能为空",Toast.LENGTH_SHORT).show();
                }else if("admin".equals(name) && "111111".equals(pass)){
                    Toast.makeText(LoginActivity.this,"登录成功！",Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(this,MainActivity.class));

                }else{
                    Toast.makeText(LoginActivity.this,"用户名或密码不正确",Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.tv_register:
                break;
        }
    }


}
