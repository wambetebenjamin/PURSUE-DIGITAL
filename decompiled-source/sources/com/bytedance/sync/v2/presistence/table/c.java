package com.bytedance.sync.v2.presistence.table;

import com.bytedance.sync.v2.protocal.g;
import com.bytedance.sync.v2.protocal.p;
/* compiled from: SyncCursor.java */
/* loaded from: classes3.dex */
public class c {
    public g bucket;
    public String did;
    public long receiveCursor;
    public long reportCursor;
    public String syncId;
    public p topicType;
    public String uid;

    public String toString() {
        return "SyncCursor{did='" + this.did + "', uid='" + this.uid + "', syncId='" + this.syncId + "', topicType=" + this.topicType + ", bucket=" + this.bucket + ", receiveCursor=" + this.receiveCursor + ", reportCursor=" + this.reportCursor + '}';
    }

    public c() {
    }

    public c(c cVar) {
        this.did = cVar.did;
        this.uid = cVar.uid;
        this.syncId = cVar.syncId;
        this.topicType = cVar.topicType;
        this.bucket = cVar.bucket;
        this.receiveCursor = cVar.receiveCursor;
        this.reportCursor = cVar.reportCursor;
    }
}
