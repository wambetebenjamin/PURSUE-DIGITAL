package com.oasis.webview;

import android.animation.ValueAnimator;
import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.core.net.MailTo;
import com.google.common.net.HttpHeaders;
import com.just.agentweb.DefaultWebClient;
import com.oasis.Logger.Logger;
import com.oasis.android.ActivityManager;
import com.oasis.android.WebViewAgent;
import java.util.HashMap;
/* loaded from: classes9.dex */
public class WebViewActivity extends Activity {
    private static final String TAG = "WebViewActivity";
    private ValueAnimator fadeoutAnimator;
    private ValueAnimator progressAnimator;
    private ProgressBar progressBar;
    private WebView webView;

    public void onClickBtnBack(View view) {
        this.webView.goBack();
    }

    public void onClickBtnForward(View view) {
        this.webView.goForward();
    }

    public void onClickBtnRefresh(View view) {
        this.webView.reload();
    }

    public void onClickBtnClose(View view) {
        finish();
    }

    @Override // android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        String stringExtra = getIntent().getStringExtra("orientation");
        if (stringExtra != null) {
            stringExtra.hashCode();
            char c2 = 65535;
            switch (stringExtra.hashCode()) {
                case -675508834:
                    if (stringExtra.equals("reverse_landscape")) {
                        c2 = 0;
                        break;
                    }
                    break;
                case -31410088:
                    if (stringExtra.equals("reverse_portrait")) {
                        c2 = 1;
                        break;
                    }
                    break;
                case 729267099:
                    if (stringExtra.equals("portrait")) {
                        c2 = 2;
                        break;
                    }
                    break;
                case 1430647483:
                    if (stringExtra.equals("landscape")) {
                        c2 = 3;
                        break;
                    }
                    break;
            }
            switch (c2) {
                case 0:
                    setRequestedOrientation(8);
                    break;
                case 1:
                    setRequestedOrientation(9);
                    break;
                case 2:
                    setRequestedOrientation(1);
                    break;
                case 3:
                    setRequestedOrientation(0);
                    break;
            }
        }
        setContentView(R.layout.oasis_web_view_layout);
        final ImageView imageView = (ImageView) findViewById(R.id.btn_back);
        final ImageView imageView2 = (ImageView) findViewById(R.id.btn_forward);
        final TextView textView = (TextView) findViewById(R.id.tv_title);
        this.webView = (WebView) findViewById(R.id.web_view);
        this.progressBar = (ProgressBar) findViewById(R.id.progress_bar);
        final String stringExtra2 = getIntent().getStringExtra("title");
        if (stringExtra2 != null && stringExtra2.length() != 0) {
            textView.setText(stringExtra2);
        }
        WebSettings settings = this.webView.getSettings();
        settings.setCacheMode(2);
        settings.setDomStorageEnabled(true);
        settings.setJavaScriptEnabled(true);
        this.webView.setWebViewClient(new WebViewClient() { // from class: com.oasis.webview.WebViewActivity.1
            @Override // android.webkit.WebViewClient
            public boolean shouldOverrideUrlLoading(WebView webView, String str) {
                Logger.d(WebViewActivity.TAG, "shouldOverrideUrlLoading() called with: view = [" + webView + "], url = [" + str + "]");
                if (WebViewAgentImpl.listener != null) {
                    WebViewAgentImpl.listener.onLoadUrl(str);
                }
                if (str.startsWith(MailTo.MAILTO_SCHEME)) {
                    WebViewAgent.startActivityToSendEmail(str);
                    return true;
                } else if (str.startsWith(DefaultWebClient.WEBCHAT_PAY_SCHEME) || str.startsWith(DefaultWebClient.ALIPAYS_SCHEME)) {
                    WebViewAgent.startActivityToPay(str);
                    return true;
                } else {
                    HashMap hashMap = new HashMap();
                    if (webView.getUrl() != null) {
                        hashMap.put(HttpHeaders.REFERER, webView.getUrl());
                    }
                    webView.loadUrl(str, hashMap);
                    return true;
                }
            }

            @Override // android.webkit.WebViewClient
            public void onPageStarted(WebView webView, String str, Bitmap bitmap) {
                super.onPageStarted(webView, str, bitmap);
                String str2 = stringExtra2;
                if (str2 == null || str2.length() == 0) {
                    WebViewActivity.updateTitle(textView, webView.getTitle());
                }
                WebViewActivity.updateIcon(textView, bitmap);
            }

            @Override // android.webkit.WebViewClient
            public void onPageFinished(WebView webView, String str) {
                super.onPageFinished(webView, str);
                String str2 = stringExtra2;
                if (str2 == null || str2.length() == 0) {
                    WebViewActivity.updateTitle(textView, webView.getTitle());
                }
                WebViewActivity.updateIcon(textView, webView.getFavicon());
                imageView.setEnabled(WebViewActivity.this.webView.canGoBack());
                imageView2.setEnabled(WebViewActivity.this.webView.canGoForward());
            }

            @Override // android.webkit.WebViewClient
            public void onReceivedError(WebView webView, WebResourceRequest webResourceRequest, WebResourceError webResourceError) {
                super.onReceivedError(webView, webResourceRequest, webResourceError);
            }
        });
        this.webView.setWebChromeClient(new WebChromeClient() { // from class: com.oasis.webview.WebViewActivity.2
            @Override // android.webkit.WebChromeClient
            public void onReceivedTitle(WebView webView, String str) {
                super.onReceivedTitle(webView, str);
                String str2 = stringExtra2;
                if (str2 == null || str2.length() == 0) {
                    WebViewActivity.updateTitle(textView, str);
                }
            }

            @Override // android.webkit.WebChromeClient
            public void onReceivedIcon(WebView webView, Bitmap bitmap) {
                super.onReceivedIcon(webView, bitmap);
                WebViewActivity.updateIcon(textView, bitmap);
            }

            @Override // android.webkit.WebChromeClient
            public void onProgressChanged(WebView webView, int i2) {
                super.onProgressChanged(webView, i2);
                if (WebViewActivity.this.progressBar == null) {
                    return;
                }
                if (WebViewActivity.this.progressAnimator != null) {
                    WebViewActivity.this.progressAnimator.cancel();
                }
                if (WebViewActivity.this.fadeoutAnimator != null) {
                    WebViewActivity.this.fadeoutAnimator.cancel();
                }
                int progress = WebViewActivity.this.progressBar.getProgress();
                if (i2 == 100 && progress != 100) {
                    WebViewActivity.this.progressAnimator = ValueAnimator.ofInt(progress, 100).setDuration(1000L);
                    WebViewActivity.this.progressAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.oasis.webview.WebViewActivity.2.1
                        @Override // android.animation.ValueAnimator.AnimatorUpdateListener
                        public void onAnimationUpdate(ValueAnimator valueAnimator) {
                            WebViewActivity.this.progressBar.setProgress(((Integer) valueAnimator.getAnimatedValue()).intValue());
                        }
                    });
                    WebViewActivity.this.progressAnimator.start();
                    WebViewActivity.this.fadeoutAnimator = ValueAnimator.ofInt(255, 0).setDuration(1000L);
                    WebViewActivity.this.fadeoutAnimator.setStartDelay(500L);
                    WebViewActivity.this.fadeoutAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.oasis.webview.WebViewActivity.2.2
                        @Override // android.animation.ValueAnimator.AnimatorUpdateListener
                        public void onAnimationUpdate(ValueAnimator valueAnimator) {
                            WebViewActivity.this.progressBar.getProgressDrawable().setAlpha(((Integer) valueAnimator.getAnimatedValue()).intValue());
                            if (((Integer) valueAnimator.getAnimatedValue()).intValue() == 0) {
                                WebViewActivity.this.progressBar.setVisibility(8);
                            }
                        }
                    });
                    WebViewActivity.this.fadeoutAnimator.start();
                } else if (i2 < 100 && WebViewActivity.this.progressBar.getVisibility() != 0) {
                    WebViewActivity.this.progressBar.setVisibility(0);
                    WebViewActivity.this.progressBar.getProgressDrawable().setAlpha(255);
                    WebViewActivity.this.progressBar.setProgress(i2);
                } else {
                    WebViewActivity.this.progressBar.setProgress(i2);
                }
            }
        });
        this.webView.loadUrl(getIntent().getStringExtra("url"));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void updateIcon(TextView textView, Bitmap bitmap) {
        textView.setCompoundDrawablesWithIntrinsicBounds(new BitmapDrawable(ActivityManager.getActivity().getApplicationContext().getResources(), bitmap), (Drawable) null, (Drawable) null, (Drawable) null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void updateTitle(TextView textView, String str) {
        if (str == null || str.length() == 0) {
            return;
        }
        textView.setText(str);
    }
}
