package com.example.demo.tools;


import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;

//自定义的TextNotice /*com.example.demo.TextNotice*/ 实现textview跑马灯效果
//参考博客：  https://blog.csdn.net/qq_27061049/article/details/86501026
//xml 引用 即可   <com.example.demo.tools.TextNotice

@SuppressLint("AppCompatCustomView")
public class TextNotice extends TextView {
    public TextNotice(Context context) {
        super(context);
    }

    public TextNotice(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        // TODO Auto-generated constructor stub
    }

    public TextNotice(Context context, AttributeSet attrs) {
        super(context, attrs);
        // TODO Auto-generated constructor stub
    }

    @Override
    public boolean isFocused() {
        // TODO Auto-generated method stub
        return true;
    }

    @Override//去掉焦点
    public void onWindowFocusChanged(boolean hasWindowFocus) {
        if(hasWindowFocus) {
            super.onWindowFocusChanged(hasWindowFocus);
        }
    }




//  XML 的引用
/*     <com.example.demo.tools.TextNotice
    android:layout_marginLeft="10dp"
    android:layout_marginRight="10dp"
    android:id="@+id/text_id"
    android:layout_width="wrap_content"
    android:layout_height="wrap_content"
    android:textColor="#fff544b4"
    android:singleLine="true"
    android:ellipsize="marquee"
    android:focusable="true"
    android:focusableInTouchMode="true"
    android:marqueeRepeatLimit="marquee_forever" />*/
}
