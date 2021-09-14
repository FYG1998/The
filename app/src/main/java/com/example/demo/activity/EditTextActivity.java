package com.example.demo.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

import com.example.demo.R;
import com.example.demo.base.BaseActivity;
import com.example.demo.utils.SoundUtils;
import com.example.demo.utils.ToastUtil;

public class EditTextActivity extends BaseActivity {

    private Context context;
    private String title = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edittext);
        context = this;

        final Intent intent = getIntent();
        if (intent != null) {
            title = intent.getStringExtra("title");
            setTitle(title);
        }
        final EditText etScan = findViewById(R.id.et_scan_data);
        etScan.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                boolean flag = false;
                try {
                    //按下键盘或软键盘回车键
                    if (keyEvent != null && keyEvent.getKeyCode() == keyEvent.KEYCODE_ENTER) {
                        flag = true;
                    }
                    if (i == EditorInfo.IME_ACTION_DONE) {
                        flag = true;
                    }

                    //开始处理
                    if (flag) {
                        String str = etScan.getText().toString().replace("\n", "");
                        String password = "123123";

                        if (!str.isEmpty() && str.equals(password)) {
                            finish();

                            Intent intent = new Intent(EditTextActivity.this, TestFactory.class);
                            startActivity(intent);
                            SoundUtils.playSound(R.raw.login_success);//提示声音
                        }

                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                    etScan.requestFocus();
                } finally {
                }
                return flag;
            }
        });


    }
}