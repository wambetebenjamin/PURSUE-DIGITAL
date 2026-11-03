package com.bytedance.pipo.payment.common.lib.defaultimpl;

import android.content.Context;
import com.bytedance.pipo.payment.common.lib.interfaze.b;
import com.ss.android.common.applog.AppLog;
import org.json.JSONObject;
/* compiled from: DefaultEventSender.java */
/* loaded from: classes2.dex */
public class a implements b {
    @Override // com.bytedance.pipo.payment.common.lib.interfaze.b
    public void a(String str, JSONObject jSONObject) {
        AppLog.onEvent((Context) null, "event_v3", str, (String) null, 0L, 0L, jSONObject);
    }
}
