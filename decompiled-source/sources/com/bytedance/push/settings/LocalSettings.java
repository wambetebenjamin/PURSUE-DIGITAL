package com.bytedance.push.settings;

import com.ss.android.pushmanager.setting.PushMultiProcessSharedProvider;
import java.util.Map;
@com.bytedance.push.settings.annotation.f(storageKey = PushMultiProcessSharedProvider.SP_CONFIG_NAME, supportMultiProcess = true)
/* loaded from: classes2.dex */
public interface LocalSettings extends ILocalSettings {
    public static final long DEFAULT_PULL_REQUEST_INTERVAL_IN_SECOND = 1800;

    /* renamed from: d  reason: collision with root package name */
    public static final int f11705d = 0;

    /* renamed from: e  reason: collision with root package name */
    public static final int f11706e = 1;

    String a();

    void a(int i2);

    void a(long j2);

    void a(String str);

    void a(Map<String, com.bytedance.push.settings.notification.a> map);

    void a(boolean z);

    long b();

    void b(int i2);

    void b(long j2);

    void b(String str);

    void b(boolean z);

    long c();

    void c(long j2);

    void c(boolean z);

    long d();

    void d(long j2);

    long e();

    void e(long j2);

    void f(long j2);

    boolean f();

    boolean g();

    String getAbVersion();

    int getAliPushType();

    String getPushChannelsJsonArray();

    String getPushDaemonMonitor();

    String getPushDaemonMonitorResult();

    String getRedBadgeBody();

    String getRedBadgeTimeParams();

    int getSceneIdV2();

    Map<String, com.bytedance.push.settings.notification.a> h();

    String i();

    boolean isAllowNetwork();

    boolean isPushNotifyEnable();

    boolean j();

    long k();

    int l();

    int m();

    long n();

    void setAbVersion(String str);

    void setAliPushType(int i2);

    void setAllowNetwork(boolean z);

    void setPushChannelsJsonArray(String str);

    void setPushDaemonMonitor(String str);

    void setPushDaemonMonitorResult(String str);

    void setPushNotifyEnable(boolean z);

    void setRedBadgeBody(String str);

    void setRedBadgeTimeParams(String str);

    void setSceneIdV2(int i2);
}
