package com.bytedance.ug.sdk.share.api.callback;

import java.util.UUID;
/* compiled from: IShareTokenGenerator.java */
/* loaded from: classes8.dex */
public interface f {
    String generateShareToken(com.bytedance.ug.sdk.share.api.entity.h hVar);

    /* compiled from: IShareTokenGenerator.java */
    /* loaded from: classes8.dex */
    public static class a implements f {
        @Override // com.bytedance.ug.sdk.share.api.callback.f
        public String generateShareToken(com.bytedance.ug.sdk.share.api.entity.h hVar) {
            return UUID.randomUUID().toString();
        }
    }
}
