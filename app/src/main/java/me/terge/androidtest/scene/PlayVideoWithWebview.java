package me.terge.androidtest.scene;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.FrameLayout;

import me.terge.androidtest.AbsActivity;
import me.terge.androidtest.R;

public class PlayVideoWithWebview extends AbsActivity {
    WebView wv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_video_with_webview);
        wv = (WebView) findViewById(R.id.wv);
        loadUrl("http://coc.5253.com/1509/m_306252940768.html");
    }



    private void loadUrl(String url){
        wv.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                wv.loadUrl(url);
                return true;
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                view.loadUrl("javascript:try{ bridge.require('bridge/channel').onNativeReady.fire();}catch(e){_nativeReady = true;}");
            }
        });
        wv.setWebChromeClient(new ChromeClientForVideoFullscreen());
        wv.loadUrl(url);

    }


    @Override
    protected void onPause() {
        super.onPause();
        wv.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        wv.destroy();
    }

    public class ChromeClientForVideoFullscreen extends WebChromeClient {

        ChromeClientForVideoFullscreen(){
            decoreView = (ViewGroup) context.getWindow().getDecorView();
        }

        private boolean isFullScreen = false;
        private View videoView = null;
        private ViewGroup decoreView = null;
        private View pageView = null;
        private CustomViewCallback mCallback = null;


        @Override
        public void onShowCustomView(View view, CustomViewCallback callback) {

            if (videoView != null) {
                callback.onCustomViewHidden();
                return;
            }

            pageView = decoreView.getChildAt(0);

            pageView.setVisibility(View.GONE);
            decoreView.setBackgroundColor(Color.BLACK);
            decoreView.addView(view, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));

            videoView = view;
            mCallback = callback;
            isFullScreen = true;

        }

        @Override
        public void onHideCustomView() {
            if (!isFullScreen) return;

            if (videoView == null || decoreView == null || pageView == null) return;
            videoView.setVisibility(View.GONE);
            pageView.setVisibility(View.VISIBLE);

            decoreView.removeView(videoView);
            mCallback.onCustomViewHidden();

            pageView = null;
            videoView = null;
            isFullScreen = false;
        }

        @Override
        public View getVideoLoadingProgressView() {
            FrameLayout frameLayout = new FrameLayout(context);
            frameLayout.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
            return frameLayout;
        }

    }
}
