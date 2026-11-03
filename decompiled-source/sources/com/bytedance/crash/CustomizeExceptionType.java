package com.bytedance.crash;
/* loaded from: classes2.dex */
public enum CustomizeExceptionType {
    XASAN("xasan"),
    HEAP_TRACKER("heap_tracker"),
    FD_TRACKER("fd_tracker"),
    VM_MONITOR("vm_monitor"),
    TLS_MONITOR("tls_monitor"),
    REF_MONITOR("ref_monitor"),
    PRIO_MONITOR("priority_monitor"),
    MAX("max");
    

    /* renamed from: i  reason: collision with root package name */
    private final String f6050i;

    CustomizeExceptionType(String str) {
        this.f6050i = str;
    }

    public String a() {
        return this.f6050i;
    }

    public static CustomizeExceptionType a(String str) {
        try {
            return valueOf(str.toUpperCase());
        } catch (Throwable unused) {
            return MAX;
        }
    }

    @Override // java.lang.Enum
    public String toString() {
        return a();
    }
}
