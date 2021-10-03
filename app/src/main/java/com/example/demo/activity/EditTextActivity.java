package com.example.demo.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.hardware.fingerprint.FingerprintManager;
import android.os.Build;
import android.os.Bundle;
import android.os.CancellationSignal;
import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

import com.example.demo.R;
import com.example.demo.base.BaseActivity;
import com.example.demo.utils.FingerUtils;
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


        //指纹登录 demo
        FingerprintManager.AuthenticationCallback mSelfCancelled = new FingerprintManager.AuthenticationCallback() {
            @Override
            public void onAuthenticationError(int errorCode, CharSequence errString) {
                //多次指纹密码验证错误后，进入此方法；并且，不可再验（短时间）
                //errorCode是失败的次数
                ToastUtil.show(mContext, "尝试次数过多，请稍后重试", 3000);
            }

            @Override
            public void onAuthenticationHelp(int helpCode, CharSequence helpString) {
                //指纹验证失败，可再验，可能手指过脏，或者移动过快等原因。
            }

            @Override
            public void onAuthenticationSucceeded(FingerprintManager.AuthenticationResult result) {
                //指纹密码验证成功
                Intent intent = new Intent(EditTextActivity.this, TestFactory.class);
                startActivity(intent);
                SoundUtils.playSound(R.raw.login_success);//提示声音
            }

            @Override
            public void onAuthenticationFailed() {
                //指纹验证失败，指纹识别失败，可再验，错误原因为：该指纹不是系统录入的指纹。
            }
        };
        CancellationSignal mCancellationSignal = new CancellationSignal();
        FingerUtils.getInstance(context).authenticate(null,mCancellationSignal,0,mSelfCancelled,null);





    }
}