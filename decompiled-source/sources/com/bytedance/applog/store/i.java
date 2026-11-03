package com.bytedance.applog.store;

import android.content.ContentValues;
import android.database.Cursor;
import android.text.TextUtils;
import com.facebook.internal.security.CertificateUtil;
import com.ss.android.common.applog.AppLog;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.json.JSONObject;
/* compiled from: Page.java */
/* loaded from: classes.dex */
public class i extends a {
    static final String P = "page_key";
    static final String Q = "duration";
    static final String R = "page";
    private static final String Y = "refer_page_key";
    private static final String Z = "is_back";
    private static final String aa = "last_session";
    private static final String ab = "flat_params";
    public long S;
    public String T;
    public String U;
    public int V;
    public String W;
    public String X;

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.bytedance.applog.store.a
    public String h() {
        return "page";
    }

    @Override // com.bytedance.applog.store.a
    public String i() {
        return "";
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.bytedance.applog.store.a
    public List<String> e() {
        List<String> e2 = super.e();
        ArrayList arrayList = new ArrayList(e2.size());
        arrayList.addAll(e2);
        arrayList.addAll(Arrays.asList(P, "varchar", Y, "varchar", "duration", "integer", Z, "integer", aa, "varchar", ab, "varchar"));
        return arrayList;
    }

    @Override // com.bytedance.applog.store.a
    public int a(Cursor cursor) {
        int a2 = super.a(cursor);
        int i2 = a2 + 1;
        this.U = cursor.getString(a2);
        int i3 = i2 + 1;
        this.T = cursor.getString(i2);
        int i4 = i3 + 1;
        this.S = cursor.getLong(i3);
        int i5 = i4 + 1;
        this.V = cursor.getInt(i4);
        int i6 = i5 + 1;
        this.W = cursor.getString(i5);
        int i7 = i6 + 1;
        this.X = cursor.getString(i6);
        return i7;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.bytedance.applog.store.a
    public void a(ContentValues contentValues) {
        super.a(contentValues);
        contentValues.put(P, this.U);
        contentValues.put(Y, this.T);
        contentValues.put("duration", Long.valueOf(this.S));
        contentValues.put(Z, Integer.valueOf(this.V));
        contentValues.put(aa, this.W);
        contentValues.put(ab, this.X);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.bytedance.applog.store.a
    public void a(JSONObject jSONObject) {
        super.a(jSONObject);
        jSONObject.put(P, this.U);
        jSONObject.put(Y, this.T);
        jSONObject.put("duration", this.S);
        jSONObject.put(Z, this.V);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.bytedance.applog.store.a
    public a b(JSONObject jSONObject) {
        super.b(jSONObject);
        this.U = jSONObject.optString(P, null);
        this.T = jSONObject.optString(Y, null);
        this.S = jSONObject.optLong("duration", 0L);
        this.V = jSONObject.optInt(Z, 0);
        return this;
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
        jSONObject.put("event", "bav2b_page");
        jSONObject.put("is_bav", 1);
        jSONObject.put("params", v());
        jSONObject.put("datetime", this.H);
        if (this.K > 0) {
            jSONObject.put(AppLog.KEY_EVENT_ID, this.K);
        }
        return jSONObject;
    }

    private JSONObject v() {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put(P, this.U);
        jSONObject.put(Y, this.T);
        jSONObject.put(Z, this.V);
        jSONObject.put("duration", this.S);
        return jSONObject;
    }

    public boolean t() {
        return this.S == -1;
    }

    public boolean u() {
        return this.U.contains(CertificateUtil.DELIMITER);
    }

    @Override // com.bytedance.applog.store.a
    public String n() {
        return this.U + ", " + this.S;
    }
}
