package com.bytedance.gameprotect;

import android.util.Base64;
import tt.g.p.f.n.j;
/* loaded from: classes2.dex */
public abstract class GPPacketChannel {
    public static final int MAX_PACKET_SIZE = 2596;
    private static GPPacketChannel sInstance;

    public static synchronized GPPacketChannel open() {
        GPPacketChannel gPPacketChannel;
        synchronized (GPPacketChannel.class) {
            if (sInstance == null) {
                sInstance = new GameChannel(MAX_PACKET_SIZE);
            }
            gPPacketChannel = sInstance;
        }
        return gPPacketChannel;
    }

    public abstract byte[] readPacket();

    public byte[] readPacketText() {
        byte[] readPacket = readPacket();
        return readPacket.length == 0 ? readPacket : Base64.encode(readPacket, 10);
    }

    public void testEchoRequest(int i2) {
        j.b(i2);
    }

    public abstract int writePacket(byte[] bArr);

    public int writePacketText(byte[] bArr) {
        bArr.getClass();
        if (bArr.length == 0) {
            return -1;
        }
        return writePacket(Base64.decode(bArr, 8));
    }
}
