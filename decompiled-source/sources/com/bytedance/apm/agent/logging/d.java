package com.bytedance.apm.agent.logging;

import com.meituan.robust.Constants;
/* compiled from: ConsoleAgentLog.java */
/* loaded from: classes.dex */
public class d implements a {

    /* renamed from: a  reason: collision with root package name */
    private int f305a = 3;

    @Override // com.bytedance.apm.agent.logging.a
    public void audit(String str) {
        if (this.f305a == 6) {
            a("AUDIT", str);
        }
    }

    @Override // com.bytedance.apm.agent.logging.a
    public void debug(String str) {
        if (this.f305a >= 5) {
            a("DEBUG", str);
        }
    }

    @Override // com.bytedance.apm.agent.logging.a
    public void verbose(String str) {
        if (this.f305a >= 4) {
            a("VERBOSE", str);
        }
    }

    @Override // com.bytedance.apm.agent.logging.a
    public void info(String str) {
        if (this.f305a >= 3) {
            a("INFO", str);
        }
    }

    @Override // com.bytedance.apm.agent.logging.a
    public void warning(String str) {
        if (this.f305a >= 2) {
            a("WARN", str);
        }
    }

    @Override // com.bytedance.apm.agent.logging.a
    public void error(String str, Throwable th) {
        if (this.f305a >= 1) {
            a("ERROR", str + " " + th.getMessage());
        }
    }

    @Override // com.bytedance.apm.agent.logging.a
    public void error(String str) {
        if (this.f305a >= 1) {
            a("ERROR", str);
        }
    }

    @Override // com.bytedance.apm.agent.logging.a
    public int getLevel() {
        return this.f305a;
    }

    @Override // com.bytedance.apm.agent.logging.a
    public void setLevel(int i2) {
        this.f305a = i2;
    }

    private static void a(String str, String str2) {
        System.out.println(Constants.ARRAY_TYPE + str + "] " + str2);
    }
}
