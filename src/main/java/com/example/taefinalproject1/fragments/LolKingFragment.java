package com.example.taefinalproject1.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.taefinalproject1.R;

/**
 * Created by TAE_user2 on 27/01/2016.
 */
public class LolKingFragment extends Fragment {
    WebView myWebView;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = (View) inflater.inflate(R.layout.probuild_fragment, container, false);
        myWebView = (WebView) v.findViewById(R.id.probuilds_webview);
        WebSettings webSettings = myWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        myWebView.loadUrl(getArguments().getString("url"));
        myWebView.setWebViewClient(new WebViewClient());
        return v;
    }
}
