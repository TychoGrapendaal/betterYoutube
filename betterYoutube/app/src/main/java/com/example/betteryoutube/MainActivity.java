package com.example.betteryoutube;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;

import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.appcompat.app.AppCompatActivity;

import androidx.annotation.NonNull;

import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.utils.YouTubePlayerTracker;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView;

public class MainActivity extends AppCompatActivity {

    private WebView webView;
    static YouTubePlayer youTubePlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        YouTubePlayerTracker tracker = new YouTubePlayerTracker();

        YouTubePlayerView youTubePlayerView = findViewById(R.id.youtube_player_view);
        youTubePlayerView.enableBackgroundPlayback(true);

        youTubePlayerView.addYouTubePlayerListener(new AbstractYouTubePlayerListener() {
            @Override
            public void onReady(@NonNull YouTubePlayer youTubePlayer) {
                MainActivity.youTubePlayer = youTubePlayer;
                youTubePlayer.addListener(tracker);
            }
        });




        webView = findViewById(R.id.webView);

        // Enable JavaScript in the WebView
        webView.getSettings().setJavaScriptEnabled(true);

        // Load the YouTube search results page
        webView.loadUrl("https://www.youtube.com");

        // Set WebView clients to handle page loading and JavaScript events
        webView.setWebViewClient(new WebViewClient()
        {
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);

                Log.d("WebView", "your current url when webpage loading.." + url);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                Log.d("WebView", "your current url when webpage loading.. finish" + url);
                super.onPageFinished(view, url);
            }

            @Override
            public void onLoadResource(WebView view, String url) {
                // TODO Auto-generated method stub
                super.onLoadResource(view, url);
            }
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                System.out.println("when you click on any interlink on webview that time you got url :-" + url);
                return super.shouldOverrideUrlLoading(view, url);
            }
        });

        //when the url of the webview changes it prints the new url it to the terminal

        webView.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                System.out.println(webView.getUrl());
                //get the index of watch?v= and add 11 to get video id
                if (webView.getUrl().contains("watch?v=")) {
                    int index = webView.getUrl().indexOf("watch?v=");
                    String videoId = webView.getUrl().substring(index+8, index + 19);
                    System.out.println(videoId);
                    //check the current vidoe id that the youtubeplayer is playing
                    //if it is not the same as the new video id then load the new video
                    if (tracker.getVideoId() != null &&
                            tracker.getVideoId().equals(videoId)) return;
                    youTubePlayer.loadVideo(videoId, 0);
                }
            }
        });
    }
}