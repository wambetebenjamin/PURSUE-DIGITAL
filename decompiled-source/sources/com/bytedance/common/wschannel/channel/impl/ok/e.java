package com.bytedance.common.wschannel.channel.impl.ok;

import android.text.TextUtils;
import android.util.Pair;
import com.bytedance.common.utility.Logger;
import com.bytedance.common.utility.o;
import java.util.ArrayList;
import java.util.List;
import okhttp3.Response;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: WsPolicy.java */
/* loaded from: classes2.dex */
public class e {

    /* renamed from: a  reason: collision with root package name */
    private final List<String> f5481a;

    /* renamed from: b  reason: collision with root package name */
    private com.bytedance.common.wschannel.channel.impl.ok.policy.b f5482b;

    /* renamed from: c  reason: collision with root package name */
    private int f5483c = 0;

    /* renamed from: d  reason: collision with root package name */
    private String f5484d;

    /* JADX INFO: Access modifiers changed from: package-private */
    public e(List<String> list, com.bytedance.common.wschannel.channel.impl.ok.policy.b bVar) {
        ArrayList arrayList = new ArrayList();
        this.f5481a = arrayList;
        if (list != null) {
            arrayList.addAll(list);
        }
        Logger.d(b.f5415a, "urls : " + arrayList);
        this.f5482b = bVar;
        b();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public synchronized Pair<String, Long> a(Response response) {
        String c2;
        long a2;
        c2 = c();
        a2 = this.f5482b.a(response);
        if (a2 == -1) {
            this.f5482b.c();
            c2 = a();
            if (!TextUtils.isEmpty(c2)) {
                a2 = this.f5482b.b();
            }
        }
        return new Pair<>(c2, Long.valueOf(a2));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public synchronized String a() {
        int i2 = this.f5483c + 1;
        this.f5483c = i2;
        if (this.f5481a.size() > i2) {
            String str = this.f5481a.get(i2);
            this.f5484d = str;
            return str;
        }
        return "";
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public synchronized void b() {
        this.f5482b.c();
        this.f5484d = null;
        this.f5483c = 0;
    }

    public synchronized String c() {
        if (o.isEmpty(this.f5484d)) {
            int size = this.f5481a.size();
            int i2 = this.f5483c;
            if (size > i2) {
                this.f5484d = this.f5481a.get(i2);
            }
        }
        return this.f5484d;
    }
}
