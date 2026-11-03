package com.bytedance.applog.store;

import android.content.ContentValues;
import android.database.Cursor;
import android.text.TextUtils;
import com.bytedance.applog.priority.original.m;
import com.ss.android.common.applog.AppLog;
import java.util.Iterator;
import java.util.List;
import org.json.JSONObject;
/* compiled from: Terminate.java */
/* loaded from: classes.dex */
public class j extends a {
    static final String P = "terminate";
    static final String Q = "stop_timestamp";
    private static final String Z = "duration";
    public long R;
    public long S;
    public String T;
    public Integer U;
    public String V;
    public String W;
    public Integer X;
    public String Y;

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.bytedance.applog.store.a
    public List<String> e() {
        return null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.bytedance.applog.store.a
    public String h() {
        return "terminate";
    }

    @Override // com.bytedance.applog.store.a
    public String i() {
        return "terminate";
    }

    @Override // com.bytedance.applog.store.a
    public int a(Cursor cursor) {
        s().e(4, this.N, "read db not implemented", new Object[0]);
        return 0;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.bytedance.applog.store.a
    public void a(ContentValues contentValues) {
        s().e(4, this.N, "write db not implemented", new Object[0]);
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
        jSONObject.put(Q, this.S / 1000);
        jSONObject.put("duration", this.R / 1000);
        jSONObject.put("datetime", this.H);
        c(jSONObject);
        if (!TextUtils.isEmpty(this.A)) {
            jSONObject.put("user_unique_id", this.A);
        }
        if (!TextUtils.isEmpty(this.B)) {
            jSONObject.put("ab_sdk_version", this.B);
        }
        if (!TextUtils.isEmpty(this.T)) {
            jSONObject.put("uuid_changed", true);
            if (!TextUtils.equals(this.T, this.x)) {
                jSONObject.put("original_session_id", this.T);
            }
        }
        Integer num = this.U;
        if (num != null) {
            jSONObject.put(com.bytedance.applog.server.a.T, num);
        }
        if (!TextUtils.isEmpty(this.W)) {
            jSONObject.put(com.bytedance.applog.server.a.R, this.W);
        }
        if (!TextUtils.isEmpty(this.V)) {
            jSONObject.put(com.bytedance.applog.server.a.S, this.V);
        }
        Integer num2 = this.X;
        if (num2 != null) {
            jSONObject.put(com.bytedance.applog.server.a.Q, num2);
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
        if (!TextUtils.isEmpty(this.Y)) {
            try {
                JSONObject jSONObject2 = new JSONObject(this.Y);
                Iterator<String> keys = jSONObject2.keys();
                while (keys.hasNext()) {
                    String next = keys.next();
                    if (!jSONObject.has(next)) {
                        jSONObject.put(next, jSONObject2.opt(next));
                    } else {
                        s().d(4, this.N, "Add flat params to terminate conflict: {} - {}", next, jSONObject2.opt(next));
                    }
                }
            } catch (Throwable th) {
                s().a(4, this.N, "Add params to terminate error", th, new Object[0]);
            }
        }
        return jSONObject;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.bytedance.applog.store.a
    public a b(JSONObject jSONObject) {
        s().e(4, this.N, "read ipc not implemented", new Object[0]);
        return this;
    }

    @Override // com.bytedance.applog.store.a
    public String n() {
        return String.valueOf(this.R);
    }
}
