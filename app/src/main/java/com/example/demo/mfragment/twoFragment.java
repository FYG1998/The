package com.example.demo.mfragment;

import android.content.Context;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.os.Bundle;
import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.os.Message;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.CompletionInfo;
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
import com.tencent.smtt.sdk.WebSettings;
import com.tencent.smtt.sdk.WebView;
import com.tencent.smtt.sdk.WebViewClient;

import static com.example.demo.activity.Browser.MSG_OPEN_TEST_URL;


public class twoFragment extends Fragment {


    String music = "https://music.heiya123.com/";
    private X5WebView mx5webViewmusic;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_two, container, false);


        mx5webViewmusic = view.findViewById(R.id.webViewBrowsermusic);
        mx5webViewmusic.loadUrl(music);
        mx5webViewmusic.getView().setOverScrollMode(View.OVER_SCROLL_ALWAYS);
        mx5webViewmusic.setWebChromeClient(new WebChromeClient());

        return view;
    }







}