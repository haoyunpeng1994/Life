package com.hyp.life.activity;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;

import com.github.jlmd.animatedcircleloadingview.AnimatedCircleLoadingView;
import com.hyp.life.R;

/**
 * Created by acer on 2015/11/21.
 */
public class WebView_Activity extends ActionBarActivity {
    private String url;
    private AnimatedCircleLoadingView loadingView;
    private WebView webview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview);
        url = getIntent().getStringExtra("url");
        initView();
    }
    private void initView()
    {
        loadingView = (AnimatedCircleLoadingView) findViewById(R.id.loading);
        loadingView.startDeterminate();
        ImageView iv_back = (ImageView) findViewById(R.id.iv_back);
        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        webview = (WebView) findViewById(R.id.webview);
        /** 支持javascript */
        webview.getSettings().setJavaScriptEnabled(true);
        /** 设置可以支持缩放 */
        webview.getSettings().setSupportZoom(true);
        /** 设置出现缩放工具 */
        webview.getSettings().setBuiltInZoomControls(true);
        /** 扩大比例的缩放 */
        webview.getSettings().setUseWideViewPort(true);
        /** 清除浏览器缓存 */
        webview.clearCache(true);
        /** 自适应屏幕 */
        webview.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);

        //WebView加载web资源
        webview.loadUrl(url);
        //覆盖WebView默认使用第三方或系统默认浏览器打开网页的行为，使网页用WebView打开
        webview.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                /** 返回值是true的时候控制去WebView打开，为false调用系统浏览器或第三方浏览器 */
                view.loadUrl(url);
                return true;
            }
        });
        /** 判断页面加载过程 */
        webview.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                if (newProgress == 100) {
                    /** 网页加载完成 */
                    loadingView.setVisibility(View.GONE);
                    webview.setVisibility(View.VISIBLE);
                } else {
                    /** 加载中 */
                    loadingView.setPercent(newProgress);
                }
            }
        });
    }
}
