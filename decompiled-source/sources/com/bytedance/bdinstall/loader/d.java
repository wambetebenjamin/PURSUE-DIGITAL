package com.bytedance.bdinstall.loader;

import android.content.Context;
import android.os.Build;
import org.json.JSONObject;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: BuildLoader.java */
/* loaded from: classes.dex */
public class d extends c {

    /* renamed from: e  reason: collision with root package name */
    private final Context f3880e;

    /* JADX INFO: Access modifiers changed from: package-private */
    public d(Context context) {
        super(true, false);
        this.f3880e = context;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.bytedance.bdinstall.loader.c
    public boolean a(JSONObject jSONObject) {
        jSONObject.put("os", com.bytedance.bdturing.utils.b.f4370d);
        jSONObject.put("os_version", Build.VERSION.RELEASE);
        jSONObject.put("os_api", Build.VERSION.SDK_INT);
        jSONObject.put("device_model", Build.BRAND.equals("Pico") ? com.bytedance.bdinstall.util.p.c() : Build.MODEL);
        jSONObject.put("device_brand", Build.BRAND);
        jSONObject.put("device_manufacturer", Build.MANUFACTURER);
        jSONObject.put("cpu_abi", Build.CPU_ABI);
        return true;
    }
}
