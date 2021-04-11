package com.example.demo.activity;
import android.content.Context;
import android.os.Bundle;
import android.os.ConditionVariable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import com.example.demo.BaseActivity;
import com.example.demo.R;
import com.example.demo.utils.SPDataUtils;


public class SettingSavePage extends BaseActivity {

    private EditText editText;
    private Button button;

    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting_save_page);

        context =this;
        initView();
        initData();
    }


    private void initView() {
         editText = findViewById(R.id.edpath);
         button = findViewById(R.id.btn_save);

    }

    private void initData() {

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String uname="user";
                String upass = editText.getText().toString();
                boolean flag = SPDataUtils.saveUserInfo(context,uname,upass);

                if(flag){
                    showToast("Save OK!");
                }



            }
        });


    }


}