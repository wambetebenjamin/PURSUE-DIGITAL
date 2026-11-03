package com.bytedance.apm.config;

import org.json.JSONObject;
/* compiled from: FluencyConfig.java */
/* loaded from: classes.dex */
public class g {

    /* renamed from: a  reason: collision with root package name */
    private boolean f917a;

    /* renamed from: b  reason: collision with root package name */
    private boolean f918b;

    /* renamed from: c  reason: collision with root package name */
    private long f919c;

    /* renamed from: d  reason: collision with root package name */
    private boolean f920d;

    /* renamed from: e  reason: collision with root package name */
    private boolean f921e;

    /* renamed from: f  reason: collision with root package name */
    private int f922f;

    /* renamed from: g  reason: collision with root package name */
    private boolean f923g;

    /* renamed from: h  reason: collision with root package name */
    private long f924h;

    /* renamed from: i  reason: collision with root package name */
    private boolean f925i;

    /* renamed from: j  reason: collision with root package name */
    private boolean f926j;
    private boolean k;
    private long l;
    private long m;
    private boolean n;
    private boolean o;
    private JSONObject p;

    public boolean isEnableStackSampling() {
        return this.f917a;
    }

    public void setEnableStackSampling(boolean z) {
        this.f917a = z;
    }

    public boolean isEnableTrace() {
        return this.f918b;
    }

    public void setEnableTrace(boolean z) {
        this.f918b = z;
    }

    public long getAtraceTag() {
        return this.f919c;
    }

    public void setAtraceTag(long j2) {
        this.f919c = j2;
    }

    public boolean isBlockDumpStackEnable() {
        return this.f920d;
    }

    public void setBlockDumpStackEnable(boolean z) {
        this.f920d = z;
    }

    public boolean isEnableGfxMonitor() {
        return this.f921e;
    }

    public void setEnableGfxMonitor(boolean z) {
        this.f921e = z;
    }

    public int getBlockMonitorMode() {
        return this.f922f;
    }

    public void setBlockMonitorMode(int i2) {
        this.f922f = i2;
    }

    public boolean isSeriousBlockEnableUpload() {
        return this.f923g;
    }

    public void setSeriousBlockEnableUpload(boolean z) {
        this.f923g = z;
    }

    public long getSeriousBlockThreshold() {
        return this.f924h;
    }

    public void setSeriousBlockThreshold(long j2) {
        this.f924h = j2;
    }

    public boolean isSlowMethodEnableUpload() {
        return this.f925i;
    }

    public void setSlowMethodEnableUpload(boolean z) {
        this.f925i = z;
    }

    public boolean isDropEnableUpload() {
        return this.f926j;
    }

    public void setDropEnableUpload(boolean z) {
        this.f926j = z;
    }

    public boolean isFpsEnableUpload() {
        return this.k;
    }

    public void setFpsEnableUpload(boolean z) {
        this.k = z;
    }

    public long getBlockThreshold() {
        return this.l;
    }

    public void setBlockThreshold(long j2) {
        this.l = j2;
    }

    public long getSlowMethodDropThreshold() {
        return this.m;
    }

    public void setSlowMethodDropThreshold(long j2) {
        this.m = j2;
    }

    public boolean isBlockEnableUpload() {
        return this.n;
    }

    public void setBlockEnableUpload(boolean z) {
        this.n = z;
    }

    public boolean isDropSlowMethodSwitch() {
        return this.o;
    }

    public void setDropSlowMethodSwitch(boolean z) {
        this.o = z;
    }

    public boolean isAllowSceneEnable(String str) {
        JSONObject jSONObject = this.p;
        return jSONObject != null && jSONObject.optInt(str, 0) == 1;
    }

    public void setAllowScene(JSONObject jSONObject) {
        this.p = jSONObject;
    }

    public String toString() {
        return "FluencyConfig{enableStackSampling=" + this.f917a + ", enableTrace=" + this.f918b + ", atraceTag=" + this.f919c + ", blockDumpStackEnable=" + this.f920d + ", enableGfxMonitor=" + this.f921e + ", blockMonitorMode=" + this.f922f + ", seriousBlockEnableUpload=" + this.f923g + ", seriousBlockThreshold=" + this.f924h + ", slowMethodEnableUpload=" + this.f925i + ", dropEnableUpload=" + this.f926j + ", fpsEnableUpload=" + this.k + ", blockThreshold=" + this.l + ", slowMethodDropThreshold=" + this.m + ", blockEnableUpload=" + this.n + ", dropSlowMethodSwitch=" + this.o + '}';
    }
}
