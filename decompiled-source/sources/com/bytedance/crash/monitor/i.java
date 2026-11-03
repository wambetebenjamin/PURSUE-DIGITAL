package com.bytedance.crash.monitor;

import android.text.TextUtils;
import com.bytedance.crash.util.ae;
import org.json.JSONArray;
/* compiled from: SdkMonitor.java */
/* loaded from: classes2.dex */
public class i extends d {
    private String[] n;
    private String[] o;

    public i(String str, String str2, long j2, String[] strArr, String[] strArr2) {
        super(str, str2, j2);
        this.o = strArr;
        this.n = strArr2;
    }

    public String[] a() {
        return this.o;
    }

    public String[] b() {
        return this.n;
    }

    public void a(String[] strArr) {
        this.n = strArr;
    }

    public void b(String[] strArr) {
        this.o = strArr;
    }

    public JSONArray a(StackTraceElement[] stackTraceElementArr) {
        String[] strArr = this.o;
        if (strArr == null || stackTraceElementArr == null) {
            return null;
        }
        return ae.a(stackTraceElementArr, strArr);
    }

    public boolean f(String str) {
        if (this.n != null && !TextUtils.isEmpty(str)) {
            for (String str2 : this.n) {
                if (str.contains(str2)) {
                    return true;
                }
            }
        }
        return false;
    }
}
