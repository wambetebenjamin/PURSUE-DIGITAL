package com.ss.android;

import com.ss.android.token.d;
/* compiled from: TTAccountExtraConfig.java */
/* loaded from: classes9.dex */
public class g {

    /* renamed from: a  reason: collision with root package name */
    private long f18296a = 600000;

    /* renamed from: b  reason: collision with root package name */
    private a f18297b;

    /* compiled from: TTAccountExtraConfig.java */
    /* loaded from: classes9.dex */
    public interface a {
        void onException(Throwable th);
    }

    public long getUpdateInfoInterval() {
        return this.f18296a;
    }

    public g setUpdateInfoInterval(long j2) {
        this.f18296a = j2;
        return this;
    }

    public void setInnerExceptionCatcher(final a aVar) {
        this.f18297b = aVar;
        com.ss.android.token.d.a(new d.a() { // from class: com.ss.android.g.1
            @Override // com.ss.android.token.d.a
            public void a(Throwable th) {
                aVar.onException(th);
            }
        });
    }

    public a getInnerExceptionCatcher() {
        return this.f18297b;
    }
}
