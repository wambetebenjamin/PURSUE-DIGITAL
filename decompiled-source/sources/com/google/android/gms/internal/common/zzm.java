package com.google.android.gms.internal.common;
/* compiled from: com.google.android.gms:play-services-basement@@18.9.0 */
/* loaded from: classes5.dex */
final class zzm extends zzl {
    private final char zza;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzm(char c2) {
        this.zza = c2;
    }

    public final String toString() {
        char[] cArr = {'\\', 'u', 0, 0, 0, 0};
        int i2 = this.zza;
        for (int i3 = 0; i3 < 4; i3++) {
            cArr[5 - i3] = "0123456789ABCDEF".charAt(i2 & 15);
            i2 >>= 4;
        }
        String copyValueOf = String.copyValueOf(cArr);
        StringBuilder sb = new StringBuilder(String.valueOf(copyValueOf).length() + 18);
        sb.append("CharMatcher.is('");
        sb.append(copyValueOf);
        sb.append("')");
        return sb.toString();
    }

    @Override // com.google.android.gms.internal.common.zzp
    public final boolean zza(char c2) {
        return c2 == this.zza;
    }
}
