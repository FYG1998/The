package com.example.demo.mfragment;

import android.os.Bundle;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.demo.R;
import com.example.demo.tencent_tbs.X5WebView;
import com.tencent.smtt.sdk.WebChromeClient;


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