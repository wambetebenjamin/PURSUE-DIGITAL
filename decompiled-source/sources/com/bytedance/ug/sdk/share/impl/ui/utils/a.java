package com.bytedance.ug.sdk.share.impl.ui.utils;

import android.os.Handler;
import android.os.Looper;
import android.view.View;
/* compiled from: DebouncingOnClickListener.java */
/* loaded from: classes8.dex */
public abstract class a implements View.OnClickListener {

    /* renamed from: a  reason: collision with root package name */
    private static final Handler f16128a = new Handler(Looper.getMainLooper());

    /* renamed from: b  reason: collision with root package name */
    private static final long f16129b = 500;

    /* renamed from: c  reason: collision with root package name */
    private boolean f16130c;

    /* renamed from: d  reason: collision with root package name */
    private long f16131d;

    /* renamed from: e  reason: collision with root package name */
    private final Runnable f16132e;

    public abstract void doClick(View view);

    public a() {
        this(500L);
    }

    public a(long j2) {
        this.f16130c = true;
        this.f16132e = new Runnable() { // from class: com.bytedance.ug.sdk.share.impl.ui.utils.a.1
            @Override // java.lang.Runnable
            public void run() {
                a.this.f16130c = true;
            }
        };
        this.f16131d = j2;
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        if (this.f16130c) {
            this.f16130c = false;
            f16128a.postDelayed(this.f16132e, this.f16131d);
            doClick(view);
        }
    }

    public long getInterval() {
        return this.f16131d;
    }

    public void setInterval(long j2) {
        this.f16131d = j2;
    }
}
