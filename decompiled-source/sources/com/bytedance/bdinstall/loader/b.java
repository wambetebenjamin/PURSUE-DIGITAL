package com.bytedance.bdinstall.loader;

import android.content.Context;
import com.bytedance.bdinstall.am;
import org.json.JSONObject;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: AppTraitLoader.java */
/* loaded from: classes.dex */
public final class b extends c {

    /* renamed from: e  reason: collision with root package name */
    private final Context f3873e;

    /* renamed from: f  reason: collision with root package name */
    private final am f3874f;

    /* JADX INFO: Access modifiers changed from: package-private */
    public b(Context context, am amVar) {
        super(true, false);
        this.f3873e = context;
        this.f3874f = amVar;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.bytedance.bdinstall.loader.c
    public boolean a(JSONObject jSONObject) {
        com.bytedance.bdinstall.intf.a Q = this.f3874f.Q();
        com.bytedance.bdinstall.u.a("IAppTraitCallback = " + Q);
        if (Q != null) {
            String str = Q.get(this.f3873e);
            com.bytedance.bdinstall.u.a("IAppTraitCallback.get = " + str);
            jSONObject.put("app_trait", str);
            return true;
        }
        return true;
    }
}
