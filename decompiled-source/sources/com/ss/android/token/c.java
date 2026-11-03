package com.ss.android.token;
/* compiled from: TTTokenHeader.java */
/* loaded from: classes9.dex */
public final class c {

    /* renamed from: a  reason: collision with root package name */
    private final String f18537a;

    /* renamed from: b  reason: collision with root package name */
    private final String f18538b;

    public c(String str, String str2) {
        this.f18537a = str;
        this.f18538b = str2;
    }

    public String getName() {
        return this.f18537a;
    }

    public String getValue() {
        return this.f18538b;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        c cVar = (c) obj;
        String str = this.f18537a;
        if (str == null ? cVar.f18537a == null : str.equals(cVar.f18537a)) {
            String str2 = this.f18538b;
            String str3 = cVar.f18538b;
            return str2 == null ? str3 == null : str2.equals(str3);
        }
        return false;
    }

    public int hashCode() {
        String str = this.f18537a;
        int hashCode = (str != null ? str.hashCode() : 0) * 31;
        String str2 = this.f18538b;
        return hashCode + (str2 != null ? str2.hashCode() : 0);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        String str = this.f18537a;
        if (str == null) {
            str = "";
        }
        StringBuilder append = sb.append(str).append(": ");
        String str2 = this.f18538b;
        return append.append(str2 != null ? str2 : "").toString();
    }
}
