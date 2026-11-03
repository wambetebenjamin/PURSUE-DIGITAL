package com.bytedance.bdinstall.loader;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.Signature;
import org.json.JSONObject;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: SigHashLoader.java */
/* loaded from: classes.dex */
public class x extends c {

    /* renamed from: e  reason: collision with root package name */
    private final Context f3925e;

    /* JADX INFO: Access modifiers changed from: package-private */
    public x(Context context) {
        super(true, false);
        this.f3925e = context;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.bytedance.bdinstall.loader.c
    public boolean a(JSONObject jSONObject) {
        PackageInfo packageInfo;
        Signature signature;
        String str = null;
        try {
            packageInfo = this.f3925e.getPackageManager().getPackageInfo(this.f3925e.getPackageName(), 64);
        } catch (Exception e2) {
            com.bytedance.bdinstall.u.a(e2);
            packageInfo = null;
        }
        if (packageInfo != null && packageInfo.signatures != null && packageInfo.signatures.length > 0 && (signature = packageInfo.signatures[0]) != null) {
            str = com.bytedance.bdinstall.util.e.b(signature.toByteArray());
        }
        if (str != null) {
            jSONObject.put("sig_hash", str);
            return true;
        }
        return true;
    }
}
