package com.bytedance.crash.upload;

import android.text.TextUtils;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.json.JSONObject;
/* compiled from: Response.java */
/* loaded from: classes2.dex */
public class p {

    /* renamed from: a  reason: collision with root package name */
    private int f7243a;

    /* renamed from: b  reason: collision with root package name */
    private String f7244b;

    /* renamed from: c  reason: collision with root package name */
    private JSONObject f7245c;

    /* renamed from: d  reason: collision with root package name */
    private byte[] f7246d;

    /* renamed from: e  reason: collision with root package name */
    private int f7247e;

    /* renamed from: f  reason: collision with root package name */
    private Map<String, List<String>> f7248f;

    public p(int i2) {
        this.f7243a = i2;
    }

    public p(int i2, byte[] bArr) {
        this.f7243a = i2;
        this.f7246d = bArr;
    }

    public p(int i2, Throwable th) {
        this.f7243a = i2;
        if (th != null) {
            this.f7244b = th.getMessage();
        }
    }

    public p(int i2, String str) {
        this.f7243a = i2;
        this.f7244b = str;
    }

    public p(int i2, JSONObject jSONObject) {
        this.f7243a = i2;
        this.f7245c = jSONObject;
    }

    public boolean isSuccess() {
        int i2 = this.f7243a;
        return (i2 == 207 || i2 == 214) ? false : true;
    }

    public boolean a() {
        return this.f7243a == 213;
    }

    public boolean isStateOk() {
        JSONObject jSONObject = this.f7245c;
        return jSONObject != null && jSONObject.optInt("state") == 0;
    }

    public byte[] getData() {
        return this.f7246d;
    }

    public void a(byte[] bArr) {
        this.f7246d = bArr;
    }

    public void a(int i2) {
        this.f7243a = i2;
    }

    public JSONObject getServerJson() {
        return this.f7245c;
    }

    public void a(JSONObject jSONObject) {
        this.f7245c = jSONObject;
        try {
            String str = "";
            if (this.f7248f != null) {
                new ArrayList();
                Iterator<Map.Entry<String, List<String>>> it = this.f7248f.entrySet().iterator();
                while (true) {
                    if (!it.hasNext()) {
                        break;
                    }
                    Map.Entry<String, List<String>> next = it.next();
                    if ("Alog_quota".equalsIgnoreCase(next.getKey())) {
                        List<String> value = next.getValue();
                        if (value.size() > 0) {
                            str = value.get(0);
                            break;
                        }
                    }
                }
            }
            this.f7245c.putOpt("Alog_quota", str);
            String optString = this.f7245c.optString("message");
            if (TextUtils.isEmpty(optString)) {
                return;
            }
            if (optString.equals(com.bytedance.apm.constant.k.RESPONSE_MESSAGE_DROP_ALL_DATA) || optString.equals(com.bytedance.apm.constant.k.RESPONSE_MESSAGE_DROP_DATA)) {
                a(213);
            }
        } catch (Throwable unused) {
        }
    }

    public void b(int i2) {
        this.f7247e = i2;
    }

    public int errorCode() {
        return this.f7243a;
    }

    public int b() {
        return this.f7247e;
    }

    public String errorInfo() {
        return this.f7244b;
    }

    public void a(String str) {
        this.f7244b = str;
    }

    public void a(Map<String, List<String>> map) {
        this.f7248f = map;
    }

    public String toString() {
        String str = "Response:mErrorCode=" + this.f7243a + ", mServerErrorCode=" + this.f7247e;
        return this.f7245c != null ? str + ", mServerJson=" + this.f7245c.toString() : str;
    }
}
