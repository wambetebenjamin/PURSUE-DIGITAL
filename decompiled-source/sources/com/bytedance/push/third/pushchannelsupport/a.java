package com.bytedance.push.third.pushchannelsupport;

import android.content.Context;
/* compiled from: AvalilablePushChannelSupportHelper.java */
/* loaded from: classes2.dex */
public class a extends b {

    /* renamed from: a  reason: collision with root package name */
    private Context f12010a;

    public a(Context context) {
        this.f12010a = context;
    }

    @Override // com.bytedance.push.third.pushchannelsupport.b
    public boolean isSupportChannel(com.bytedance.push.third.b bVar, int i2) {
        return bVar != null && bVar.isPushAvailable(this.f12010a, i2);
    }
}
