package com.bytedance.crash.entity;

import java.util.List;
import org.json.JSONObject;
/* compiled from: UploadRequest.java */
@Deprecated
/* loaded from: classes2.dex */
public class g {

    /* renamed from: a  reason: collision with root package name */
    private String f6659a;

    /* renamed from: b  reason: collision with root package name */
    private JSONObject f6660b;

    /* renamed from: c  reason: collision with root package name */
    private String f6661c;

    /* renamed from: d  reason: collision with root package name */
    private boolean f6662d;

    /* renamed from: e  reason: collision with root package name */
    private String f6663e;

    /* renamed from: f  reason: collision with root package name */
    private String f6664f;

    /* renamed from: g  reason: collision with root package name */
    private String f6665g;

    /* renamed from: h  reason: collision with root package name */
    private List<String> f6666h;

    public String getUploadUrl() {
        return this.f6659a;
    }

    public void setUploadUrl(String str) {
        this.f6659a = str;
    }

    public JSONObject getUploadBody() {
        return this.f6660b;
    }

    public void setUploadBody(JSONObject jSONObject) {
        this.f6660b = jSONObject;
    }

    public String getDumpFilePath() {
        return this.f6661c;
    }

    public void setDumpFilePath(String str) {
        this.f6661c = str;
    }

    public boolean isEncrypt() {
        return this.f6662d;
    }

    public void setEncrypt(boolean z) {
        this.f6662d = z;
    }

    public String getDid() {
        return this.f6663e;
    }

    public void setDid(String str) {
        this.f6663e = str;
    }

    public String getAid() {
        return this.f6664f;
    }

    public void setAid(String str) {
        this.f6664f = str;
    }

    public String getProcessName() {
        return this.f6665g;
    }

    public void setProcessName(String str) {
        this.f6665g = str;
    }

    public List<String> getAlogFiles() {
        return this.f6666h;
    }

    public void setAlogFiles(List<String> list) {
        this.f6666h = list;
    }
}
