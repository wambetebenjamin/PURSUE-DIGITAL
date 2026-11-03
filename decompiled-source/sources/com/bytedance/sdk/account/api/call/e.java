package com.bytedance.sdk.account.api.call;

import java.util.List;
/* compiled from: LogoutOthersApiResponse.java */
/* loaded from: classes2.dex */
public class e extends b {

    /* renamed from: a  reason: collision with root package name */
    private List<String> f12248a;

    public e(boolean z, int i2) {
        super(z, i2);
    }

    public List<String> getLogoutUserIds() {
        return this.f12248a;
    }

    public void setLogoutUserIds(List<String> list) {
        this.f12248a = list;
    }
}
