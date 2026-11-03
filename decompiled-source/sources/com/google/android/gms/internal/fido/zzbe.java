package com.google.android.gms.internal.fido;

import java.io.IOException;
import java.math.RoundingMode;
import javax.annotation.CheckForNull;
/* compiled from: com.google.android.gms:play-services-fido@@20.0.1 */
/* loaded from: classes5.dex */
class zzbe extends zzbf {
    final zzbb zzb;
    @CheckForNull
    final Character zzc;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Removed duplicated region for block: B:10:0x001a  */
    /* JADX WARN: Removed duplicated region for block: B:12:0x001d  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public zzbe(com.google.android.gms.internal.fido.zzbb r4, @javax.annotation.CheckForNull java.lang.Character r5) {
        /*
            r3 = this;
            r3.<init>()
            r3.zzb = r4
            r0 = 0
            r1 = 1
            if (r5 == 0) goto L17
            r5.charValue()
            r2 = 61
            boolean r4 = r4.zzb(r2)
            if (r4 != 0) goto L15
            goto L17
        L15:
            r4 = r0
            goto L18
        L17:
            r4 = r1
        L18:
            if (r4 == 0) goto L1d
            r3.zzc = r5
            return
        L1d:
            java.lang.IllegalArgumentException r4 = new java.lang.IllegalArgumentException
            java.lang.Object[] r1 = new java.lang.Object[r1]
            r1[r0] = r5
            java.lang.String r5 = "Padding character %s was already in alphabet"
            java.lang.String r5 = com.google.android.gms.internal.fido.zzan.zza(r5, r1)
            r4.<init>(r5)
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.fido.zzbe.<init>(com.google.android.gms.internal.fido.zzbb, java.lang.Character):void");
    }

    public final boolean equals(@CheckForNull Object obj) {
        if (obj instanceof zzbe) {
            zzbe zzbeVar = (zzbe) obj;
            if (this.zzb.equals(zzbeVar.zzb)) {
                Character ch = this.zzc;
                Character ch2 = zzbeVar.zzc;
                if (ch == ch2) {
                    return true;
                }
                if (ch != null && ch.equals(ch2)) {
                    return true;
                }
            }
        }
        return false;
    }

    public final int hashCode() {
        int hashCode = this.zzb.hashCode();
        Character ch = this.zzc;
        return hashCode ^ (ch == null ? 0 : ch.hashCode());
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("BaseEncoding.");
        sb.append(this.zzb);
        if (8 % this.zzb.zzb != 0) {
            if (this.zzc == null) {
                sb.append(".omitPadding()");
            } else {
                sb.append(".withPadChar('");
                sb.append(this.zzc);
                sb.append("')");
            }
        }
        return sb.toString();
    }

    @Override // com.google.android.gms.internal.fido.zzbf
    void zza(Appendable appendable, byte[] bArr, int i2, int i3) throws IOException {
        int i4 = 0;
        zzam.zze(0, i3, bArr.length);
        while (i4 < i3) {
            zzc(appendable, bArr, i4, Math.min(this.zzb.zzd, i3 - i4));
            i4 += this.zzb.zzd;
        }
    }

    @Override // com.google.android.gms.internal.fido.zzbf
    final int zzb(int i2) {
        zzbb zzbbVar = this.zzb;
        return zzbbVar.zzc * zzbh.zza(i2, zzbbVar.zzd, RoundingMode.CEILING);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void zzc(Appendable appendable, byte[] bArr, int i2, int i3) throws IOException {
        zzam.zze(i2, i2 + i3, bArr.length);
        int i4 = 0;
        zzam.zzc(i3 <= this.zzb.zzd);
        long j2 = 0;
        for (int i5 = 0; i5 < i3; i5++) {
            j2 = (j2 | (bArr[i2 + i5] & 255)) << 8;
        }
        int i6 = ((i3 + 1) * 8) - this.zzb.zzb;
        while (i4 < i3 * 8) {
            zzbb zzbbVar = this.zzb;
            appendable.append(zzbbVar.zza(zzbbVar.zza & ((int) (j2 >>> (i6 - i4)))));
            i4 += this.zzb.zzb;
        }
        if (this.zzc != null) {
            while (i4 < this.zzb.zzd * 8) {
                this.zzc.charValue();
                appendable.append('=');
                i4 += this.zzb.zzb;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzbe(String str, String str2, @CheckForNull Character ch) {
        this(new zzbb(str, str2.toCharArray()), ch);
    }
}
