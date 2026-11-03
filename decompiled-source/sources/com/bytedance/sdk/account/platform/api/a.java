package com.bytedance.sdk.account.platform.api;

import org.json.JSONObject;
/* compiled from: IAccountSettingsService.java */
/* loaded from: classes2.dex */
public interface a extends d {

    /* compiled from: IAccountSettingsService.java */
    /* renamed from: com.bytedance.sdk.account.platform.api.a$a  reason: collision with other inner class name */
    /* loaded from: classes2.dex */
    public interface InterfaceC0196a {
        JSONObject a();
    }

    JSONObject a();

    void a(InterfaceC0196a interfaceC0196a);

    JSONObject getLoginInfoConfig();

    JSONObject getOnekeyLoginConfig();

    JSONObject getThirdPartyConfig();

    void updateSettings(JSONObject jSONObject);
}
