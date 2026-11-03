package com.bytedance.push.settings.notification;
/* compiled from: NotificationSettingsConverter.java */
/* loaded from: classes2.dex */
public class g implements com.bytedance.push.settings.c<f>, com.bytedance.push.settings.d<f> {
    @Override // com.bytedance.push.settings.c
    /* renamed from: a */
    public f create() {
        return new f();
    }

    @Override // com.bytedance.push.settings.d
    /* renamed from: a */
    public f to(String str) {
        return new f(str);
    }

    @Override // com.bytedance.push.settings.d
    /* renamed from: a */
    public String from(f fVar) {
        return fVar.a();
    }
}
