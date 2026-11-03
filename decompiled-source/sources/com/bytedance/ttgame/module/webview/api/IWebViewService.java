package com.bytedance.ttgame.module.webview.api;

import android.content.Context;
import android.os.Bundle;
import android.webkit.WebView;
import com.bytedance.bdturing.methods.l;
import com.bytedance.ttgame.framework.module.spi.IModuleApi;
import com.bytedance.ttgame.module.webview.api.aidl.Command;
import com.bytedance.ttgame.module.webview.api.aidl.IWebAidlResultCallback;
import java.lang.ref.WeakReference;
import java.util.Map;
import kotlin.Deprecated;
import kotlin.Metadata;
/* compiled from: IWebViewService.kt */
@Metadata(d1 = {"\u0000t\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010$\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010%\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H&J\b\u0010\u0004\u001a\u00020\u0005H&J\u0010\u0010\u0006\u001a\n\u0012\u0004\u0012\u00020\b\u0018\u00010\u0007H&J@\u0010\t\u001a\u00020\u00032\b\u0010\n\u001a\u0004\u0018\u00010\u000b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000f2\u0014\u0010\u0010\u001a\u0010\u0012\u0004\u0012\u00020\u000f\u0012\u0004\u0012\u00020\u0012\u0018\u00010\u00112\u0006\u0010\u0013\u001a\u00020\u0014H&JJ\u0010\u0015\u001a\u0014\u0012\u0006\u0012\u0004\u0018\u00010\u0012\u0012\u0006\u0012\u0004\u0018\u00010\u0012\u0018\u00010\u00162\b\u0010\n\u001a\u0004\u0018\u00010\u000b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000f2\u0014\u0010\u0010\u001a\u0010\u0012\u0004\u0012\u00020\u000f\u0012\u0004\u0012\u00020\u0012\u0018\u00010\u0011H&J\b\u0010\u0017\u001a\u00020\rH&J\u0010\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u0012H&J\u0018\u0010\u001b\u001a\u00020\u00032\u0006\u0010\u001c\u001a\u00020\r2\u0006\u0010\u001d\u001a\u00020\u001eH&J\u0012\u0010\u001f\u001a\u00020\u00032\b\u0010 \u001a\u0004\u0018\u00010\u0012H&J&\u0010!\u001a\u00020\u00032\b\u0010\n\u001a\u0004\u0018\u00010\u000b2\b\u0010\"\u001a\u0004\u0018\u00010\u000f2\b\u0010#\u001a\u0004\u0018\u00010\u000fH'J0\u0010!\u001a\u00020\u00032\b\u0010\n\u001a\u0004\u0018\u00010\u000b2\b\u0010\"\u001a\u0004\u0018\u00010\u000f2\b\u0010#\u001a\u0004\u0018\u00010\u000f2\b\u0010$\u001a\u0004\u0018\u00010%H'J8\u0010!\u001a\u00020\u00032\b\u0010\n\u001a\u0004\u0018\u00010\u000b2\b\u0010\"\u001a\u0004\u0018\u00010\u000f2\b\u0010#\u001a\u0004\u0018\u00010\u000f2\b\u0010$\u001a\u0004\u0018\u00010%2\u0006\u0010&\u001a\u00020'H&J.\u0010!\u001a\u00020\u00032\b\u0010\n\u001a\u0004\u0018\u00010\u000b2\b\u0010\"\u001a\u0004\u0018\u00010\u000f2\b\u0010#\u001a\u0004\u0018\u00010\u000f2\u0006\u0010&\u001a\u00020'H&J:\u0010(\u001a\u00020\u00032\b\u0010\n\u001a\u0004\u0018\u00010\u000b2\b\u0010\"\u001a\u0004\u0018\u00010\u000f2\b\u0010#\u001a\u0004\u0018\u00010\u000f2\b\u0010$\u001a\u0004\u0018\u00010%2\b\u0010\u0013\u001a\u0004\u0018\u00010)H'JB\u0010(\u001a\u00020\u00032\b\u0010\n\u001a\u0004\u0018\u00010\u000b2\b\u0010\"\u001a\u0004\u0018\u00010\u000f2\b\u0010#\u001a\u0004\u0018\u00010\u000f2\b\u0010$\u001a\u0004\u0018\u00010%2\b\u0010\u0013\u001a\u0004\u0018\u00010)2\u0006\u0010&\u001a\u00020'H&JL\u0010(\u001a\u00020\u00032\b\u0010\n\u001a\u0004\u0018\u00010\u000b2\b\u0010\"\u001a\u0004\u0018\u00010\u000f2\b\u0010#\u001a\u0004\u0018\u00010\u000f2\b\u0010$\u001a\u0004\u0018\u00010%2\b\u0010\u0013\u001a\u0004\u0018\u00010)2\u0006\u0010&\u001a\u00020'2\b\u0010*\u001a\u0004\u0018\u00010+H&¨\u0006,"}, d2 = {"Lcom/bytedance/ttgame/module/webview/api/IWebViewService;", "Lcom/bytedance/ttgame/framework/module/spi/IModuleApi;", "closeWebView", "", "getConcaveHeight", "", "getWeakRefWebView", "Ljava/lang/ref/WeakReference;", "Landroid/webkit/WebView;", "handleWebAsyncAction", "context", "Landroid/content/Context;", "level", "", "actionName", "", "jsonParams", "", "", l.VALUE_CALLBACK, "Lcom/bytedance/ttgame/module/webview/api/aidl/IWebAidlResultCallback;", "handleWebSyncAction", "", "isConcaveDevice", "isSafeIdentification", "", "bridgeContext", "registerCommand", "commandLevel", "command", "Lcom/bytedance/ttgame/module/webview/api/aidl/Command;", "setUserInfoContext", "bdUserInfo", "showWebView", "title", "url", "bundle", "Landroid/os/Bundle;", "errorCodeCallback", "Lcom/bytedance/ttgame/module/webview/api/IWebViewErrorCodeCallback;", "showWebViewWithCallback", "Lcom/bytedance/ttgame/module/webview/api/IExitWebViewCallback;", "customCallback", "Lcom/bytedance/ttgame/module/webview/api/IExecuteCustomCallback;", "webview_api_i18nRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes7.dex */
public interface IWebViewService extends IModuleApi {
    void closeWebView();

    float getConcaveHeight();

    WeakReference<WebView> getWeakRefWebView();

    void handleWebAsyncAction(Context context, int i2, String str, Map<String, ? extends Object> map, IWebAidlResultCallback iWebAidlResultCallback);

    Map<Object, Object> handleWebSyncAction(Context context, int i2, String str, Map<String, ? extends Object> map);

    int isConcaveDevice();

    boolean isSafeIdentification(Object obj);

    void registerCommand(int i2, Command command);

    void setUserInfoContext(Object obj);

    @Deprecated(message = "Deprecated")
    void showWebView(Context context, String str, String str2);

    @Deprecated(message = "Deprecated")
    void showWebView(Context context, String str, String str2, Bundle bundle);

    void showWebView(Context context, String str, String str2, Bundle bundle, IWebViewErrorCodeCallback iWebViewErrorCodeCallback);

    void showWebView(Context context, String str, String str2, IWebViewErrorCodeCallback iWebViewErrorCodeCallback);

    @Deprecated(message = "Deprecated")
    void showWebViewWithCallback(Context context, String str, String str2, Bundle bundle, IExitWebViewCallback iExitWebViewCallback);

    void showWebViewWithCallback(Context context, String str, String str2, Bundle bundle, IExitWebViewCallback iExitWebViewCallback, IWebViewErrorCodeCallback iWebViewErrorCodeCallback);

    void showWebViewWithCallback(Context context, String str, String str2, Bundle bundle, IExitWebViewCallback iExitWebViewCallback, IWebViewErrorCodeCallback iWebViewErrorCodeCallback, IExecuteCustomCallback iExecuteCustomCallback);
}
