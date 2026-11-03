package com.bytedance.sdk.account.bus.util;

import android.text.TextUtils;
/* compiled from: Version.java */
/* loaded from: classes2.dex */
public class b implements Comparable<b> {

    /* renamed from: a  reason: collision with root package name */
    private String f12333a;

    /* renamed from: b  reason: collision with root package name */
    private String[] f12334b;

    /* renamed from: c  reason: collision with root package name */
    private String[] f12335c;

    /* renamed from: d  reason: collision with root package name */
    private a f12336d;

    /* renamed from: e  reason: collision with root package name */
    private int f12337e;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: Version.java */
    /* loaded from: classes2.dex */
    public enum a {
        STABLE,
        RC,
        ALPHA
    }

    public b(String str) {
        this(str, 0);
    }

    public b(String str, int i2) {
        this.f12334b = null;
        this.f12335c = null;
        this.f12336d = a.STABLE;
        this.f12337e = i2;
        this.f12333a = str;
        if (TextUtils.isEmpty(str)) {
            return;
        }
        String[] split = this.f12333a.split("-");
        this.f12334b = split[0].split("\\.");
        if (split.length > 1) {
            String[] split2 = split[1].split("\\.");
            this.f12335c = split2;
            if ("rc".equalsIgnoreCase(split2[0])) {
                this.f12336d = a.RC;
            } else if ("alpha".equalsIgnoreCase(this.f12335c[0])) {
                this.f12336d = a.ALPHA;
            }
        }
    }

    @Override // java.lang.Comparable
    /* renamed from: a */
    public int compareTo(b bVar) {
        int i2 = this.f12337e - bVar.f12337e;
        if (i2 != 0) {
            return i2;
        }
        if (TextUtils.isEmpty(this.f12333a) && TextUtils.isEmpty(bVar.f12333a)) {
            return 0;
        }
        if (TextUtils.isEmpty(bVar.f12333a)) {
            return 1;
        }
        if (TextUtils.isEmpty(this.f12333a)) {
            return -1;
        }
        if (this.f12333a.equals(bVar.f12333a)) {
            return 0;
        }
        int min = Math.min(this.f12334b.length, bVar.f12334b.length);
        for (int i3 = 0; i3 < min; i3++) {
            int parseInt = Integer.parseInt(this.f12334b[i3]) - Integer.parseInt(bVar.f12334b[i3]);
            if (parseInt != 0) {
                return parseInt;
            }
        }
        if (this.f12334b.length > min) {
            return 1;
        }
        if (bVar.f12334b.length > min) {
            return -1;
        }
        if (this.f12336d == a.STABLE && bVar.f12336d == a.STABLE) {
            return 0;
        }
        if ((this.f12336d == a.RC && bVar.f12336d == a.RC) || (this.f12336d == a.ALPHA && bVar.f12336d == a.ALPHA)) {
            return Integer.parseInt(this.f12335c[1]) - Integer.parseInt(bVar.f12335c[1]);
        }
        if (this.f12336d == a.STABLE) {
            return 1;
        }
        return (bVar.f12336d != a.STABLE && this.f12336d == a.RC) ? 1 : -1;
    }
}
