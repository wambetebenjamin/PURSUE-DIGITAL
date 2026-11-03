package com.bytedance.push;

import android.content.ContentValues;
import android.database.Cursor;
import android.text.TextUtils;
import com.bytedance.android.service.manager.PushServiceManager;
import org.json.JSONException;
import org.json.JSONObject;
/* compiled from: PushNotificationMessage.java */
/* loaded from: classes2.dex */
public class l extends com.bytedance.common.push.c {

    /* renamed from: a  reason: collision with root package name */
    public int f11415a;

    /* renamed from: b  reason: collision with root package name */
    public long f11416b;

    /* renamed from: c  reason: collision with root package name */
    public long f11417c;

    /* renamed from: d  reason: collision with root package name */
    public boolean f11418d;

    /* renamed from: e  reason: collision with root package name */
    public boolean f11419e;

    /* renamed from: f  reason: collision with root package name */
    private long f11420f;

    /* renamed from: g  reason: collision with root package name */
    private String f11421g;

    /* renamed from: h  reason: collision with root package name */
    private g f11422h;

    /* renamed from: i  reason: collision with root package name */
    private final String f11423i = "client_time";

    /* renamed from: j  reason: collision with root package name */
    private final String f11424j = "run_type";
    private final String k = com.bytedance.push.client.intelligence.a.CLIENT_FEATURE;
    private final String l = "expire_time";

    public l(Cursor cursor) {
        this.f11420f = cursor.getLong(0);
        this.f11416b = cursor.getLong(1);
        this.f11417c = cursor.getLong(2);
        this.f11415a = cursor.getInt(3);
        this.f11418d = cursor.getInt(4) == 1;
        this.f11419e = cursor.getInt(5) == 1;
        String string = cursor.getString(6);
        this.f11421g = string;
        if (TextUtils.isEmpty(string)) {
            return;
        }
        try {
            this.f11422h = new g(new JSONObject(this.f11421g));
        } catch (JSONException e2) {
            e2.printStackTrace();
            this.f11422h = null;
        }
    }

    public ContentValues e() {
        ContentValues contentValues = new ContentValues();
        contentValues.put("message_id", Long.valueOf(c()));
        contentValues.put(com.bytedance.push.helper.c.f11358d, Long.valueOf(this.f11416b));
        contentValues.put(com.bytedance.push.helper.c.f11359e, Long.valueOf(this.f11417c));
        contentValues.put("sender", Integer.valueOf(this.f11415a));
        contentValues.put(com.bytedance.push.helper.c.f11362h, Integer.valueOf(this.f11418d ? 1 : 0));
        contentValues.put(com.bytedance.push.helper.c.f11363i, Integer.valueOf(this.f11419e ? 1 : 0));
        contentValues.put("push_body", a());
        return contentValues;
    }

    public l(int i2, long j2, long j3, long j4, boolean z, boolean z2, String str) {
        this.f11415a = i2;
        this.f11420f = j2;
        this.f11416b = j3;
        this.f11417c = j4;
        this.f11418d = z;
        this.f11419e = z2;
        this.f11421g = str;
    }

    public l(int i2, long j2, long j3, long j4, boolean z, boolean z2, g gVar) {
        this.f11415a = i2;
        this.f11420f = j2;
        this.f11416b = j3;
        this.f11417c = j4;
        this.f11418d = z;
        this.f11419e = z2;
        this.f11422h = gVar;
        this.f11421g = gVar.getOriginData();
    }

    public String a() {
        g gVar;
        if (TextUtils.isEmpty(this.f11421g) && (gVar = this.f11422h) != null) {
            this.f11421g = gVar.getOriginData();
        }
        return this.f11421g;
    }

    public g b() {
        if (this.f11422h == null) {
            try {
                this.f11422h = new g(new JSONObject(this.f11421g));
            } catch (JSONException e2) {
                e2.printStackTrace();
            }
        }
        return this.f11422h;
    }

    public l a(g gVar) {
        this.f11422h = gVar;
        return this;
    }

    public long c() {
        return this.f11420f;
    }

    public long d() {
        return this.f11416b;
    }

    public JSONObject f() {
        JSONObject jSONObject = new JSONObject();
        add(jSONObject, "run_type", "show_push");
        add(jSONObject, "client_time", System.currentTimeMillis());
        add(jSONObject, "expire_time", this.f11417c);
        add(jSONObject, com.ss.android.pushmanager.e.KEY_RULE_ID, this.f11422h.id);
        add(jSONObject, com.ss.android.pushmanager.e.KEY_RULE_ID64, this.f11422h.rid64);
        add(jSONObject, com.bytedance.push.client.intelligence.a.CLIENT_FEATURE, PushServiceManager.get().getPushExternalService().getClientFeatureService().getClientFeatureSync());
        return jSONObject;
    }
}
