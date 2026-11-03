package com.bytedance.bdinstall.loader;

import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;
import org.json.JSONObject;
/* compiled from: AppKeyLoader.java */
/* loaded from: classes.dex */
public class a extends c {

    /* renamed from: e  reason: collision with root package name */
    private Context f3872e;

    /* JADX INFO: Access modifiers changed from: package-private */
    public a(Context context) {
        super(true, false);
        this.f3872e = context;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.bytedance.bdinstall.loader.c
    public boolean a(JSONObject jSONObject) {
        try {
            Bundle bundle = this.f3872e.getPackageManager().getApplicationInfo(this.f3872e.getPackageName(), 128).metaData;
            if (bundle != null) {
                jSONObject.put(com.bytedance.bdinstall.e.P, bundle.getString("UMENG_APPKEY"));
                return true;
            }
            return true;
        } catch (PackageManager.NameNotFoundException e2) {
            e2.printStackTrace();
            return true;
        }
    }
}
