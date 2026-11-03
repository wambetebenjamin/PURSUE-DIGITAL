package com.ss.android.pushmanager.app;

import android.content.Context;
import android.os.Bundle;
import java.util.Map;
import org.json.JSONObject;
/* compiled from: MessageAppHooks.java */
/* loaded from: classes9.dex */
public class a {

    /* compiled from: MessageAppHooks.java */
    /* renamed from: com.ss.android.pushmanager.app.a$a  reason: collision with other inner class name */
    /* loaded from: classes9.dex */
    public interface InterfaceC0334a extends com.ss.android.ug.bus.a {
        void tryInit(Context context);
    }

    /* compiled from: MessageAppHooks.java */
    /* loaded from: classes9.dex */
    public interface b extends com.ss.android.ug.bus.a {
        Map<String, String> getHttpCommonParams();

        String getMessageAction();

        com.ss.android.pushmanager.b getMessageContext();

        String getMessageKeyData();

        boolean isSswoActivityisFinish();

        boolean needControlFlares();

        void onEvent(Context context, String str, String str2, String str3, long j2, long j3, JSONObject jSONObject);

        void onEventV3(String str, Bundle bundle);

        void onEventV3(String str, JSONObject jSONObject);
    }

    public static InterfaceC0334a getInitHook() {
        return (InterfaceC0334a) com.ss.android.ug.bus.b.getService(InterfaceC0334a.class);
    }

    public static b getPushHook() {
        return (b) com.ss.android.ug.bus.b.getService(b.class);
    }
}
