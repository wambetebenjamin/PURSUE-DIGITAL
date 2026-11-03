package com.bytedance.bdinstall.loader;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.text.TextUtils;
import com.bytedance.bdinstall.am;
import org.json.JSONObject;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: PackageLoader.java */
/* loaded from: classes.dex */
public class u extends c {

    /* renamed from: e  reason: collision with root package name */
    private final Context f3921e;

    /* renamed from: f  reason: collision with root package name */
    private final am f3922f;

    /* JADX INFO: Access modifiers changed from: package-private */
    public u(Context context, am amVar) {
        super(false, false);
        this.f3921e = context;
        this.f3922f = amVar;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.bytedance.bdinstall.loader.c
    public boolean a(JSONObject jSONObject) {
        int i2;
        String packageName = this.f3921e.getPackageName();
        if (TextUtils.isEmpty(this.f3922f.e())) {
            jSONObject.put("package", packageName);
        } else {
            if (com.bytedance.bdinstall.u.a()) {
                com.bytedance.bdinstall.u.a("has zijie pkg");
            }
            jSONObject.put("package", this.f3922f.e());
            jSONObject.put("real_package_name", packageName);
        }
        try {
            jSONObject.put("app_version", this.f3922f.f());
            jSONObject.put("app_version_minor", this.f3922f.g());
            jSONObject.put("version_code", this.f3922f.h());
            jSONObject.put("update_version_code", this.f3922f.i());
            jSONObject.put("manifest_version_code", this.f3922f.j());
            if (!TextUtils.isEmpty(this.f3922f.k())) {
                jSONObject.put("app_name", this.f3922f.k());
            }
            if (!TextUtils.isEmpty(this.f3922f.m())) {
                jSONObject.put(com.bytedance.bdinstall.e.au, this.f3922f.m());
            }
            PackageInfo packageInfo = this.f3921e.getPackageManager().getPackageInfo(packageName, 0);
            if (packageInfo.applicationInfo == null || (i2 = packageInfo.applicationInfo.labelRes) <= 0) {
                return true;
            }
            jSONObject.put("display_name", this.f3921e.getString(i2));
            return true;
        } catch (Throwable th) {
            com.bytedance.bdinstall.u.a(th);
            return false;
        }
    }
}
