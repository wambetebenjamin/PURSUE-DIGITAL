package com.bytedance.apm.entity;

import java.util.List;
import org.json.JSONObject;
/* compiled from: UploadRequest.java */
/* loaded from: classes.dex */
public class n {

    /* renamed from: a  reason: collision with root package name */
    private String f1051a;

    /* renamed from: b  reason: collision with root package name */
    private String f1052b;

    /* renamed from: c  reason: collision with root package name */
    private String f1053c;

    /* renamed from: d  reason: collision with root package name */
    private List<String> f1054d;

    /* renamed from: e  reason: collision with root package name */
    private JSONObject f1055e;

    public String getDid() {
        return this.f1051a;
    }

    public void setDid(String str) {
        this.f1051a = str;
    }

    public String getAid() {
        return this.f1052b;
    }

    public void setAid(String str) {
        this.f1052b = str;
    }

    public String getProcessName() {
        return this.f1053c;
    }

    public void setProcessName(String str) {
        this.f1053c = str;
    }

    public List<String> getAlogFiles() {
        return this.f1054d;
    }

    public void setAlogFiles(List<String> list) {
        this.f1054d = list;
    }

    public JSONObject getCommonParams() {
        return this.f1055e;
    }

    public void setCommonParams(JSONObject jSONObject) {
        this.f1055e = jSONObject;
    }
}
