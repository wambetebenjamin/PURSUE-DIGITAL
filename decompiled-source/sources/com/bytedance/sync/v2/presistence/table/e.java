package com.bytedance.sync.v2.presistence.table;

import com.bytedance.sync.v2.protocal.g;
import java.util.Arrays;
/* compiled from: UploadItem.java */
/* loaded from: classes3.dex */
public class e {
    public g bucket;
    public long business;
    public long cursor;
    public byte[] data;
    public String did;
    public long id;
    public String md5;
    public String syncId;
    public String uid;

    public String toString() {
        return "UploadItem{id=" + this.id + ", syncId=" + this.syncId + ", business=" + this.business + ", did='" + this.did + "', uid='" + this.uid + "', bucket=" + this.bucket + ", cursor=" + this.cursor + ", data=" + Arrays.toString(this.data) + ", md5='" + this.md5 + "'}";
    }
}
