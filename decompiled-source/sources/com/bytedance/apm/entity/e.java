package com.bytedance.apm.entity;
/* compiled from: InitialLogInfo.java */
/* loaded from: classes.dex */
public class e {
    public boolean isSampled;
    public String key;
    public String type;
    public String type2;
    public float value;

    public e(String str, String str2, String str3, float f2, boolean z) {
        this.type = str;
        this.type2 = str2;
        this.key = str3;
        this.value = f2;
        this.isSampled = z;
    }
}
