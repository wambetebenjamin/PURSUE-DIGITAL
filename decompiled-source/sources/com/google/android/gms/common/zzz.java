package com.google.android.gms.common;
/* compiled from: com.google.android.gms:play-services-basement@@18.9.0 */
/* loaded from: classes4.dex */
final class zzz {
    /* JADX INFO: Access modifiers changed from: package-private */
    public static int zza(int i2) {
        int[] iArr = {1, 2, 3, 4, 5, 6};
        for (int i3 = 0; i3 < 6; i3++) {
            int i4 = iArr[i3];
            int i5 = i4 - 1;
            if (i4 == 0) {
                throw null;
            }
            if (i5 == i2) {
                return i4;
            }
        }
        return 1;
    }
}
