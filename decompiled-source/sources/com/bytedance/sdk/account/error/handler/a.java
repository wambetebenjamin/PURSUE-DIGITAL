package com.bytedance.sdk.account.error.handler;

import java.util.Map;
import org.json.JSONObject;
/* compiled from: ErrorHandler.java */
/* loaded from: classes2.dex */
public interface a {

    /* compiled from: ErrorHandler.java */
    /* renamed from: com.bytedance.sdk.account.error.handler.a$a  reason: collision with other inner class name */
    /* loaded from: classes2.dex */
    public interface InterfaceC0186a {
        void a(boolean z, boolean z2, Map<String, String> map);

        void a(boolean z, boolean z2, Map<String, String> map, com.bytedance.sdk.account.api.call.b bVar);
    }

    boolean a(int i2, Map<String, String> map, JSONObject jSONObject, InterfaceC0186a interfaceC0186a);
}
