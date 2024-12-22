package com.example.elearning;

import android.annotation.SuppressLint;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.VideoView;


public class FirstFragment extends Fragment {
    public FirstFragment() {
        // Required empty public constructor
    }

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_first, container, false);
        WebView webView = view.findViewById(R.id.video);
        ImageView play = view.findViewById(R.id.play);
        TextView text1 = view.findViewById(R.id.text1);
        // Load the initial YouTube video
        String videoId = "<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/RSl87lqOXDE?si=5utJzwKCvJhQWAru\" title=\"YouTube video player\" frameborder=\"0\" allow=\"accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share\" referrerpolicy=\"strict-origin-when-cross-origin\" allowfullscreen></iframe>";
        webView.loadData(videoId, "text/html", "utf-8");
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient());

        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // New video URL to load when play is clicked
                text1.setText("Video 2 : Simple select query using alies | joins");
                String newVideoId = "<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/TPivN7tpdwc?si=gwIrkHBJCj2Ig6kr\" title=\"YouTube video player\" frameborder=\"0\" allow=\"accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share\" referrerpolicy=\"strict-origin-when-cross-origin\" allowfullscreen></iframe>";
                webView.loadData(newVideoId, "text/html", "utf-8");
                webView.getSettings().setJavaScriptEnabled(true);
                webView.setWebViewClient(new WebViewClient());
            }
        });
        return view;
    }
}