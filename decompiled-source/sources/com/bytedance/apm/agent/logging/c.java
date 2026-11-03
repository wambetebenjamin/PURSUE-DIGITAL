package com.bytedance.apm.agent.logging;

import android.util.Log;
/* compiled from: AndroidAgentLog.java */
/* loaded from: classes.dex */
public class c implements a {

    /* renamed from: a  reason: collision with root package name */
    private static final String f303a = "[tt_apm]";

    /* renamed from: b  reason: collision with root package name */
    private int f304b = 3;

    @Override // com.bytedance.apm.agent.logging.a
    public void audit(String str) {
        if (this.f304b == 6) {
            Log.d("[tt_apm]", str);
        }
    }

    @Override // com.bytedance.apm.agent.logging.a
    public void debug(String str) {
        if (this.f304b >= 5) {
            Log.d("[tt_apm]", str);
        }
    }

    @Override // com.bytedance.apm.agent.logging.a
    public void verbose(String str) {
        if (this.f304b >= 4) {
            Log.v("[tt_apm]", str);
        }
    }

    @Override // com.bytedance.apm.agent.logging.a
    public void info(String str) {
        if (this.f304b >= 3) {
            Log.i("[tt_apm]", str);
        }
    }

    @Override // com.bytedance.apm.agent.logging.a
    public void warning(String str) {
        if (this.f304b >= 2) {
            Log.w("[tt_apm]", str);
        }
    }

    @Override // com.bytedance.apm.agent.logging.a
    public void error(String str) {
        if (this.f304b >= 1) {
            Log.e("[tt_apm]", str);
        }
    }

    @Override // com.bytedance.apm.agent.logging.a
    public void error(String str, Throwable th) {
        if (this.f304b >= 1) {
            Log.e("[tt_apm]", str, th);
        }
    }

    @Override // com.bytedance.apm.agent.logging.a
    public int getLevel() {
        return this.f304b;
    }

    @Override // com.bytedance.apm.agent.logging.a
    public void setLevel(int i2) {
        if (i2 <= 6 && i2 >= 1) {
            this.f304b = i2;
            return;
        }
        throw new IllegalArgumentException("Log level is not between ERROR and AUDIT");
    }
}
