package com.bytedance.apm6.consumer.slardar.persistent;

import org.json.JSONObject;
/* compiled from: LogItem.java */
/* loaded from: classes.dex */
public class b {

    /* renamed from: a  reason: collision with root package name */
    private byte[] f1820a;

    public b(byte[] bArr) {
        this.f1820a = bArr;
    }

    public byte[] getData() {
        return this.f1820a;
    }

    public String toString() {
        try {
            return new JSONObject(new String(this.f1820a)).toString();
        } catch (Exception unused) {
            return "";
        }
    }
}
