package com.bytedance.sync.exc;
/* compiled from: SyncApiException.java */
/* loaded from: classes3.dex */
public class a extends Exception implements b {

    /* renamed from: a  reason: collision with root package name */
    private int f13430a;

    public a(int i2, String str) {
        super(str);
        this.f13430a = i2;
    }

    @Override // com.bytedance.sync.exc.b
    public int getErrorCode() {
        return this.f13430a;
    }

    public void setErrorCode(int i2) {
        this.f13430a = i2;
    }

    @Override // com.bytedance.sync.exc.b
    public String getErrorMsg() {
        return getMessage();
    }
}
