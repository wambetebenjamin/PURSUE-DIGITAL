package com.bytedance.push.settings.pull;

import com.bytedance.push.settings.c;
import com.bytedance.push.settings.d;
import com.bytedance.push.utils.x;
/* compiled from: PullSettingsConverter.java */
/* loaded from: classes2.dex */
public class a implements c<b>, d<b> {
    @Override // com.bytedance.push.settings.c
    /* renamed from: a */
    public b create() {
        return new b();
    }

    @Override // com.bytedance.push.settings.d
    /* renamed from: a */
    public b to(String str) {
        return (b) x.a(str, b.class);
    }

    @Override // com.bytedance.push.settings.d
    /* renamed from: a */
    public String from(b bVar) {
        return x.a(bVar);
    }
}
