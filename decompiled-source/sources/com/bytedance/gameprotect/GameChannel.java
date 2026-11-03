package com.bytedance.gameprotect;
/* loaded from: classes2.dex */
public final class GameChannel extends GPPacketChannel {

    /* renamed from: a  reason: collision with root package name */
    private static byte[] f8832a = new byte[0];

    /* JADX INFO: Access modifiers changed from: protected */
    public GameChannel(int i2) {
        nativeInit(i2);
    }

    private native void nativeInit(int i2);

    private native byte[] nativeReadPacket();

    private native int nativeWritePacket(byte[] bArr, int i2, int i3);

    @Override // com.bytedance.gameprotect.GPPacketChannel
    public byte[] readPacket() {
        byte[] nativeReadPacket = nativeReadPacket();
        return nativeReadPacket == null ? f8832a : nativeReadPacket;
    }

    @Override // com.bytedance.gameprotect.GPPacketChannel
    public int writePacket(byte[] bArr) {
        bArr.getClass();
        if (bArr.length == 0) {
            return -1;
        }
        return nativeWritePacket(bArr, 0, bArr.length);
    }
}
