package com.ss.android;
/* compiled from: TTHeader.java */
/* loaded from: classes9.dex */
public final class h {

    /* renamed from: a  reason: collision with root package name */
    private final String f18300a;

    /* renamed from: b  reason: collision with root package name */
    private final String f18301b;

    public h(String str, String str2) {
        this.f18300a = str;
        this.f18301b = str2;
    }

    public String getName() {
        return this.f18300a;
    }

    public String getValue() {
        return this.f18301b;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        h hVar = (h) obj;
        String str = this.f18300a;
        if (str == null ? hVar.f18300a == null : str.equals(hVar.f18300a)) {
            String str2 = this.f18301b;
            String str3 = hVar.f18301b;
            return str2 == null ? str3 == null : str2.equals(str3);
        }
        return false;
    }

    public int hashCode() {
        String str = this.f18300a;
        int hashCode = (str != null ? str.hashCode() : 0) * 31;
        String str2 = this.f18301b;
        return hashCode + (str2 != null ? str2.hashCode() : 0);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        String str = this.f18300a;
        if (str == null) {
            str = "";
        }
        StringBuilder append = sb.append(str).append(": ");
        String str2 = this.f18301b;
        return append.append(str2 != null ? str2 : "").toString();
    }
}
