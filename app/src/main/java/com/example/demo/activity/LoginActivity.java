package com.example.demo.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.demo.BaseActivity;
import com.example.demo.BaseActivityTwo;
import com.example.demo.R;
import com.example.demo.utils.SoundUtils;

public class LoginActivity extends BaseActivity implements View.OnClickListener{

    EditText part_Number,equipment_number;
    Button fitout_submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        part_Number=findViewById(R.id.et_part_number);//零件编号
        equipment_number= findViewById(R.id.et_equipment_number);//机台编号
        fitout_submit=findViewById(R.id.btn_fitout_submit);//装配提交
        fitout_submit.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_fitout_submit:  //上架
                SoundUtils.playSound(R.raw.login_error);;
                break;
        }

    }
}