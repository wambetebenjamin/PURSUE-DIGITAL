package com.bytedance.common.wschannel.channel.impl.ok.policy;

import android.content.Context;
/* compiled from: RetryTimesPolicy.java */
/* loaded from: classes2.dex */
public class c extends a {

    /* renamed from: b  reason: collision with root package name */
    public static final int f5516b = 4;

    /* renamed from: c  reason: collision with root package name */
    private final int f5517c;

    /* renamed from: d  reason: collision with root package name */
    private int f5518d;

    public c(Context context) {
        this(context, 4);
    }

    c(Context context, int i2) {
        super(context);
        this.f5517c = i2 < 0 ? 4 : i2;
    }

    @Override // com.bytedance.common.wschannel.channel.impl.ok.policy.a
    public long a() {
        long a2 = this.f5518d < this.f5517c ? super.a() : -1L;
        if (a2 != -1) {
            this.f5518d++;
        }
        return a2;
    }

    public String toString() {
        return "RetryTimesPolicy{mMaxRetryTime=" + this.f5517c + ", mCurrRetryTime=" + this.f5518d + '}';
    }

    @Override // com.bytedance.common.wschannel.channel.impl.ok.policy.a, com.bytedance.common.wschannel.channel.impl.ok.policy.b
    public void c() {
        super.c();
        this.f5518d = 0;
    }
}
