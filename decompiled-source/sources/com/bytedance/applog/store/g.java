package com.bytedance.applog.store;

import android.content.ContentValues;
import android.database.Cursor;
import android.text.TextUtils;
import com.bytedance.applog.priority.original.m;
import com.ss.android.common.applog.AppLog;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import org.json.JSONObject;
/* compiled from: Launch.java */
/* loaded from: classes.dex */
public class g extends a {
    static final String P = "launch";
    static final String Q = "is_background";
    private static final String W = "ver_name";
    private static final String X = "ver_code";
    private static final String Y = "last_session";
    private static final String Z = "flat_params";
    public long R;
    public String S;
    public boolean T;
    public String U;
    public String V;

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.bytedance.applog.store.a
    public String h() {
        return "launch";
    }

    @Override // com.bytedance.applog.store.a
    public String i() {
        return "launch";
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.bytedance.applog.store.a
    public List<String> e() {
        List<String> e2 = super.e();
        ArrayList arrayList = new ArrayList(e2.size());
        arrayList.addAll(e2);
        arrayList.addAll(Arrays.asList(W, "varchar", X, "integer", Y, "varchar", "is_background", "integer", Z, "varchar"));
        return arrayList;
    }

    @Override // com.bytedance.applog.store.a
    public int a(Cursor cursor) {
        int a2 = super.a(cursor);
        int i2 = a2 + 1;
        this.S = cursor.getString(a2);
        int i3 = i2 + 1;
        this.R = cursor.getLong(i2);
        int i4 = i3 + 1;
        this.U = cursor.getString(i3);
        int i5 = i4 + 1;
        this.T = cursor.getInt(i4) == 1;
        int i6 = i5 + 1;
        this.V = cursor.getString(i5);
        return i6;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.bytedance.applog.store.a
    public void a(ContentValues contentValues) {
        super.a(contentValues);
        contentValues.put(W, this.S);
        contentValues.put(X, Long.valueOf(this.R));
        contentValues.put(Y, this.U);
        contentValues.put("is_background", Integer.valueOf(this.T ? 1 : 0));
        contentValues.put(Z, this.V);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.bytedance.applog.store.a
    public void a(JSONObject jSONObject) {
        s().e(4, this.N, "write ipc not implemented", new Object[0]);
    }

    @Override // com.bytedance.applog.store.a
    protected JSONObject f() {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("local_time_ms", this.v);
        jSONObject.put("tea_event_index", this.w);
        jSONObject.put("session_id", this.x);
        c(jSONObject);
        if (!TextUtils.isEmpty(this.A)) {
            jSONObject.put("user_unique_id", this.A);
        }
        if (this.T) {
            jSONObject.put("is_background", true);
        }
        jSONObject.put("datetime", this.H);
        if (!TextUtils.isEmpty(this.B)) {
            jSONObject.put("ab_sdk_version", this.B);
        }
        if (!TextUtils.isEmpty(this.U)) {
            jSONObject.put("uuid_changed", true);
            jSONObject.put("original_session_id", this.U);
        }
        if (this.K > 0) {
            jSONObject.put(AppLog.KEY_EVENT_ID, this.K);
        }
        if (this.L > 0) {
            jSONObject.put(m.f3283d, String.valueOf(this.L));
        }
        if (this.M > 0) {
            jSONObject.put("event_stained", this.M);
        }
        if (!TextUtils.isEmpty(this.V)) {
            try {
                JSONObject jSONObject2 = new JSONObject(this.V);
                Iterator<String> keys = jSONObject2.keys();
                while (keys.hasNext()) {
                    String next = keys.next();
                    if (!jSONObject.has(next)) {
                        jSONObject.put(next, jSONObject2.opt(next));
                    } else {
                        s().d(4, this.N, "Add params to launch conflict: {} - {}", next, jSONObject2.opt(next));
                    }
                }
            } catch (Throwable th) {
                s().a(4, this.N, "Add flat params: {} to launch error", th, this.V);
            }
        }
        return jSONObject;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.bytedance.applog.store.a
    public a b(JSONObject jSONObject) {
        s().e(4, this.N, "read ipc not implemented", new Object[0]);
        return null;
    }

    @Override // com.bytedance.applog.store.a
    public String n() {
        return this.T ? "bg" : "fg";
    }
}
