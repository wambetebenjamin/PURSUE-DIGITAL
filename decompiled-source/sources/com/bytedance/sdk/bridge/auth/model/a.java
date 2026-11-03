package com.bytedance.sdk.bridge.auth.model;

import java.util.List;
/* compiled from: AuthRule.java */
/* loaded from: classes2.dex */
public class a {

    /* renamed from: a  reason: collision with root package name */
    private String f13107a;

    /* renamed from: b  reason: collision with root package name */
    private String f13108b = "public";

    /* renamed from: c  reason: collision with root package name */
    private List<String> f13109c = null;

    /* renamed from: d  reason: collision with root package name */
    private List<String> f13110d = null;

    public String getPattern() {
        return this.f13107a;
    }

    public void setPattern(String str) {
        this.f13107a = str;
    }

    public String getGroup() {
        return this.f13108b;
    }

    public void setGroup(String str) {
        this.f13108b = str;
    }

    public List<String> getIncludedMethods() {
        return this.f13109c;
    }

    public void setIncludedMethods(List<String> list) {
        this.f13109c = list;
    }

    public List<String> getExcludedMethods() {
        return this.f13110d;
    }

    public void setExcludedMethods(List<String> list) {
        this.f13110d = list;
    }
}
