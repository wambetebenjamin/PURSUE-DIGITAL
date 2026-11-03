package com.bytedance.apm;

import java.util.Map;
import org.json.JSONObject;
/* compiled from: CommonParams.java */
/* loaded from: classes.dex */
public class g {

    /* renamed from: a  reason: collision with root package name */
    private int f1064a;

    /* renamed from: b  reason: collision with root package name */
    private String f1065b;

    /* renamed from: c  reason: collision with root package name */
    private String f1066c;

    /* renamed from: d  reason: collision with root package name */
    private String f1067d;

    /* renamed from: e  reason: collision with root package name */
    private int f1068e;

    /* renamed from: f  reason: collision with root package name */
    private String f1069f;

    /* renamed from: g  reason: collision with root package name */
    private int f1070g;

    /* renamed from: h  reason: collision with root package name */
    private int f1071h;

    /* renamed from: i  reason: collision with root package name */
    private String f1072i;

    /* renamed from: j  reason: collision with root package name */
    private String f1073j;
    private JSONObject k;
    private JSONObject l;
    private Map<String, String> m;

    public int getAid() {
        return this.f1064a;
    }

    public void setAid(int i2) {
        this.f1064a = i2;
    }

    public String getDeviceId() {
        return this.f1065b;
    }

    public void setDeviceId(String str) {
        this.f1065b = str;
    }

    public long getUid() {
        if (c.getDynamicParams() != null) {
            return c.getDynamicParams().getUid();
        }
        return 0L;
    }

    public String getSession() {
        if (c.getDynamicParams() != null) {
            return c.getDynamicParams().getSessionId();
        }
        return null;
    }

    public String getProcessName() {
        return this.f1066c;
    }

    public void setProcessName(String str) {
        this.f1066c = str;
    }

    public String getChannel() {
        return this.f1067d;
    }

    public void setChannel(String str) {
        this.f1067d = str;
    }

    public int getUpdateVersionCode() {
        return this.f1068e;
    }

    public void setUpdateVersionCode(int i2) {
        this.f1068e = i2;
    }

    public String getVersionName() {
        return this.f1069f;
    }

    public void setVersionName(String str) {
        this.f1069f = str;
    }

    public int getManifestVersionCode() {
        return this.f1070g;
    }

    public void setManifestVersionCode(int i2) {
        this.f1070g = i2;
    }

    public int getVersionCode() {
        return this.f1071h;
    }

    public void setVersionCode(int i2) {
        this.f1071h = i2;
    }

    public String getAppVersion() {
        return this.f1072i;
    }

    public void setAppVersion(String str) {
        this.f1072i = str;
    }

    public String getReleaseBuild() {
        return this.f1073j;
    }

    public void setReleaseBuild(String str) {
        this.f1073j = str;
    }

    public JSONObject getDynamicHeaderExtras() {
        return this.k;
    }

    public void setDynamicHeaderExtras(JSONObject jSONObject) {
        this.k = jSONObject;
    }

    public JSONObject getStaticHeaderExtras() {
        return this.l;
    }

    public void setStaticHeaderExtras(JSONObject jSONObject) {
        this.l = jSONObject;
    }

    public Map<String, String> getParamExtras() {
        return this.m;
    }

    public void setParamExtras(Map<String, String> map) {
        this.m = map;
    }
}
