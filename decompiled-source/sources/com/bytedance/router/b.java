package com.bytedance.router;

import android.content.Context;
import android.text.TextUtils;
import com.bytedance.router.c;
import java.util.LinkedList;
import java.util.List;
/* compiled from: InterceptManager.java */
/* loaded from: classes2.dex */
public class b {

    /* renamed from: a  reason: collision with root package name */
    private List<com.bytedance.router.interceptor.a> f12157a = null;

    public void addInterceptor(com.bytedance.router.interceptor.a aVar) {
        if (aVar == null) {
            return;
        }
        if (this.f12157a == null) {
            this.f12157a = new LinkedList();
        }
        this.f12157a.add(aVar);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean a(Context context, c cVar) {
        if (cVar == null) {
            return true;
        }
        List<com.bytedance.router.interceptor.a> list = this.f12157a;
        if (list != null && list.size() != 0) {
            for (com.bytedance.router.interceptor.a aVar : this.f12157a) {
                if (aVar.matchInterceptRules(cVar) && aVar.onInterceptRoute(context, cVar)) {
                    return true;
                }
            }
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean a(String str) {
        List<com.bytedance.router.interceptor.a> list;
        if (!TextUtils.isEmpty(str) && (list = this.f12157a) != null && list.size() != 0) {
            c build = new c.a(str).build();
            for (com.bytedance.router.interceptor.a aVar : this.f12157a) {
                if (aVar.matchInterceptRules(build)) {
                    return true;
                }
            }
        }
        return false;
    }
}
