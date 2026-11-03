package com.bytedance.sdk.open.tiktok.authorize.ui;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.net.http.SslError;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.webkit.SslErrorHandler;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import com.bytedance.sdk.open.tiktok.R;
import com.bytedance.sdk.open.tiktok.authorize.WebViewHelper;
import com.bytedance.sdk.open.tiktok.authorize.model.Authorization;
import com.bytedance.sdk.open.tiktok.common.constants.ParamKeyConstants;
import com.bytedance.sdk.open.tiktok.common.handler.IApiEventHandler;
import com.bytedance.sdk.open.tiktok.common.model.BaseReq;
import com.bytedance.sdk.open.tiktok.common.model.BaseResp;
import com.bytedance.sdk.open.tiktok.utils.AppUtil;
import com.bytedance.sdk.open.tiktok.utils.OpenUtils;
import com.ss.android.socialbase.downloader.utils.DownloadExpSwitchCode;
/* loaded from: classes3.dex */
public abstract class BaseWebAuthorizeActivity extends Activity implements IApiEventHandler {
    private static final int MSG_LOADING_TIME_OUT = 100;
    private static final String RES_ID = "id";
    private static final String RES_LAYOUT = "layout";
    private static final String RES_STRING = "string";
    protected static final String WAP_AUTHORIZE_URL = "wap_authorize_url";
    protected Authorization.Request mAuthRequest;
    protected AlertDialog mBaseErrorDialog;
    protected ImageView mCancelImg;
    protected RelativeLayout mContainer;
    protected WebView mContentWebView;
    private Context mContext;
    protected boolean mHasExecutingRequest;
    protected RelativeLayout mHeaderView;
    private int mLastErrorCode;
    protected FrameLayout mLoadingLayout;
    int OP_ERROR_NO_CONNECTION = -12;
    int OP_ERROR_CONNECT_TIMEOUT = -13;
    int OP_ERROR_NETWORK_ERROR = -15;
    protected boolean mStatusDestroyed = false;
    protected boolean isShowNetworkError = false;

    protected abstract String errorCode2Message(int i2);

    protected abstract String getAuthPath();

    protected abstract String getDomain();

    protected abstract String getHost();

    protected abstract boolean handleIntent(Intent intent, IApiEventHandler iApiEventHandler);

    protected void initActions() {
    }

    protected abstract boolean isNetworkAvailable();

    @Override // com.bytedance.sdk.open.tiktok.common.handler.IApiEventHandler
    public void onErrorIntent(Intent intent) {
    }

    @Override // com.bytedance.sdk.open.tiktok.common.handler.IApiEventHandler
    public void onResp(BaseResp baseResp) {
    }

