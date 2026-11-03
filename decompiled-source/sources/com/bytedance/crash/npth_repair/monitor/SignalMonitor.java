package com.bytedance.crash.npth_repair.monitor;

import android.os.Build;
import com.bytedance.crash.npth_repair.NpthRepair;
import com.bytedance.crash.npth_repair.utils.c;
import com.bytedance.crash.npth_repair.utils.d;
import org.json.JSONArray;
/* loaded from: classes2.dex */
public class SignalMonitor {

    /* renamed from: a  reason: collision with root package name */
    private static final String f6939a = "SignalMonitor: ";

    /* renamed from: b  reason: collision with root package name */
    private static SignalMonitor f6940b;

    /* renamed from: c  reason: collision with root package name */
    private c f6941c;

    /* renamed from: d  reason: collision with root package name */
    private int f6942d = 1110;

    private native int nDumpSignalMonitor(String str);

    private native int nStartSignalMonitorWithSigType(int[] iArr);

    private native void nStopSignalMonitor();

    private SignalMonitor() {
    }

    public static SignalMonitor a() {
        if (f6940b == null) {
            synchronized (SignalMonitor.class) {
                if (f6940b == null) {
                    f6940b = new SignalMonitor();
                }
            }
        }
        return f6940b;
    }

    private boolean b(c cVar) {
        return cVar == null || (cVar.a(Build.VERSION.SDK_INT) && cVar.a(Build.BRAND));
    }

    private static int[] c(c cVar) {
        int[] iArr = null;
        if (cVar == null) {
            return null;
        }
        JSONArray c2 = cVar.c("sigTypes");
        if (c2 != null && c2.length() > 0) {
            iArr = new int[c2.length()];
            for (int i2 = 0; i2 < c2.length(); i2++) {
                iArr[i2] = c2.optInt(i2);
            }
        }
        return iArr;
    }

    public synchronized int a(c cVar) {
        int i2 = this.f6942d;
        if (i2 == 1110 || i2 == 1113) {
            if (NpthRepair.a()) {
                this.f6941c = cVar;
                int i3 = -6;
                if (b(cVar)) {
                    try {
                        i3 = nStartSignalMonitorWithSigType(c(cVar));
                    } catch (Throwable unused) {
                        i3 = -4;
                    }
                }
                if (i3 == 0) {
                    this.f6942d = 1111;
                } else {
                    this.f6942d = 1112;
                }
                return i3;
            }
            return -1;
        }
        return -2;
    }

    public synchronized void b() {
        if (this.f6942d == 1111) {
            this.f6942d = 1113;
            nStopSignalMonitor();
        }
    }

    public synchronized int a(String str) {
        int i2 = this.f6942d;
        if (i2 == 1111 || i2 == 1113) {
            this.f6942d = d.m;
            return nDumpSignalMonitor(str);
        }
        return -1;
    }
}
