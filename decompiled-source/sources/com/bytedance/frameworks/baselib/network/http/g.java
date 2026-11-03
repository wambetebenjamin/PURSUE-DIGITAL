package com.bytedance.frameworks.baselib.network.http;

import android.text.TextUtils;
import java.util.Map;
/* compiled from: RequestRetryResult.java */
/* loaded from: classes2.dex */
public class g {

    /* renamed from: a  reason: collision with root package name */
    private boolean f7839a;

    /* renamed from: b  reason: collision with root package name */
    private Map<String, String> f7840b;

    /* renamed from: c  reason: collision with root package name */
    private boolean f7841c;

    /* renamed from: d  reason: collision with root package name */
    private String f7842d;

    public g(boolean z, Map<String, String> map) {
        this.f7841c = true;
        this.f7839a = z;
        this.f7840b = map;
    }

    public g(boolean z) {
        this.f7841c = true;
        this.f7839a = z;
    }

    public g(boolean z, boolean z2, Map<String, String> map) {
        this.f7841c = true;
        this.f7839a = z;
        this.f7841c = z2;
        this.f7840b = map;
    }

    public boolean a() {
        return this.f7839a;
    }

    public void a(boolean z) {
        this.f7839a = z;
    }

    public boolean c() {
        return this.f7841c;
    }

    public void b(boolean z) {
        this.f7841c = z;
    }

    public Map<String, String> b() {
        return this.f7840b;
    }

    public void a(Map<String, String> map) {
        this.f7840b = map;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void a(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        this.f7842d = str;
    }

    public String d() {
        return this.f7842d;
    }
}
