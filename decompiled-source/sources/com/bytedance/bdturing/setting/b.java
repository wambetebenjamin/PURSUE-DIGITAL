package com.bytedance.bdturing.setting;

import android.os.Looper;
import kotlin.Metadata;
/* compiled from: ConfigProvider.kt */
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\n\u0010\u0002\u001a\u0004\u0018\u00010\u0003H&J\n\u0010\u0004\u001a\u0004\u0018\u00010\u0003H&J\n\u0010\u0005\u001a\u0004\u0018\u00010\u0003H&J\n\u0010\u0006\u001a\u0004\u0018\u00010\u0003H&J\b\u0010\u0007\u001a\u00020\bH&J\n\u0010\t\u001a\u0004\u0018\u00010\u0003H&J\b\u0010\n\u001a\u00020\u000bH&J\n\u0010\f\u001a\u0004\u0018\u00010\u0003H&J\n\u0010\r\u001a\u0004\u0018\u00010\u0003H&J\b\u0010\u000e\u001a\u00020\u0003H&J\n\u0010\u000f\u001a\u0004\u0018\u00010\u0003H&J\n\u0010\u0010\u001a\u0004\u0018\u00010\u0011H&J\b\u0010\u0012\u001a\u00020\u0013H&¨\u0006\u0014"}, d2 = {"Lcom/bytedance/bdturing/setting/ConfigProvider;", "", "getAppId", "", "getAppName", "getAppVersion", "getChannel", "getDefaultSettings", "Lcom/bytedance/bdturing/net/models/DefaultSettingsModel;", "getDeviceId", "getHttpClient", "Lcom/bytedance/bdturing/net/HttpClient;", "getInstallId", "getLang", "getRegion", "getSDKVersion", "getServiceInterceptor", "Lcom/bytedance/bdturing/setting/ServiceInterceptor;", "getWorkerLooper", "Landroid/os/Looper;", "setting_i18nRelease"}, k = 1, mv = {1, 5, 1}, xi = 48)
/* loaded from: classes.dex */
public interface b {
    com.bytedance.bdturing.net.models.a a();

    i b();

    com.bytedance.bdturing.net.a c();

    String getAppId();

    String getAppName();

    String getAppVersion();

    String getChannel();

    String getDeviceId();

    String getInstallId();

    String getLang();

    String getRegion();

    String getSDKVersion();

    Looper getWorkerLooper();
}
