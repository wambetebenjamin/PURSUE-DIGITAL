package com.ss.android.deviceregister.core.cache;

import org.json.JSONArray;
/* compiled from: IDeviceRegisterParameter.java */
/* loaded from: classes9.dex */
public interface a {
    String a();

    JSONArray b();

    String[] c();

    void clear(String str);

    String d();

    String getClientUDID();

    String getDeviceId();

    String getOpenUdid(boolean z);

    void updateDeviceId(String str);
}
