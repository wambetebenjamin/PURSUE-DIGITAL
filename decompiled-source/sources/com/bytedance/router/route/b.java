package com.bytedance.router.route;

import android.net.Uri;
import android.os.Bundle;
/* compiled from: BaseRoute.java */
/* loaded from: classes2.dex */
public abstract class b implements d {

    /* renamed from: a  reason: collision with root package name */
    private String f12225a;

    /* renamed from: b  reason: collision with root package name */
    private Uri f12226b;

    /* renamed from: c  reason: collision with root package name */
    private com.bytedance.router.c f12227c;

    /* renamed from: d  reason: collision with root package name */
    private com.bytedance.router.e f12228d;

    public void init(com.bytedance.router.c cVar, com.bytedance.router.e eVar) {
        this.f12227c = cVar;
        String url = cVar.getUrl();
        this.f12225a = url;
        this.f12226b = Uri.parse(url);
        this.f12228d = eVar;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public com.bytedance.router.c a() {
        return this.f12227c;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public String b() {
        return this.f12228d.getTargetClass(this.f12225a);
    }

    @Override // com.bytedance.router.route.d
    public String getUrl() {
        return this.f12225a;
    }

    @Override // com.bytedance.router.route.d
    public String getScheme() {
        return this.f12226b.getScheme();
    }

    @Override // com.bytedance.router.route.d
    public String getHost() {
        return this.f12226b.getHost();
    }

    @Override // com.bytedance.router.route.d
    public String getPath() {
        return this.f12226b.getPath();
    }

    @Override // com.bytedance.router.route.d
    public Bundle getParams() {
        if (this.f12227c.getExtra() != null) {
            return this.f12227c.getExtra().getExtras();
        }
        return new Bundle();
    }
}
