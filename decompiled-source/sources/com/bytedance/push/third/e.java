package com.bytedance.push.third;

import com.bytedance.common.utility.o;
import com.bytedance.push.utils.p;
/* compiled from: PushChannel.java */
/* loaded from: classes2.dex */
public class e extends p<c> implements c {

    /* renamed from: a  reason: collision with root package name */
    private int f11997a;

    /* renamed from: b  reason: collision with root package name */
    private String f11998b;

    /* renamed from: c  reason: collision with root package name */
    private boolean f11999c = false;

    /* renamed from: d  reason: collision with root package name */
    private boolean f12000d = false;

    /* renamed from: e  reason: collision with root package name */
    private b f12001e;

    /* renamed from: f  reason: collision with root package name */
    private String f12002f;

    /* renamed from: g  reason: collision with root package name */
    private com.bytedance.push.third.pushchannelsupport.b f12003g;

    @Override // com.bytedance.push.third.c
    public boolean isSupport() {
        if (!this.f12000d) {
            this.f11999c = this.f12003g.isSupportChannel(this.f12001e, this.f11997a);
            this.f12000d = true;
        }
        return this.f11999c;
    }

    @Override // com.bytedance.push.third.c
    public b getAdapterInstance() {
        return this.f12001e;
    }

    public e(int i2, String str, String str2, com.bytedance.push.third.pushchannelsupport.b bVar) {
        this.f11997a = i2;
        this.f11998b = str;
        this.f12002f = str2;
        this.f12003g = bVar;
    }

    @Override // com.bytedance.push.third.c
    public String getChannelClassName() {
        return this.f11998b;
    }

    @Override // com.bytedance.push.third.c
    public String getSenderName() {
        return this.f12002f;
    }

    @Override // com.bytedance.push.third.c
    public void resetInitStatus() {
        this.f12000d = false;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.bytedance.push.utils.p
    /* renamed from: a */
    public c b(Object... objArr) {
        if (o.isEmpty(this.f11998b)) {
            return this;
        }
        try {
            Object newInstance = Class.forName(this.f11998b).newInstance();
            if (newInstance instanceof b) {
                this.f12001e = (b) newInstance;
            }
            com.bytedance.push.utils.h.d("PushManager", "load PushManagerImpl success: " + this.f11998b);
        } catch (Throwable th) {
            com.bytedance.push.utils.h.e("PushManager", "load PushManagerImpl exception: " + this.f11998b + " exception is:" + th);
        }
        return this;
    }
}
