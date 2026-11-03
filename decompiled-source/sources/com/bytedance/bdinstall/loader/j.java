package com.bytedance.bdinstall.loader;

import android.content.Context;
import com.bytedance.bdinstall.am;
import org.json.JSONException;
import org.json.JSONObject;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: DeviceCategoryLoader.java */
/* loaded from: classes.dex */
public class j extends c {

    /* renamed from: e  reason: collision with root package name */
    private final am f3902e;

    /* JADX INFO: Access modifiers changed from: package-private */
    public j(Context context, am amVar) {
        super(true, true);
        this.f3902e = amVar;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.bytedance.bdinstall.loader.c
    public boolean a(JSONObject jSONObject) {
        try {
            jSONObject.put("device_category", this.f3902e.ac());
            return true;
        } catch (JSONException e2) {
            throw new RuntimeException(e2);
        }
    }
}
