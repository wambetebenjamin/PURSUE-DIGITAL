package com.bytedance.push.statistics;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: IConfigProvider.java */
/* loaded from: classes2.dex */
public interface b {
    long getBackgroundInterval();

    long getDefaultBackgroundInterval();

    long getDefaultForegroundInterval();

    long getForegroundInterval();

    boolean isAppForeground();

    boolean isMainProcess();

    boolean isScreenOn();

    boolean isUsbCharging();

    void save(a aVar, boolean z, boolean z2);

    void submitAndUpload(boolean z);

    void submitAndUploadSync(boolean z);
}
