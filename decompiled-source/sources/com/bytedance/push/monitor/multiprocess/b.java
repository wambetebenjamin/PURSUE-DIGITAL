package com.bytedance.push.monitor.multiprocess;

import android.content.ContentValues;
import android.database.Cursor;
import org.json.JSONObject;
/* compiled from: MonitorEvent.java */
/* loaded from: classes2.dex */
class b extends com.bytedance.common.push.c {

    /* renamed from: a  reason: collision with root package name */
    public long f11494a;

    /* renamed from: b  reason: collision with root package name */
    public String f11495b;

    /* renamed from: c  reason: collision with root package name */
    public String f11496c;

    /* renamed from: d  reason: collision with root package name */
    public String f11497d;

    /* renamed from: e  reason: collision with root package name */
    public String f11498e;

    public b(String str, JSONObject jSONObject, JSONObject jSONObject2, JSONObject jSONObject3) {
        this.f11495b = str;
        if (jSONObject != null) {
            this.f11496c = jSONObject.toString();
        }
        if (jSONObject2 != null) {
            this.f11497d = jSONObject2.toString();
        }
        if (jSONObject3 != null) {
            this.f11498e = jSONObject3.toString();
        }
    }

    public b(Cursor cursor) {
        this.f11494a = cursor.getLong(0);
        this.f11495b = cursor.getString(1);
        this.f11496c = cursor.getString(2);
        this.f11497d = cursor.getString(3);
        this.f11498e = cursor.getString(4);
    }

    public JSONObject a() {
        JSONObject jSONObject = new JSONObject();
        add(jSONObject, "service_name", this.f11495b);
        add(jSONObject, "category", this.f11496c);
        add(jSONObject, "metric", this.f11497d);
        add(jSONObject, "extra", this.f11498e);
        return jSONObject;
    }

    public ContentValues b() {
        ContentValues contentValues = new ContentValues();
        contentValues.put("service_name", this.f11495b);
        contentValues.put("category", this.f11496c);
        contentValues.put("metric", this.f11497d);
        contentValues.put("extra", this.f11498e);
        return contentValues;
    }

    public String toString() {
        return "{id=" + this.f11494a + ", serviceName='" + this.f11495b + "', category='" + this.f11496c + "', metric='" + this.f11497d + "', extra='" + this.f11498e + "'}";
    }

    public JSONObject c() {
        try {
            return new JSONObject(this.f11496c);
        } catch (Throwable unused) {
            return null;
        }
    }

    public JSONObject d() {
        try {
            return new JSONObject(this.f11497d);
        } catch (Throwable unused) {
            return null;
        }
    }

    public JSONObject e() {
        try {
            return new JSONObject(this.f11498e);
        } catch (Throwable unused) {
            return null;
        }
    }
}
