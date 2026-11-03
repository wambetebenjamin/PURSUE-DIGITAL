package com.bytedance.frameworks.baselib.network.http.ok3.impl.urldispatcher;

import android.text.TextUtils;
import com.bytedance.frameworks.baselib.network.http.ok3.impl.urldispatcher.a;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONObject;
/* compiled from: URLDispatchAction.java */
/* loaded from: classes2.dex */
public abstract class j extends a {

    /* renamed from: i  reason: collision with root package name */
    private static final String f8209i = "j";

    /* renamed from: j  reason: collision with root package name */
    private boolean f8210j = false;

    public abstract a.EnumC0115a a(l lVar, String str, List<String> list);

    protected abstract void a(String str, boolean z, int i2);

    protected abstract boolean a(JSONObject jSONObject, List<Boolean> list, String str, long j2);

    public abstract int e();

    public int h() {
        return -555;
    }

    public a.EnumC0115a a(l lVar, String str, List<String> list, List<d> list2) {
        if (!a(lVar) || TextUtils.isEmpty(str)) {
            list.set(0, str);
            return a.EnumC0115a.DISPATCH_NONE;
        }
        a.EnumC0115a a2 = a(lVar, str, list);
        d dVar = new d();
        if (a2 != a.EnumC0115a.DISPATCH_NONE) {
            dVar.f8183d = true;
            dVar.f8181b = b();
            dVar.f8182c = a();
            dVar.f8184e = e();
            dVar.f8180a = c();
            if (a2 == a.EnumC0115a.DISPATCH_HIT) {
                dVar.f8186g = list.get(0);
                dVar.f8185f = f();
            }
            lVar.a(this.f8171h);
        }
        list2.add(dVar);
        return a2;
    }

    @Override // com.bytedance.frameworks.baselib.network.http.ok3.impl.urldispatcher.a
    public boolean a(JSONObject jSONObject, String str, long j2) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(0, false);
        boolean a2 = a(jSONObject, arrayList, str, j2);
        this.f8210j = arrayList.get(0).booleanValue();
        return a2;
    }

    public void b(String str, boolean z, int i2) {
        if (this.f8210j) {
            a(str, z, i2);
        }
    }

    public boolean f() {
        return this.f8210j;
    }
}
