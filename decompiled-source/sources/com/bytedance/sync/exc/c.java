package com.bytedance.sync.exc;

import java.io.IOException;
/* compiled from: SyncIOException.java */
/* loaded from: classes3.dex */
public class c extends IOException implements b {

    /* renamed from: a  reason: collision with root package name */
    int f13431a;

    public c(int i2, String str, Throwable th) {
        super(str, th);
        this.f13431a = i2;
    }

    @Override // com.bytedance.sync.exc.b
    public int getErrorCode() {
        return this.f13431a;
    }

    @Override // com.bytedance.sync.exc.b
    public String getErrorMsg() {
        return getMessage();
    }
}
