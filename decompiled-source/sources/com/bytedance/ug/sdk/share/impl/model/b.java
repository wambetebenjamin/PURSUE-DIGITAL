package com.bytedance.ug.sdk.share.impl.model;

import android.text.TextUtils;
/* compiled from: ImageCheckResult.java */
/* loaded from: classes8.dex */
public class b {

    /* renamed from: a  reason: collision with root package name */
    private String f15839a;

    /* renamed from: b  reason: collision with root package name */
    private String f15840b;

    /* renamed from: c  reason: collision with root package name */
    private String f15841c;

    /* renamed from: d  reason: collision with root package name */
    private boolean f15842d;

    public b(String str, String str2, String str3, boolean z) {
        this.f15839a = str;
        this.f15840b = str2;
        this.f15841c = str3;
        this.f15842d = z;
    }

    public String getQrScan() {
        return this.f15840b;
    }

    public String getHiddenStr() {
        return this.f15841c;
    }

    public String getFilePath() {
        return this.f15839a;
    }

    public boolean isFromVideo() {
        return this.f15842d;
    }

    public String getCommand() {
        if (TextUtils.isEmpty(this.f15840b)) {
            return !TextUtils.isEmpty(this.f15841c) ? this.f15841c : "";
        }
        return this.f15840b;
    }
}
