package com.example.demo.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.demo.base.BaseActivity;
import com.example.demo.R;
import com.example.demo.utils.SoundUtils;

public class LoginActivity extends BaseActivity implements View.OnClickListener{

    EditText account,password;
    Button submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        account=findViewById(R.id.et_account);//账号
        password= findViewById(R.id.et_password);//密码
        submit=findViewById(R.id.btn_submit);//提交
        submit.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_submit:  //上架
                SoundUtils.playSound(R.raw.login_error);//提示声音
                break;
        }

    }
}