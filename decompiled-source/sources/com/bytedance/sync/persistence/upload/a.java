package com.bytedance.sync.persistence.upload;

import com.bytedance.sync.protocal.g;
import java.util.List;
/* compiled from: UploadDao.java */
/* loaded from: classes3.dex */
public interface a {
    void deleteAckData(long j2, long j3);

    void deleteMsgWhichBusinessNotExist();

    void insert(c cVar);

    List<c> queryDistributeMsgs(long j2, int i2);

    List<c> queryDistributeMsgsWithSyncId(long j2, int i2);

    List<c> queryMsgs(int i2);

    List<c> queryUploadMsgByDeviceInfo(g gVar, String str, int i2);

    List<c> queryUploadMsgByDeviceInfo(g gVar, String str, String str2, int i2);

    int update(List<c> list);
}
