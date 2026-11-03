package com.google.android.gms.internal.common;
/* compiled from: com.google.android.gms:play-services-basement@@18.9.0 */
/* loaded from: classes5.dex */
public final class zzai {
    /* JADX INFO: Access modifiers changed from: package-private */
    public static Object[] zza(Object[] objArr, int i2) {
        for (int i3 = 0; i3 < i2; i3++) {
            if (objArr[i3] == null) {
                StringBuilder sb = new StringBuilder(String.valueOf(i3).length() + 9);
                sb.append("at index ");
                sb.append(i3);
                throw new NullPointerException(sb.toString());
            }
        }
        return objArr;
    }
}
