package com.bytedance.sync.interfaze;

import android.content.Context;
/* compiled from: ISyncClient.java */
/* loaded from: classes3.dex */
public interface n {

    /* compiled from: ISyncClient.java */
    /* loaded from: classes3.dex */
    public static class a {
        public long cursor;
        public byte[] data;
        public String did;
        public long publishTimeStamp;
        public long receiveTimeStamp;
        public String topic;
        public String uid;
    }

    void addOnDataUpdateListener(r rVar);

    void remove();

    void removeOnDataUpdateListener(r rVar);

    b sendMsg(Context context, byte[] bArr);

    /* compiled from: ISyncClient.java */
    /* loaded from: classes3.dex */
    public static class b {

        /* renamed from: a  reason: collision with root package name */
        boolean f13451a;

        /* renamed from: b  reason: collision with root package name */
        String f13452b;

        public b(boolean z, String str) {
            this.f13451a = z;
            this.f13452b = str;
        }

        public boolean isSuccess() {
            return this.f13451a;
        }

        public String getErrMsg() {
            return this.f13452b;
        }
    }
}
