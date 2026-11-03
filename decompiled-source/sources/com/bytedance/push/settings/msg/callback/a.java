package com.bytedance.push.settings.msg.callback;

import com.bytedance.push.settings.c;
import com.bytedance.push.settings.d;
/* compiled from: MsgCallbackSettingsConverter.java */
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
        return new b(str);
    }

    @Override // com.bytedance.push.settings.d
    /* renamed from: a */
    public String from(b bVar) {
        return bVar.toString();
    }
}
