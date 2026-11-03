package com.bytedance.sync.v2.presistence.table;

import com.bytedance.sync.v2.protocal.g;
import com.bytedance.sync.v2.protocal.h;
import java.util.Arrays;
/* compiled from: SyncLog.java */
/* loaded from: classes3.dex */
public class d {
    public g bucket;
    public long business;
    public h consumeType;
    public byte[] data;
    public com.bytedance.sync.model.a dataType;
    public String did;
    public String md5;
    public long publishTs;
    public long receiveTs;
    public String reqId;
    public long syncCursor;
    public String syncId;
    public String uid;

    public String toString() {
        return "SyncLog{syncId='" + this.syncId + "', did='" + this.did + "', uid='" + this.uid + "', syncCursor=" + this.syncCursor + ", data=" + Arrays.toString(this.data) + ", md5='" + this.md5 + "', business=" + this.business + ", consumeType=" + this.consumeType + ", dataType=" + this.dataType + ", publishTs=" + this.publishTs + ", receiveTs=" + this.receiveTs + ", bucket=" + this.bucket + ", reqId='" + this.reqId + "'}";
    }
}
