package com.bytedance.frameworks.baselib.network.utils;
/* compiled from: TTReflectException.java */
/* loaded from: classes2.dex */
public class d extends RuntimeException {

    /* renamed from: a  reason: collision with root package name */
    private final String f8524a;

    /* renamed from: b  reason: collision with root package name */
    private final String f8525b;

    /* renamed from: c  reason: collision with root package name */
    private final String f8526c;

    public d(String str, Throwable th, String... strArr) {
        super(a(str, strArr), th);
        this.f8524a = strArr.length > 0 ? strArr[0] : "unknown";
        this.f8525b = strArr.length > 1 ? strArr[1] : "unknown";
        this.f8526c = strArr.length > 2 ? strArr[2] : "unknown";
    }

    public d(String str) {
        this(str, null, new String[0]);
    }

    private static String a(String str, String... strArr) {
        StringBuilder sb = new StringBuilder(str);
        if (strArr != null && strArr.length > 0) {
            sb.append(" [TargetClass: ").append(strArr[0]);
            if (strArr.length > 1) {
                sb.append(", TargetMember: ").append(strArr[1]);
            }
            if (strArr.length > 2) {
                sb.append(", MemberType: ").append(strArr[2]);
            }
            sb.append("]");
        }
        return sb.toString();
    }

    public String a() {
        return this.f8524a;
    }

    public String b() {
        return this.f8525b;
    }

    public String c() {
        return this.f8526c;
    }
}
