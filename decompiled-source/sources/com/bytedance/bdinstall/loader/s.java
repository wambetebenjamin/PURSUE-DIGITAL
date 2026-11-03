package com.bytedance.bdinstall.loader;

import android.content.Context;
import com.bytedance.bdinstall.ax;
import org.json.JSONObject;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: NetLoader.java */
/* loaded from: classes.dex */
public class s extends c {

    /* renamed from: e  reason: collision with root package name */
    private final Context f3920e;

    /* JADX INFO: Access modifiers changed from: package-private */
    public s(Context context) {
        super(true, true);
        this.f3920e = context;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.bytedance.bdinstall.loader.c
    public boolean a(JSONObject jSONObject) {
        ax.a(jSONObject, com.bytedance.bdinstall.e.p, com.bytedance.common.utility.l.getNetworkAccessType(this.f3920e));
        return true;
    }
}
