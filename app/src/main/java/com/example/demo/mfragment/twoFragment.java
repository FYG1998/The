package com.example.demo.mfragment;

import android.content.Context;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import com.example.demo.R;
import com.example.demo.activity.VideoActivity;
import com.example.demo.activity.VideoPlayActivity;
import com.example.demo.tencent_tbs.X5WebView;
import com.tencent.smtt.sdk.TbsVideo;
import com.tencent.smtt.sdk.WebChromeClient;


public class twoFragment extends Fragment {

    private EditText mUrl;
    private Button mGo;
    private String api ="https://vip.bljiex.com/?v=";
    private TextView textView;
    private X5WebView x5webView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_two, container, false);

        mUrl =	view.findViewById(R.id.editUrl1);
        mGo = view.findViewById(R.id.btnGo1);
        textView = view.findViewById(R.id.showtext);
        x5webView = view.findViewById(R.id.webView1);

        initBtnListenser();

        return view;
    }


    private void initBtnListenser()
    {
        mGo.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                String url = api + mUrl.getText().toString();

                //intent 传值 https://blog.csdn.net/qq_36721053/article/details/53637667
                Intent intent =new Intent(getActivity(), VideoActivity.class); //启动
                intent.putExtra("url", url);
                startActivity(intent);

            }
        });



        //setOnFocusChangeListener  焦点事件
        mUrl.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    mGo.setVisibility(View.VISIBLE);


                } else {
                    mGo.setVisibility(View.GONE);

                    InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                }
            }

        });



        //文本变化监听器addTextChangedListener中TextWatcher方法三个方法意义
        mUrl.addTextChangedListener(new TextWatcher() {

            @Override
            public void afterTextChanged(Editable s) {
                // TODO Auto-generated method stub
                String url = null;

                if (mUrl.getText() != null)
                {
                    url = mUrl.getText().toString();
                }

                if (url == null || mUrl.getText().toString().equalsIgnoreCase(""))
                //equalsIgnoreCase() 方法用于将字符串与指定的对象比较，不考虑大小写。如果给定对象与字符串相等，则返回 true；否则返回 false。
                {
                    mGo.setText("请输入网址");
                    mGo.setTextColor(0X6F0F0F0F);
                }
                else
                {
                    mGo.setText("进入");
                    mGo.setTextColor(0X6F0000CD);
                }
            }

            @Override
            public void beforeTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
                // TODO Auto-generated method stub

            }

            @Override
            public void onTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
                // TODO Auto-generated method stub

            }
        });
        

    }



}