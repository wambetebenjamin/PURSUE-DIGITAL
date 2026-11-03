package com.meituan.robust;
/* loaded from: classes9.dex */
public class Decryptor {
    private static int[] KEYS = new int[8];
    private static final int KEYS_LENGTH = 8;
    private static final int START_KEY = 137;
    private int mKeysIndex = 0;

    public Decryptor() {
        initKeys();
    }

    private void initKeys() {
        int i2 = START_KEY;
        int i3 = 0;
        while (i3 < 8) {
            KEYS[i3] = i2;
            i3++;
            i2++;
        }
    }

    public void decrypt(byte[] bArr, int i2) {
        for (int i3 = 0; i3 < i2; i3++) {
            byte b2 = bArr[i3];
            int[] iArr = KEYS;
            int i4 = this.mKeysIndex;
            this.mKeysIndex = i4 + 1;
            bArr[i3] = (byte) (b2 ^ iArr[i4 % 8]);
        }
    }
}
