package com.bytedance.sync.model;

import com.bytedance.sync.protocal.g;
/* compiled from: StartUpData.java */
/* loaded from: classes3.dex */
public class c {
    public g bucket;
    public long cursor;
    public String did;
    public long syncId;
    public String uid;

    public String toString() {
        return "StartUpData{did='" + this.did + "', uid='" + this.uid + "', syncId=" + this.syncId + ", cursor=" + this.cursor + ", bucket=" + this.bucket + '}';
    }
}
