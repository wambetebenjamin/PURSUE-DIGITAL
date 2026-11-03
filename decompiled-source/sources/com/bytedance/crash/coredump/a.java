package com.bytedance.crash.coredump;

import com.bytedance.crash.config.i;
import org.json.JSONArray;
/* compiled from: CoreDumpConfig.java */
/* loaded from: classes2.dex */
public class a {

    /* renamed from: a  reason: collision with root package name */
    private boolean f6394a;

    /* renamed from: b  reason: collision with root package name */
    private JSONArray f6395b;

    /* renamed from: c  reason: collision with root package name */
    private boolean f6396c;

    /* renamed from: d  reason: collision with root package name */
    private boolean f6397d;

    /* renamed from: e  reason: collision with root package name */
    private String f6398e;

    public a(JSONArray jSONArray, boolean z, boolean z2, boolean z3) {
        this.f6398e = null;
        this.f6394a = z && z2;
        if (jSONArray == null || jSONArray.length() >= 10) {
            this.f6394a = false;
        }
        if (this.f6394a) {
            StringBuffer stringBuffer = new StringBuffer("");
            boolean equals = "1".equals(jSONArray.optString(0));
            for (int i2 = 0; i2 < jSONArray.length(); i2++) {
                stringBuffer.append(jSONArray.optString(i2) + "#");
            }
            this.f6394a = equals;
            this.f6398e = stringBuffer.toString();
        } else if (i.j()) {
            this.f6394a = true;
            this.f6398e = "1#all#all#null#lib#SIG#0#0#7#";
        }
        this.f6396c = z3;
    }

    public String a() {
        return this.f6398e;
    }

    public boolean b() {
        return this.f6394a;
    }

    public boolean c() {
        return this.f6397d;
    }

    public JSONArray d() {
        return this.f6395b;
    }

    public String toString() {
        return "CoreDumpConfig{isEnable=" + this.f6394a + ", nativeCrashFeature=" + this.f6395b + '}';
    }
}
