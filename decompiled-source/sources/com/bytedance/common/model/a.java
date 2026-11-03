package com.bytedance.common.model;

import android.text.TextUtils;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;
/* compiled from: MethodCallRecord.java */
/* loaded from: classes2.dex */
public class a extends com.bytedance.common.push.c {

    /* renamed from: a  reason: collision with root package name */
    private String f5065a;

    /* renamed from: b  reason: collision with root package name */
    private String f5066b;

    /* renamed from: c  reason: collision with root package name */
    private String f5067c;

    /* renamed from: d  reason: collision with root package name */
    private String f5068d;

    /* renamed from: e  reason: collision with root package name */
    private List f5069e;

    /* renamed from: f  reason: collision with root package name */
    private String f5070f;

    public String toString() {
        return "MethodCallRecord{originProcessSuffix='" + this.f5065a + "', targetProcessSuffix='" + this.f5066b + "', methodName='" + this.f5067c + "', args='" + this.f5068d + "', id='" + this.f5070f + "'}";
    }

    public a(String str, String str2, String str3, String str4, String str5) {
        this.f5065a = str;
        this.f5066b = str2;
        this.f5067c = str3;
        this.f5068d = str4;
        this.f5070f = str5;
        if (TextUtils.isEmpty(str4)) {
            return;
        }
        try {
            JSONObject jSONObject = new JSONObject(str4);
            int length = jSONObject.length();
            this.f5069e = new ArrayList();
            for (int i2 = 0; i2 < length; i2++) {
                this.f5069e.add(jSONObject.getString(i2 + ""));
            }
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
    }

    public a(String str, String str2, String str3, List list) {
        this.f5065a = str;
        this.f5066b = str2;
        this.f5067c = str3;
        this.f5069e = list;
        if (list == null) {
            this.f5068d = new JSONObject().toString();
            return;
        }
        JSONObject jSONObject = new JSONObject();
        for (int i2 = 0; i2 < list.size(); i2++) {
            add(jSONObject, i2 + "", list.get(i2).toString());
        }
        this.f5068d = jSONObject.toString();
    }

    public String getMethodName() {
        return this.f5067c;
    }

    public a setMethodName(String str) {
        this.f5067c = str;
        return this;
    }

    public String getArgs() {
        return this.f5068d;
    }

    public a setArgs(String str) {
        this.f5068d = str;
        return this;
    }

    public String getTargetProcessSuffix() {
        return this.f5066b;
    }

    public a setTargetProcessSuffix(String str) {
        this.f5066b = str;
        return this;
    }

    public List getListArgs() {
        return this.f5069e;
    }

    public a setListArgs(List list) {
        this.f5069e = list;
        return this;
    }

    public String getOriginProcessSuffix() {
        return this.f5065a;
    }

    public a setOriginProcessSuffix(String str) {
        this.f5065a = str;
        return this;
    }

    public String getId() {
        return this.f5070f;
    }

    public a setId(String str) {
        this.f5070f = str;
        return this;
    }
}
