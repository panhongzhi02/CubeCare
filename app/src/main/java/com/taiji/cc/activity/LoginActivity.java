package com.taiji.cc.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.taiji.cc.R;

/**
 * Created by panho on 2016/8/30.
 */
public class LoginActivity extends AppCompatActivity{

    private Button btn_surelogin;

    private TextView tv_cannotlogin;//无法登陆

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_acy);
        getSupportActionBar().hide();

        btn_surelogin = (Button) findViewById(R.id.btn_surelogin);
        btn_surelogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this,HomeActivity.class);
                startActivity(intent);
            }
        });

        tv_cannotlogin = (TextView) findViewById(R.id.tv_cannotlogin);
        tv_cannotlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this,ConnotLoginActivity.class);
                startActivity(intent);
            }
        });
    }
}
