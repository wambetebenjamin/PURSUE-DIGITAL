package com.bytedance.apm.data.ui;

import com.bytedance.apm.structure.b;
import java.util.List;
import org.json.JSONObject;
/* compiled from: ActionRecord.java */
/* loaded from: classes.dex */
public class a {

    /* renamed from: a  reason: collision with root package name */
    private static volatile a f1003a;

    /* renamed from: b  reason: collision with root package name */
    private b<JSONObject> f1004b = new b<>(20);

    private a() {
    }

    public static a getInstance() {
        if (f1003a == null) {
            synchronized (a.class) {
                if (f1003a == null) {
                    f1003a = new a();
                }
            }
        }
        return f1003a;
    }

    public void addRecord(JSONObject jSONObject) {
        this.f1004b.enqueue(jSONObject);
    }

    public List<JSONObject> getRecords() {
        return this.f1004b.toList();
    }
}
