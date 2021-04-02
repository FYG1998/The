package com.example.demo.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.demo.BaseActivity;
import com.example.demo.R;
import com.r0adkll.slidr.Slidr;

public class PictureDetailActivity extends BaseActivity {


    private ImageView img_picture;
    private String picUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_picturedetail);

        Slidr.attach(this);  //右滑框架

        initData();
        initView();
    }

    private void initData() {
        picUrl = getIntent().getStringExtra("pic_url");
    }

    private void initView() {
        img_picture = findViewById(R.id.img_picture);
        if(picUrl != null) {
            Glide.with(this).load(picUrl).into(img_picture);
        }
    }


}