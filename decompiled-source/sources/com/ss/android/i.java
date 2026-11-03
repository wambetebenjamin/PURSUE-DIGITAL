package com.ss.android;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
/* compiled from: TTResponse.java */
/* loaded from: classes9.dex */
public final class i {

    /* renamed from: a  reason: collision with root package name */
    private final String f18302a;

    /* renamed from: b  reason: collision with root package name */
    private final int f18303b;

    /* renamed from: c  reason: collision with root package name */
    private final List<h> f18304c;

    /* renamed from: d  reason: collision with root package name */
    private final String f18305d;

    /* renamed from: e  reason: collision with root package name */
    private Object f18306e;

    /* renamed from: f  reason: collision with root package name */
    private b f18307f;

    public i(String str, int i2, List<h> list, String str2) {
        this.f18302a = str;
        this.f18303b = i2;
        this.f18304c = Collections.unmodifiableList(new ArrayList(list));
        this.f18305d = str2;
    }

    public String getUrl() {
        return this.f18302a;
    }

    public int getStatus() {
        return this.f18303b;
    }

    public List<h> getHeaders() {
        return this.f18304c;
    }

    public List<h> headers(String str) {
        List<h> list;
        ArrayList arrayList = null;
        if (str != null && (list = this.f18304c) != null) {
            for (h hVar : list) {
                if (str.equalsIgnoreCase(hVar.getName())) {
                    if (arrayList == null) {
                        arrayList = new ArrayList();
                    }
                    arrayList.add(hVar);
                }
            }
        }
        return arrayList;
    }

    public String getBody() {
        return this.f18305d;
    }

    public boolean isSuccessful() {
        int i2 = this.f18303b;
        return i2 >= 200 && i2 < 300;
    }

    public void setExtraInfo(Object obj) {
        this.f18306e = obj;
    }

    public Object getExtraInfo() {
        return this.f18306e;
    }

    public b a() {
        return this.f18307f;
    }

    public void a(b bVar) {
        this.f18307f = bVar;
    }
}
