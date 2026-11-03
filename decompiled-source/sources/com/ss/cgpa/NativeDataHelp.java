package com.ss.cgpa;
/* loaded from: classes9.dex */
public class NativeDataHelp {
    public final native void closeDataClientHelp();

    public final native void flushCacheMsg();

    public final native void freeMsgInfo(long j2);

    public final native int getCgpMsgInfoSizes(long j2);

    public final native byte[] packCgpMsgInfo(long j2, int i2);

    public final native boolean procReadCgpMsg(long j2);

    public final native void setDataClientHelp(DataClientHelp dataClientHelp);

    public final native void stopReport();

    public final native long unpackCgpMsgInfo(byte[] bArr);
}