    protected abstract void sendInnerResponse(Authorization.Request request, BaseResp baseResp);

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.mContext = this;
        handleIntent(getIntent(), this);
        setContentView(R.layout.layout_open_web_authorize);
        initView();
        initActions();
        handleRequestIntent();
    }

    @Override // com.bytedance.sdk.open.tiktok.common.handler.IApiEventHandler
    public void onReq(BaseReq baseReq) {
        if (baseReq instanceof Authorization.Request) {
            Authorization.Request request = (Authorization.Request) baseReq;
            this.mAuthRequest = request;
            request.redirectUri = "https://" + getDomain() + ParamKeyConstants.REDIRECT_URL_PATH;
            setRequestedOrientation(-1);
        }
    }

    @Override // android.app.Activity
    protected void onResume() {
        super.onResume();
    }

    @Override // android.app.Activity
    public void onBackPressed() {
        redirectToClientApp("", -2);
    }

    public final void handleRequestIntent() {
        Authorization.Request request = this.mAuthRequest;
        if (request == null) {
            finish();
        } else if (!isNetworkAvailable()) {
            this.isShowNetworkError = true;
            showNetworkErrorDialog(this.OP_ERROR_NO_CONNECTION);
        } else {
            startLoading();
            configWebView();
            this.mContentWebView.loadUrl(WebViewHelper.getLoadUrl(this, request, getHost(), getAuthPath()));
        }
    }

    @Override // android.app.Activity
    protected void onPause() {
        super.onPause();
        AlertDialog alertDialog = this.mBaseErrorDialog;
        if (alertDialog == null || !alertDialog.isShowing()) {
            return;
        }
        this.mBaseErrorDialog.dismiss();
    }

    protected void configWebView() {
        this.mContentWebView.setWebViewClient(new AuthWebViewClient());
    }

    private void redirectToClientApp(String str, int i2) {
        redirectToClientApp(str, null, i2);
    }

    private void redirectToClientApp(String str, String str2, int i2) {
        Authorization.Response response = new Authorization.Response();
        response.authCode = str;
        response.errorCode = i2;
        response.state = str2;
        sendInnerResponse(this.mAuthRequest, response);
        finish();
    }

    private void redirectToClientApp(String str, String str2, String str3, int i2) {
        Authorization.Response response = new Authorization.Response();
        response.authCode = str;
        response.errorCode = i2;
        response.state = str2;
        response.grantedPermissions = str3;
        sendInnerResponse(this.mAuthRequest, response);
        finish();
    }

    public boolean sendInnerResponse(String str, Authorization.Request request, BaseResp baseResp) {
        if (baseResp == null || this.mContext == null || !baseResp.checkArgs()) {
            return false;
        }
        Bundle bundle = new Bundle();
        baseResp.toBundle(bundle);
        String packageName = this.mContext.getPackageName();
        String buildComponentClassName = TextUtils.isEmpty(request.callerLocalEntry) ? AppUtil.buildComponentClassName(packageName, str) : request.callerLocalEntry;
        Intent intent = new Intent();
        intent.setComponent(new ComponentName(packageName, buildComponentClassName));
        intent.putExtras(bundle);
        intent.addFlags(DownloadExpSwitchCode.BACK_BUGFIX_SIGBUS);
        intent.addFlags(536870912);
        try {
            this.mContext.startActivity(intent);
            return true;
        } catch (Exception unused) {
            return false;
        }
    }

    private void initView() {
        this.mContainer = (RelativeLayout) findViewById(R.id.open_rl_container);
        this.mHeaderView = (RelativeLayout) findViewById(R.id.open_header_view);
        ImageView imageView = (ImageView) findViewById(R.id.cancel);
        this.mCancelImg = imageView;
        imageView.setOnClickListener(new View.OnClickListener() { // from class: com.bytedance.sdk.open.tiktok.authorize.ui.BaseWebAuthorizeActivity.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                BaseWebAuthorizeActivity.this.onCancel(-2);
            }
        });
        setContainerViewBgColor();
        FrameLayout frameLayout = (FrameLayout) findViewById(R.id.open_loading_group);
        this.mLoadingLayout = frameLayout;
        View loadingView = getLoadingView(frameLayout);
        if (loadingView != null) {
            this.mLoadingLayout.removeAllViews();
            this.mLoadingLayout.addView(loadingView);
        }
        initWebView(this);
        if (this.mContentWebView.getParent() != null) {
            ((ViewGroup) this.mContentWebView.getParent()).removeView(this.mContentWebView);
        }
        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) this.mContentWebView.getLayoutParams();
        layoutParams.addRule(3, R.id.open_header_view);
        this.mContentWebView.setLayoutParams(layoutParams);
        this.mContentWebView.setVisibility(4);
        this.mContainer.addView(this.mContentWebView);
    }

    public void initWebView(Context context) {
        this.mContentWebView = new WebView(context);
        this.mContentWebView.setLayoutParams(new RelativeLayout.LayoutParams(-1, -1));
        WebSettings settings = this.mContentWebView.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setDomStorageEnabled(true);
        settings.setCacheMode(-1);
    }

    /* loaded from: classes3.dex */
    public class AuthWebViewClient extends WebViewClient {
        public AuthWebViewClient() {
        }

        @Override // android.webkit.WebViewClient
        public boolean shouldOverrideUrlLoading(WebView webView, String str) {
            if (BaseWebAuthorizeActivity.this.isNetworkAvailable()) {
                if (BaseWebAuthorizeActivity.this.handleRedirect(str)) {
                    return true;
                }
                BaseWebAuthorizeActivity.this.mContentWebView.loadUrl(str);
            } else {
                BaseWebAuthorizeActivity baseWebAuthorizeActivity = BaseWebAuthorizeActivity.this;
                baseWebAuthorizeActivity.showNetworkErrorDialog(baseWebAuthorizeActivity.OP_ERROR_NO_CONNECTION);
            }
            return true;
        }

        @Override // android.webkit.WebViewClient
        public void onPageFinished(WebView webView, String str) {
            BaseWebAuthorizeActivity.this.mHasExecutingRequest = false;
            if (BaseWebAuthorizeActivity.this.mContentWebView == null || BaseWebAuthorizeActivity.this.mContentWebView.getProgress() != 100) {
                return;
            }
            BaseWebAuthorizeActivity.this.stopLoading();
            if (BaseWebAuthorizeActivity.this.mLastErrorCode != 0 || BaseWebAuthorizeActivity.this.isShowNetworkError) {
                return;
            }
            OpenUtils.setViewVisibility(BaseWebAuthorizeActivity.this.mContentWebView, 0);
        }

        @Override // android.webkit.WebViewClient
        public void onPageStarted(WebView webView, String str, Bitmap bitmap) {
            if (BaseWebAuthorizeActivity.this.mHasExecutingRequest) {
                return;
            }
            BaseWebAuthorizeActivity.this.mLastErrorCode = 0;
            BaseWebAuthorizeActivity.this.mHasExecutingRequest = true;
            BaseWebAuthorizeActivity.this.startLoading();
        }

        @Override // android.webkit.WebViewClient
        public void onReceivedError(WebView webView, int i2, String str, String str2) {
            BaseWebAuthorizeActivity.this.mLastErrorCode = i2;
            BaseWebAuthorizeActivity baseWebAuthorizeActivity = BaseWebAuthorizeActivity.this;
            baseWebAuthorizeActivity.showNetworkErrorDialog(baseWebAuthorizeActivity.OP_ERROR_NETWORK_ERROR);
            BaseWebAuthorizeActivity.this.isShowNetworkError = true;
        }

        @Override // android.webkit.WebViewClient
        public void onReceivedSslError(WebView webView, SslErrorHandler sslErrorHandler, SslError sslError) {
            BaseWebAuthorizeActivity.this.showSslErrorDialog(sslErrorHandler, sslError);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean handleRedirect(String str) {
        Authorization.Request request;
        if (TextUtils.isEmpty(str) || (request = this.mAuthRequest) == null || request.redirectUri == null || !str.startsWith(request.redirectUri)) {
            return false;
        }
        Uri parse = Uri.parse(str);
        String queryParameter = parse.getQueryParameter("code");
        String queryParameter2 = parse.getQueryParameter("state");
        String queryParameter3 = parse.getQueryParameter(ParamKeyConstants.WebViewConstants.REDIRECT_QUERY_SCOPE);
        if (TextUtils.isEmpty(queryParameter)) {
            String queryParameter4 = parse.getQueryParameter("errCode");
            int i2 = -1;
            if (!TextUtils.isEmpty(queryParameter4)) {
                try {
                    i2 = Integer.parseInt(queryParameter4);
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            }
            redirectToClientApp("", i2);
            return false;
        }
        redirectToClientApp(queryParameter, queryParameter2, queryParameter3, 0);
        return true;
    }

    protected void setContainerViewBgColor() {
        RelativeLayout relativeLayout = this.mContainer;
        if (relativeLayout != null) {
            relativeLayout.setBackgroundColor(Color.parseColor("#ffffff"));
        }
    }

    @Override // android.app.Activity
    protected void onDestroy() {
        super.onDestroy();
        this.mStatusDestroyed = true;
        WebView webView = this.mContentWebView;
        if (webView != null) {
            ViewParent parent = webView.getParent();
            if (parent != null) {
                ((ViewGroup) parent).removeView(this.mContentWebView);
            }
            this.mContentWebView.stopLoading();
            this.mContentWebView.setWebViewClient(null);
        }
    }

    protected void startLoading() {
        OpenUtils.setViewVisibility(this.mLoadingLayout, 0);
    }

    protected void stopLoading() {
        OpenUtils.setViewVisibility(this.mLoadingLayout, 8);
    }

    protected void onCancel(int i2) {
        redirectToClientApp("", i2);
    }

    @Override // android.app.Activity
    public boolean isDestroyed() {
        if (Build.VERSION.SDK_INT < 17) {
            return this.mStatusDestroyed;
        }
        try {
            return super.isDestroyed();
        } catch (Throwable unused) {
            return this.mStatusDestroyed;
        }
    }

    protected View getLoadingView(ViewGroup viewGroup) {
        return LayoutInflater.from(this).inflate(R.layout.layout_open_loading_view, viewGroup, false);
    }

    protected void showSslErrorDialog(final SslErrorHandler sslErrorHandler, SslError sslError) {
        try {
            AlertDialog create = new AlertDialog.Builder(this.mContext).create();
            String string = this.mContext.getString(R.string.aweme_open_ssl_error);
            int primaryError = sslError.getPrimaryError();
            if (primaryError == 0) {
                string = this.mContext.getString(R.string.aweme_open_ssl_notyetvalid);
            } else if (primaryError == 1) {
                string = this.mContext.getString(R.string.aweme_open_ssl_expired);
            } else if (primaryError == 2) {
                string = this.mContext.getString(R.string.aweme_open_ssl_mismatched);
            } else if (primaryError == 3) {
                string = this.mContext.getString(R.string.aweme_open_ssl_untrusted);
            }
            create.setTitle(R.string.aweme_open_ssl_warning);
            create.setTitle(string + this.mContext.getString(R.string.aweme_open_ssl_continue));
            create.setButton(-1, this.mContext.getString(R.string.aweme_open_ssl_ok), new DialogInterface.OnClickListener() { // from class: com.bytedance.sdk.open.tiktok.authorize.ui.BaseWebAuthorizeActivity.2
                @Override // android.content.DialogInterface.OnClickListener
                public void onClick(DialogInterface dialogInterface, int i2) {
                    BaseWebAuthorizeActivity.this.cancelLoad(sslErrorHandler);
                }
            });
            create.setButton(-2, this.mContext.getString(R.string.aweme_open_ssl_cancel), new DialogInterface.OnClickListener() { // from class: com.bytedance.sdk.open.tiktok.authorize.ui.BaseWebAuthorizeActivity.3
                @Override // android.content.DialogInterface.OnClickListener
                public void onClick(DialogInterface dialogInterface, int i2) {
                    BaseWebAuthorizeActivity.this.cancelLoad(sslErrorHandler);
                }
            });
            create.setCanceledOnTouchOutside(false);
            create.show();
        } catch (Exception unused) {
            cancelLoad(sslErrorHandler);
        }
    }

    protected void cancelLoad(SslErrorHandler sslErrorHandler) {
        if (sslErrorHandler != null) {
            sslErrorHandler.cancel();
        }
        showNetworkErrorDialog(this.OP_ERROR_NETWORK_ERROR);
        this.isShowNetworkError = true;
    }

    protected void showNetworkErrorDialog(final int i2) {
        AlertDialog alertDialog = this.mBaseErrorDialog;
        if (alertDialog == null || !alertDialog.isShowing()) {
            if (this.mBaseErrorDialog == null) {
                View inflate = LayoutInflater.from(this).inflate(R.layout.layout_open_network_error_dialog, (ViewGroup) null, false);
                inflate.findViewById(R.id.tv_confirm).setOnClickListener(new View.OnClickListener() { // from class: com.bytedance.sdk.open.tiktok.authorize.ui.BaseWebAuthorizeActivity.4
                    @Override // android.view.View.OnClickListener
                    public void onClick(View view) {
                        BaseWebAuthorizeActivity.this.onCancel(i2);
                    }
                });
                this.mBaseErrorDialog = new AlertDialog.Builder(new ContextThemeWrapper(this, 16973931)).setView(inflate).setCancelable(false).create();
            }
            this.mBaseErrorDialog.show();
        }
    }
}
