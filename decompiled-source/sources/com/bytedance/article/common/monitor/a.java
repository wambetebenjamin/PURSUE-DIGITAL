package com.bytedance.article.common.monitor;

import com.bytedance.apm.c;
import org.json.JSONObject;
/* compiled from: ImageMonitor.java */
@Deprecated
/* loaded from: classes.dex */
public class a {
    private a() {
    }

    public static a getInstance() {
        return C0047a.f3613a;
    }

    /* compiled from: ImageMonitor.java */
    /* renamed from: com.bytedance.article.common.monitor.a$a  reason: collision with other inner class name */
    /* loaded from: classes.dex */
    private static class C0047a {

        /* renamed from: a  reason: collision with root package name */
        private static final a f3613a = new a();

        private C0047a() {
        }
    }

    public void monitorImageSample(String str, int i2, String str2, long j2, JSONObject jSONObject) {
        if (c.isDebugMode()) {
            throw new RuntimeException("ImageMonitor has not worked, please use ByteFresco or ByteGlide");
        }
    }
}
